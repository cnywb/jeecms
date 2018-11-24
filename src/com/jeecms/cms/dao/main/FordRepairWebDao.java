package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.FordRepairWeb;
import com.jeecms.common.page.Pagination;

public interface FordRepairWebDao {

	public Pagination getFordRepairWebByUserId(Integer userId,int pageNo, int pageSize);
	
	public List<FordRepairWeb> getFordRepairWebByUserId(Integer userId,int count);
	
	public List<FordRepairWeb> getFordRepairWebByCardId(String cardId,int count);
	
	
}
