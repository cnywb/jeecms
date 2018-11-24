package com.jeecms.cms.dao.campaign.lucydraw;

import com.jeecms.cms.entity.campaign.luckydraw.LuckyDrawAward;

public interface LuckyDrawAwardDao {
	public void evictEntity(LuckyDrawAward t);
	public LuckyDrawAward loadAndLockEntity(long id);
	public void update(LuckyDrawAward t);
	public void add(LuckyDrawAward t);
}
