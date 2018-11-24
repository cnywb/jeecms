package com.jeecms.cms.manager.main;

import java.util.List;

import com.jeecms.cms.entity.main.FordRepairWeb;
import com.jeecms.cms.vo.CardScoreInfoVo;
import com.jeecms.common.page.Pagination;

public interface FordRepairWebMng {
	public Pagination getFordRepairWebByUserId(Integer userId,int pageNo, int pageSize);
	
	public List<FordRepairWeb> getFordRepairWebByUserId(Integer userId,int count);
	
	public List<FordRepairWeb> getFordRepairWebByCardId(String cardId,int count);
	
	public List<CardScoreInfoVo> getCardScoreInfoVoBy(Integer userId,int repairCount,int dealCount);
}
