package com.jeecms.cms.entity.campaign.luckydraw;

import java.io.Serializable;
import java.util.Date;

import com.jeecms.cms.entity.main.CmsUser;

/**
 * 中奖结果
 * @author xuwen
 *
 */
public class LuckyDrawResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8391228790099332353L;

	private long id;
	
    private LuckyDrawAward award;//中奖奖品
	
	private CmsUser creater;//中奖用户
	
	private Date createTime=new Date();
	    
	private Date updateTime=new Date();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LuckyDrawAward getAward() {
		return award;
	}

	public void setAward(LuckyDrawAward award) {
		this.award = award;
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
	
	
}
