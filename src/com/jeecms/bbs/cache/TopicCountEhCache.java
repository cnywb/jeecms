package com.jeecms.bbs.cache;

import com.jeecms.bbs.schedule.ClearUserOnlineJob.TopicCountEnum;

public interface TopicCountEhCache {

	public Long getViewCount(Integer topicId);
	
	public Long getViewCount(Integer topicId,TopicCountEnum e);
	
	public Long setViewCount(Integer topicId);

	public boolean getLastReply(Integer userId, long time);

}
