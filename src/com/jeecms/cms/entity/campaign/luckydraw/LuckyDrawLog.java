package com.jeecms.cms.entity.campaign.luckydraw;

import java.io.Serializable;
import java.util.Date;

import com.jeecms.cms.entity.main.CmsUser;


/**
 * 抽奖日志
 * @author xuwen
 *
 */
public class LuckyDrawLog implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3941327730123838269L;

	/**
	 * 
	 */
	
	private long id;
	
	private Integer status=0;//0未中奖,1中奖
	
	private CmsUser creater;//抽奖用户
	
	private LuckyDraw luckyDraw;//所属活动
	
	private Date createTime=new Date();
	    
	private Date updateTime=new Date();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public LuckyDraw getLuckyDraw() {
		return luckyDraw;
	}

	public void setLuckyDraw(LuckyDraw luckyDraw) {
		this.luckyDraw = luckyDraw;
	}
	
	
}
