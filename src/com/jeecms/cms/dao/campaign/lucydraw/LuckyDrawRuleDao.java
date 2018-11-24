package com.jeecms.cms.dao.campaign.lucydraw;

import java.util.List;



import com.jeecms.cms.entity.campaign.luckydraw.LuckyDrawRule;

public interface LuckyDrawRuleDao {
	public List<LuckyDrawRule> queryAllByLuckyDrawId(long luckyDrawId);
	public LuckyDrawRule loadAndLockEntity(long id);
	public void update(LuckyDrawRule t);
	public void add(LuckyDrawRule t);
	public void lockEntity(LuckyDrawRule t);
	public void evictEntity(LuckyDrawRule t);
}
