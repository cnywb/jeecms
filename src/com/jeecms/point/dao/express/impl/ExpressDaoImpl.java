/**
 * 
 */
package com.jeecms.point.dao.express.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.point.dao.express.ExpressDao;
import com.jeecms.point.entity.Express;

/**
 * @author wanglijun
 *
 */
@Repository
public class ExpressDaoImpl  extends HibernateBaseDao<Express, Long>  implements ExpressDao {

	@Override
	protected Class<Express> getEntityClass() {
		return Express.class;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.express.ExpressDao#save(com.jeecms.point.entity.Express)
	 */
	@Override
	public Express save(Express express) {
		this.getSession().save(express);
		this.getSession().flush();
		return express;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.express.ExpressDao#findById(java.lang.Long)
	 */
	@Override
	public Express findById(Long id) {
		return super.get(id);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.express.ExpressDao#update(com.jeecms.point.entity.Express)
	 */
	@Override
	public Express update(Express express) {
		this.getSession().update(express);
		this.getSession().flush();
		return express;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.express.ExpressDao#findByOrderId(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Express findByOrderId(Long orderId) {
		String hql="from Express where orderId=:orderId";
		Finder finder=Finder.create(hql);
		finder.setParam("orderId", orderId);
		List<Express>  expresses=this.find(finder);
		if(!CollectionUtils.isEmpty(expresses)){
			return expresses.get(0);
		}
		return null;
	}
	
	
}
