/**
 * 
 */
package com.jeecms.point.manager.express.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.point.dao.express.ExpressDao;
import com.jeecms.point.entity.Express;
import com.jeecms.point.manager.express.ExpressMng;

/**
 * @author wanglijun
 *
 */
@Service
@Transactional
public class ExpressMngImpl implements ExpressMng {
	
	@Autowired
	private ExpressDao expressDao;

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.express.ExpressMng#save(com.jeecms.point.entity.Express)
	 */
	@Override
	public Express save(Express express) {
		return this.expressDao.save(express);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.express.ExpressMng#findById(java.lang.Long)
	 */
	@Override
	public Express findById(Long id) {	 
		return this.expressDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.express.ExpressMng#update(com.jeecms.point.entity.Express)
	 */
	@Override
	public Express update(Express express) {
	 
		return this.expressDao.update(express);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.express.ExpressMng#findByOrderId(java.lang.Long)
	 */
	@Override
	public Express findByOrderId(Long orderId) {	 
		return this.expressDao.findByOrderId(orderId);
	}
	
}
