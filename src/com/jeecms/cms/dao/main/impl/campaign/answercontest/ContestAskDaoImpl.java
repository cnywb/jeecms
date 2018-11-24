package com.jeecms.cms.dao.main.impl.campaign.answercontest;

import java.util.List;
import com.jeecms.cms.dao.campaign.answercontest.ContestAskDao;
import com.jeecms.cms.entity.campaign.answercontest.ContestAsk;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class ContestAskDaoImpl extends HibernateBaseDao<ContestAsk,Long>implements ContestAskDao {

	
	
	@Override
	protected Class<ContestAsk> getEntityClass() {
		// TODO Auto-generated method stub
		return ContestAsk.class;
	}
	public List<ContestAsk> findAllByGroup(String group) {
		String hql = "select bean from ContestAsk bean where bean.group=?";
		List<ContestAsk> list=find(hql,group);
		return list;
	}
	
	public ContestAsk findByCode(String code) {
		String hql = "select bean from ContestAsk bean where bean.code=?";
		List<ContestAsk> list=find(hql,code);
		return list.size()>0?list.get(0):null;
	}

	
	
	
}
