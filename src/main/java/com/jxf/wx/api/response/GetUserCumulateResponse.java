package com.jxf.wx.api.response;

import com.jxf.wx.api.entity.UserCumulate;

import java.util.List;

/***
 * 
 * @类功能说明： 
 * @类修改者： 
 * @修改日期： 
 * @修改说明： 
 * @公司名称：北京金信服科技有限公司
 * @作者：Administrator 
 * @创建时间：2016年11月11日 下午9:09:30 
 * @版本：V1.0
 */
public class GetUserCumulateResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	private List<UserCumulate> list;

    public List<UserCumulate> getList() {
        return list;
    }

    public void setList(List<UserCumulate> list) {
        this.list = list;
    }
}
