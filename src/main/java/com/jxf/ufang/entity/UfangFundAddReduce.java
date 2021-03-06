package com.jxf.ufang.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.jxf.svc.sys.crud.entity.CrudEntity;

/**
 * 优放机构加减款记录Entity
 * @author suHuimin
 * @version 2019-01-26
 */
public class UfangFundAddReduce extends CrudEntity<UfangFundAddReduce> {
	
	private static final long serialVersionUID = 1L;
	
	public enum Type{
		
		/** 客服后台加款 */
		add,
		
		/** 客服后台减款 */
		reduce
		
	}
	public enum Status{
		
		/** 待审核 */
		auditing,
		
		/** 待复审 */
		retrial,
	
		/** 已通过 */
		passed,
		
		/** 已拒绝 */
		reject
		
	}
			/** ufang公司 */
	private UfangBrn ufangBrn;		
			/** 交易类型 */
	private Type type;		
			/** 交易金额 */
	private BigDecimal amount;		
			/** 账户余额 */
	private BigDecimal curBal;		
			/** 币种 */
	private String currCode;		
			/** 交易状态 */
	private Status status;		
			/** 查询申请起始时间 */
	private Date beginTime;		
			/** 查询申请结束时间 */
	private Date endTime;
	
	public UfangFundAddReduce() {
		super();
	}

	public UfangFundAddReduce(Long id){
		super(id);
	}

	public UfangBrn getUfangBrn() {
		return ufangBrn;
	}

	public void setUfangBrn(UfangBrn ufangBrn) {
		this.ufangBrn = ufangBrn;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public BigDecimal getCurBal() {
		return curBal;
	}

	public void setCurBal(BigDecimal curBal) {
		this.curBal = curBal;
	}
	
	@Length(min=1, max=3, message="币种长度必须介于 1 和 3 之间")
	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}