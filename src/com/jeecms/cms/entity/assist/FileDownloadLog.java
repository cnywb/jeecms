package com.jeecms.cms.entity.assist;

import java.io.Serializable;
import java.util.Date;

import com.jeecms.cms.entity.main.CmsUser;


/**
 * 文件下载日志
 * @author xuwen
 *
 */
public class FileDownloadLog implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3941327730123838269L;

	/**
	 * 
	 */
	
	private Long id;
	
	private Long fileId;//
	
	private CmsUser creater;//抽奖用户
	
	private Date createTime=new Date();
	    
	private Date updateTime=new Date();

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CmsUser getCreater() {
		return creater;
	}

	public void setCreater(CmsUser creater) {
		this.creater = creater;
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

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	


	
	
}
