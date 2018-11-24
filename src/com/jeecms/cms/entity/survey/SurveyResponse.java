package com.jeecms.cms.entity.survey;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.jeecms.common.util.JSONUtil;
@JsonAutoDetect
public class SurveyResponse {

	private int status=0;
	private String message="";
	private String code="";
	private Long point;
	

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
	public Long getPoint() {
		return point;
	}
	public void setPoint(Long point) {
		this.point = point;
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
	
}
