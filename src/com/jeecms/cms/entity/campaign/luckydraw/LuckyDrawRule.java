package com.jeecms.cms.entity.campaign.luckydraw;

import java.io.Serializable;
import java.util.Date;

/**
 * 抽奖规则
 * @author xuwen
 *
 */
public class LuckyDrawRule implements Serializable{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 3554701565555304643L;

	private long id;
	  
    private LuckyDrawAward award;
	  
    private Integer awardQty=0;//出奖数量
	  
    private Integer remainingQty=0;//剩余数量
    
    public Integer getRemainingQty() {
		return remainingQty;
	}

	public void setRemainingQty(Integer remainingQty) {
		this.remainingQty = remainingQty;
	}

	private Date awardDate;//出奖日期
	  
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

	public Integer getAwardQty() {
		return awardQty;
	}

	public void setAwardQty(Integer awardQty) {
		this.awardQty = awardQty;
	}

	public Date getAwardDate() {
		return awardDate;
	}

	public void setAwardDate(Date awardDate) {
		this.awardDate = awardDate;
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
