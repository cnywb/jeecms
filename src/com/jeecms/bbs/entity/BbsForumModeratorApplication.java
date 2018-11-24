package com.jeecms.bbs.entity;

import java.util.Date;


//论坛版主申请

public class BbsForumModeratorApplication {

	
	private Long id;
	private BbsForum forum;
	private BbsUser user;
	private Integer status;//0待受理，1 申请成功，2申请失败
	
	
	
	
	public Integer getStatus() {
		
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	private Date createTime=new Date();
	private Date updateTime=new Date();
	
	public BbsForum getForum() {
		return forum;
	}
	public void setForum(BbsForum forum) {
		this.forum = forum;
	}
	public BbsUser getUser() {
	
		return user;
	}
	public void setUser(BbsUser user) {
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
