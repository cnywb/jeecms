package com.jeecms.cms.dao.main.impl.campaign.luckydraw;



import org.hibernate.LockMode;

import com.jeecms.cms.dao.campaign.lucydraw.LuckyDrawAwardDao;
import com.jeecms.cms.entity.campaign.luckydraw.LuckyDrawAward;
import com.jeecms.common.hibernate3.HibernateBaseDao;


public class LuckyDrawAwardDaoImpl extends
		HibernateBaseDao<LuckyDrawAward, Long> implements LuckyDrawAwardDao {

	@Override
	protected Class<LuckyDrawAward> getEntityClass() {
		// TODO Auto-generated method stub
		return LuckyDrawAward.class;
	}

	
	public void evictEntity(LuckyDrawAward t){
		 getSession().evict(t);
	}
	
	public LuckyDrawAward loadAndLockEntity(long id){
		return (LuckyDrawAward) getSession().load(LuckyDrawAward.class,id,LockMode.UPGRADE);
	}
	
	public void update(LuckyDrawAward t){
		getSession().update(t);
	}

	@Override
	public void add(LuckyDrawAward t) {
		getSession().save(t);
		
	}
	
	
}
