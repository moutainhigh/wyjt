package com.jxf.web.h5.gxt.member;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxf.mem.entity.Member;
import com.jxf.mem.entity.MemberResetPayPwd;
import com.jxf.mem.entity.MemberVideoVerify;
import com.jxf.mem.service.MemberResetPayPwdService;
import com.jxf.mem.service.MemberService;
import com.jxf.mem.service.MemberVideoVerifyService;
import com.jxf.mem.utils.VerifiedUtils;
import com.jxf.mms.msg.SendSmsMsgService;
import com.jxf.mms.msg.utils.VerifyCodeUtils;
import com.jxf.svc.cache.RedisUtils;
import com.jxf.svc.security.PasswordUtils;
import com.jxf.svc.utils.StringUtils;
import com.jxf.web.app.BaseController;
import com.jxf.web.model.ResponseData;




/**
 * 
 * @类功能说明： 密码
 * @类修改者： 
 * @修改日期： 
 * @修改说明： 
 * @公司名称：
 * @作者：wo 
 * @创建时间：2016年12月21日 下午4:07:01 
 * @版本：V1.0
 */
@Controller("gxtH5MemberPasswdController")
@RequestMapping(value="${gxtH5}/member")
public class MemberPasswdController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(MemberPasswdController.class);
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVideoVerifyService memberVideoVerifyService;
	@Autowired
	private MemberResetPayPwdService memberResetPayPwdService;
	@Autowired
    private SendSmsMsgService sendSmsMsgService;


	/**
	 * 发送验证码(无需手机号)
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/sendSmsCode", method = RequestMethod.POST)
	public ResponseData sendSmsCode(String tmplCode) {
		Member member = memberService.getCurrent();
		//生成短信验证码
        String smsCode = VerifyCodeUtils.genSmsValidCode();
        //将短信验证码存入缓存中
		RedisUtils.setForTimeMIN("smsCode" + member.getUsername(), smsCode, 10);
        sendSmsMsgService.sendSms(tmplCode, member.getUsername(), smsCode);

        return ResponseData.success("发送短信验证码成功");
	}
	
	/**
	 * 修改登录密码
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseData changePassword(String password, String smsCode) {
		Member member = memberService.getCurrent();
		String code = RedisUtils.get("smsCode" + member.getUsername());
		if(!StringUtils.equals(smsCode, code)){
			 return ResponseData.error("验证码错误,请重试!");
		}
		
		member.setPassword(PasswordUtils.entryptPassword(password));
		memberService.save(member);
		
		sendSmsMsgService.sendMessage("changeLoginPasswd", member.getUsername(), null);
	    return ResponseData.success("修改登录密码成功");
	}
	
	/**
	 * 	修改交易密码前去实名认证
	 */
	@RequestMapping(value = "/beforeSetPayPwd")
	public @ResponseBody ResponseData beforeSetPayPwd() {
		Member member = memberService.getCurrent();
		MemberResetPayPwd resetPayPwd = new MemberResetPayPwd();//修改支付密码记录
		resetPayPwd.setMember(member);
		resetPayPwd.setPayPwd("");
		resetPayPwd.setStatus(MemberResetPayPwd.Status.pendingReview);
		memberResetPayPwdService.save(resetPayPwd);
		
		MemberVideoVerify videoVerify = new MemberVideoVerify();
		videoVerify.setMember(member);
		videoVerify.setStatus(MemberVideoVerify.Status.pendingReview);
		videoVerify.setType(MemberVideoVerify.Type.setPayPwd);
		videoVerify.setChannel(MemberVideoVerify.Channel.gxt);
		videoVerify.setTrxId(resetPayPwd.getId());
		MemberVideoVerify findPendingReviewVerify = memberVideoVerifyService.get(videoVerify);
		if(findPendingReviewVerify != null){
			return ResponseData.error("身份信息已提交，请勿重复提交!");
		}
		//检查认证失败次数
		Integer failureTimes = memberVideoVerifyService.countFailure(member.getId());
		if(failureTimes > 3){
			return ResponseData.error("您尝试认证失败，不能再继续尝试。如需重新认证请联系客服.");
		}
		
		//3.验证每天重置密码次数 --不能超过2次
		int dayCounts = memberResetPayPwdService.getResetPayPwdCountEveryday(member);
		if(dayCounts > 1) {
			logger.error("====每天重置密码次数不能超过2次====");
			return ResponseData.error("每天重置密码次数不能超过2次");
		}
		//4.验证每月重置密码次数 --不能超过5次
		int monthCounts = memberResetPayPwdService.getResetPayPwdCountEveryMonth(member);
		if(monthCounts > 4) {
			logger.error("====每月重置密码次数不能超过5次====");
			return ResponseData.error("每月重置密码次数不能超过5次");
		}
				
		HashMap<String, Object> result = new HashMap<String,Object>();
		String h5Url = "";
		
		// 人脸demo数据
		memberVideoVerifyService.save(videoVerify);
		h5Url = memberVideoVerifyService.getGxtResult(1,videoVerify.getId().toString(),member);
		
		result.put("h5Url", h5Url);
		result.put("orderId", videoVerify.getId() == null ? "" : videoVerify.getId().toString());
		return ResponseData.success("成功", result);	
	}
	
	/**
	 * 	修改支付密码
	 */
	@RequestMapping(value = "/setPayPwd")
	public @ResponseBody
	ResponseData setPayPwd(String payPassword, String smsCode, String orderId) {	
		Member member = memberService.getCurrent();
		
		String code = RedisUtils.get("smsCode" + member.getUsername());
		if(!StringUtils.equals(smsCode, code)){
			 return ResponseData.error("验证码错误,请重试!");
		}
		
		if(PasswordUtils.validatePassword(payPassword, member.getPassword())) {
			logger.warn("会员：" + member.getId() + " 修改支付密码====支付密码不能和登录密码一致====");
			return ResponseData.error("支付密码不能和登录密码一致");
		}
		if(PasswordUtils.validatePassword(payPassword, member.getPayPassword())) {
			logger.warn("会员：" + member.getId() + " 修改支付密码====支付密码不能和原支付密码一致====");
			return ResponseData.error("支付密码不能和原支付密码一致");
		}
		
		/** 1验证实名认证 */
		Integer verifiedList = member.getVerifiedList();
		if(!VerifiedUtils.isVerified(verifiedList, 0)) {
			logger.error("====您还没有通过身份认证====");
			return ResponseData.error("您还没有通过身份认证");
		}
		if(!VerifiedUtils.isVerified(verifiedList, 1)) {
			logger.error("====您还没有通过人脸认证====");
			return ResponseData.error("您还没有通过人脸认证");
		}
		if(!VerifiedUtils.isVerified(verifiedList, 2)) {
			logger.error("====您还没有通过手机认证====");
			return ResponseData.error("您还没有通过手机认证");
		}
		
		memberVideoVerifyService.dealPayPassword(member,payPassword,orderId);
		return ResponseData.success("修改支付密码成功");
	}
	
	/**
	 * 	设置支付密码
	 */
	@RequestMapping(value = "/installPayPwd")
	public @ResponseBody
	ResponseData installPayPwd(String payPassword) {	
		Member member = memberService.getCurrent();
		if(PasswordUtils.validatePassword(payPassword, member.getPassword())) {
			logger.warn("会员：" + member.getId() + " 修改支付密码====支付密码不能和登录密码一致====");
			return ResponseData.error("支付密码不能和登录密码一致");
		}
		if(PasswordUtils.validatePassword(payPassword, member.getPayPassword())) {
			logger.warn("会员：" + member.getId() + " 修改支付密码====支付密码不能和原支付密码一致====");
			return ResponseData.error("支付密码不能和原支付密码一致");
		}
		
		/** 1验证实名认证 */
		Integer verifiedList = member.getVerifiedList();
		if(!VerifiedUtils.isVerified(verifiedList, 0)) {
			logger.error("====您还没有通过身份认证====");
			return ResponseData.error("您还没有通过身份认证");
		}
		if(!VerifiedUtils.isVerified(verifiedList, 1)) {
			logger.error("====您还没有通过人脸认证====");
			return ResponseData.error("您还没有通过人脸认证");
		}
		if(VerifiedUtils.isVerified(verifiedList,22)) {
			return ResponseData.error("您已设置支付密码!");
		}
		
		member.setVerifiedList(VerifiedUtils.addVerified(member.getVerifiedList(), 22));
		member.setPayPassword(PasswordUtils.entryptPassword(payPassword));
		memberService.save(member);
		
		return ResponseData.success("设置支付密码成功");
	}
	
	@RequestMapping(value="checkPayPwd")
	@ResponseBody
	public ResponseData checkPayPwd(String pwd) {
		Map<String, Object> data = new HashMap<String,Object>();
		if(StringUtils.isBlank(pwd)) {
			return ResponseData.error("参数错误");
		}
		Member member = memberService.getCurrent();	
		ResponseData responseData = memberService.checkPayPwd(pwd, member);
		if(responseData != null && responseData.getCode() == 0) {
			data.put("success", true);
			data.put("message", "验证成功");
		}else if(responseData != null &&responseData.getCode() != 0){
			data.put("success", false);
			data.put("message", responseData.getMessage());
		}else{
			data.put("success", false);
			data.put("message", "服务器开小差，请稍候再试！");
		}
		return ResponseData.success("请求成功", data);
	}
	
}