package com.jeecms.cms.entity.survey;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;


@JsonAutoDetect
public class Survey implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3448705052390850489L;

	private long id;
	
	private Integer participantType=0;//可参与用户类型 0 注册用户,1 认证用户,2 其它
	
	private String name;//名称
	
	private String code;//代码
	
	private String memo;//备注
	
	private Integer status=0;//状态,0停用,1启用
	
	private Date startTime;//开始时间
	 
	private Date endTime;//结束时间
	 
    private Date createTime=new Date();
    
    private Date updateTime=new Date();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getParticipantType() {
		return participantType;
	}

	public void setParticipantType(Integer participantType) {
		this.participantType = participantType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
    
	
}
