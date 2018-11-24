package com.jeecms.core.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.UnifiedUserDao;
import com.jeecms.core.entity.UnifiedUser;

@Repository
public class UnifiedUserDaoImpl extends HibernateBaseDao<UnifiedUser, Integer>
		implements UnifiedUserDao {
	public UnifiedUser getByUsername(String username) {
		return findUniqueByProperty("username", username);
	}

	public UnifiedUser getByMobilePhone(String mobilePhone) {
		return findUniqueByProperty("mobilePhone", mobilePhone);
	}
	
	public UnifiedUser getByOpenId(String openId){
		return findUniqueByProperty("openId", openId);
	}
	
	public List<UnifiedUser> getByEmail(String email) {
		return findByProperty("email", email);
	}

	public int countByEmail(String email) {
		String hql = "select count(*) from UnifiedUser bean where bean.email=:email";
		Query query = getSession().createQuery(hql);
		query.setParameter("email", email);
		return ((Number) query.iterate().next()).intValue();
	}

	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public UnifiedUser findById(Integer id) {
		UnifiedUser entity = get(id);
		return entity;
	}

	public UnifiedUser save(UnifiedUser bean) {
		getSession().save(bean);
		return bean;
	}

	public UnifiedUser deleteById(Integer id) {
		UnifiedUser entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	public UnifiedUser update(UnifiedUser bean) {
		getSession().update(bean);
		return bean;
	}
	@Override
	protected Class<UnifiedUser> getEntityClass() {
		return UnifiedUser.class;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.core.dao.UnifiedUserDao#lockUser(java.lang.Integer, org.hibernate.LockMode)
	 */
	@Override
	public UnifiedUser lockUser(Integer userId, LockMode lockMode) {		 
		return (UnifiedUser) this.getSession().get(UnifiedUser.class,userId,lockMode);
	}
	
	
}