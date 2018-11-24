package com.jeecms.cms.entity.campaign.sfdj;



import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.base.BaseMemberStory;

@JsonAutoDetect
@JsonIgnoreProperties(value={"creater"})

public class MemberStory extends BaseMemberStory {

	public MemberStory(String imageUrl, Integer status, String content,
			CmsUser creater) {
		super(imageUrl, status, content, creater);
		
	}
	public MemberStory() {
		super();
	}
}
