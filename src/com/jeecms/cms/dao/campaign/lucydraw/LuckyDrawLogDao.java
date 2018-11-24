package com.jeecms.cms.dao.campaign.lucydraw;

import com.jeecms.cms.entity.campaign.luckydraw.LuckyDrawLog;

public interface LuckyDrawLogDao {
	public int  getTotalCountByLuckyDrawIdAndCreaterId(long luckyDrawIdId,Integer createrId);
	public long add(LuckyDrawLog t);
}
