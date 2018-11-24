package com.jeecms.cms.dao.campaign.lucydraw;

import java.util.List;

import com.jeecms.cms.entity.campaign.luckydraw.LuckyDraw;

public interface LuckyDrawDao {
	
	public LuckyDraw findByCode(String code);
	public void add(LuckyDraw t);
	public List<LuckyDraw> findAllByCurrentDate();

}
