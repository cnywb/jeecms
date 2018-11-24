package com.jeecms.cms.dao.main.impl.campaign.luckydraw;

import java.util.List;

import org.hibernate.LockMode;

import com.jeecms.cms.dao.campaign.lucydraw.LuckyDrawRuleDao;
import com.jeecms.cms.entity.campaign.luckydraw.LuckyDrawRule;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class LuckyDrawRuleDaoImpl extends HibernateBaseDao<LuckyDrawRule, Long>
		implements LuckyDrawRuleDao {

	@Override
	protected Class<LuckyDrawRule> getEntityClass() {
		// TODO Auto-generated method stub
		return LuckyDrawRule.class;
	}
	
	public List<LuckyDrawRule> queryAllByLuckyDrawId(long luckyDrawId){
		String hql = "select bean from LuckyDrawRule bean where bean.award.luckyDraw.id=? order by bean.id desc";
		return find(hql,luckyDrawId);
	}
	public LuckyDrawRule loadAndLockEntity(long id){
	  return (LuckyDrawRule) getSession().load(LuckyDrawRule.class,id,LockMode.UPGRADE);
	}
	public void lockEntity(LuckyDrawRule t){
		 getSession().lock(t, LockMode.UPGRADE);
	}
	
	public void evictEntity(LuckyDrawRule t){
		 getSession().evict(t);
	}
	public void update(LuckyDrawRule t){
	  getSession().update(t);
	}
	public void add(LuckyDrawRule t){
		  getSession().save(t);
	}
		
	
	
}
