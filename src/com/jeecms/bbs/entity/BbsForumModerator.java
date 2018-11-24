package com.jeecms.bbs.entity;

import java.util.Date;

//版块版主
public class BbsForumModerator {

	private Long id;
	private BbsForum forum;
	private BbsUser user;
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
