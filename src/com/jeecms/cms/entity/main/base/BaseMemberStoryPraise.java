package com.jeecms.cms.entity.main.base;

import java.util.Date;


import com.jeecms.cms.entity.campaign.sfdj.MemberStory;
import com.jeecms.cms.entity.main.CmsUser;





public class BaseMemberStoryPraise {
	
	private long id;
	
	private MemberStory memberStory;
	
	private CmsUser creater;//点赞会员
	
    private Date createTime=new Date();
	
	private Date updateTime=new Date();

	public MemberStory getMemberStory() {
		return memberStory;
	}

	public void setMemberStory(MemberStory memberStory) {
		this.memberStory = memberStory;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BaseMemberStoryPraise(MemberStory memberStory, CmsUser creater) {
		super();
		this.memberStory = memberStory;
		this.creater = creater;
	}


	public BaseMemberStoryPraise() {
		 initialize ();
	}

	protected void initialize () {}
	

}
