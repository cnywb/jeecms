package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.jeecms.common.util.JsonDateSerializer;




//主题草稿箱，用于保存用户临时主题，


@JsonAutoDetect

public class BbsTopicDraft implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6196262023082250916L;

	private Long id;
	
	private String title;//标题
	
	private String content;//内容
	
	private Integer postTypeId;//类别
	
	private Integer forumId;//版块
	
	private Integer userId;//用户
	
	private Date createTime=new Date();
		
	private Date updateTime=new Date();

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPostTypeId() {
		return postTypeId;
	}

	public void setPostTypeId(Integer postTypeId) {
		this.postTypeId = postTypeId;
	}

	public Integer getForumId() {
		return forumId;
	}

	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
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
