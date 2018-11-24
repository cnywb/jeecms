package com.jeecms.cms.dao.main.impl.campaign.luckydraw;

import com.jeecms.cms.dao.campaign.lucydraw.LuckyDrawResultDao;
import com.jeecms.cms.entity.campaign.luckydraw.LuckyDrawResult;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class LuckyDrawResultDaoImpl extends
		HibernateBaseDao<LuckyDrawResult, Long> implements LuckyDrawResultDao {

	@Override
	protected Class<LuckyDrawResult> getEntityClass() {
		// TODO Auto-generated method stub
		return LuckyDrawResult.class;
	}
	
	public void add(LuckyDrawResult t){
		getSession().save(t);
	}

	public int  getTotalCountByLuckyDrawIdAndCreaterId(long luckyDrawIdId,Integer createrId){
		Finder f = Finder.create("select bean.id from LuckyDrawResult bean where bean.award.luckyDraw.id=:luckyDrawIdId and bean.creater.id=:createrId");
		f.setParam("luckyDrawIdId",luckyDrawIdId);
		f.setParam("createrId",createrId);
		return 	countQueryResult(f);
	}
}
