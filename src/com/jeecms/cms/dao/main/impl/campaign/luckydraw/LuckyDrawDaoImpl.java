package com.jeecms.cms.dao.main.impl.campaign.luckydraw;

import java.util.Date;
import java.util.List;

import com.jeecms.cms.dao.campaign.lucydraw.LuckyDrawDao;
import com.jeecms.cms.entity.campaign.luckydraw.LuckyDraw;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class LuckyDrawDaoImpl extends HibernateBaseDao<LuckyDraw, Long>
		implements LuckyDrawDao {

	@Override
	protected Class<LuckyDraw> getEntityClass() {
		// TODO Auto-generated method stub
		return LuckyDraw.class;
	}

	@Override
	public LuckyDraw findByCode(String code) {
		String hql = "select bean from LuckyDraw bean where bean.code=?";
		List<LuckyDraw> list=find(hql,code);
		return list.size()>0?list.get(0):null;
	}

	
	public List<LuckyDraw> findAllByCurrentDate() {
		String hql = "select bean from LuckyDraw bean where bean.startTime<=? and bean.endTime>=? and bean.status=?";
		Date date=new Date();
		List<LuckyDraw> list=find(hql,date,date,1);
		return list;
	}
	
	@Override
	public void add(LuckyDraw t) {
		getSession().save(t);
	}

}
