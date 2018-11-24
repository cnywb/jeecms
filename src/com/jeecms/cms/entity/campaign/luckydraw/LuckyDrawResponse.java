package com.jeecms.cms.entity.campaign.luckydraw;

import com.jeecms.common.util.JSONUtil;

public class LuckyDrawResponse {
	
	private int status=0;
	private String message="";
	
	private String code="";
	
	private String name="";
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return JSONUtil.objectToJson(this);
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
