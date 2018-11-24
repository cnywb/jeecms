package com.jeecms.cms.entity.campaign.sfdj;

import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.base.BaseMemberStoryPraise;



public class MemberStoryPraise extends BaseMemberStoryPraise {

	public MemberStoryPraise(MemberStory memberStory, CmsUser creater) {
		super(memberStory, creater);
	}
	public MemberStoryPraise() {
		super();
	}
}
