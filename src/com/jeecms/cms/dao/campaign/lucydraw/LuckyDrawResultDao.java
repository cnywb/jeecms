package com.jeecms.cms.dao.campaign.lucydraw;

import com.jeecms.cms.entity.campaign.luckydraw.LuckyDrawResult;

public interface LuckyDrawResultDao {
	public void add(LuckyDrawResult t);
	public int  getTotalCountByLuckyDrawIdAndCreaterId(long luckyDrawIdId,Integer createrId);
}
