package com.jeecms.cms.entity.main;

import com.jeecms.common.util.JSONUtil;

public class CommonResponse {
	private int status=0;
	private String message="";
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
}
