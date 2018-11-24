/**
 * 
 */
package com.jeecms.point.manager.point.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.dao.point.PointPayoutDao;
import com.jeecms.point.entity.PointPayout;
import com.jeecms.point.manager.point.PointPayoutMng;
import com.jeecms.point.vo.point.PointPayoutQueryVo;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
@Service
@Transactional
public class PointPayoutMngImpl implements PointPayoutMng {
	
	@Autowired
	private PointPayoutDao pointPayoutDao;

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointPayoutMng#save(com.jeecms.point.entity.PointPayout)
	 */
	@Override
	public PointPayout save(PointPayout pointPayout){
		return pointPayoutDao.save(pointPayout);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointPayoutMng#exchangeProduct(java.lang.Long, java.lang.Integer, java.lang.Long)
	 */
	@Override
	public boolean exchangeProduct(Long productId, Integer userId, Long orderId) {
		
		return false;
	}

 

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointPayoutMng#queryPagination(com.jeecms.point.web.query.QueryVo, com.jeecms.point.vo.point.PointPayoutQueryVo)
	 */
	@Override
	public Pagination queryPagination(QueryVo queryVo,PointPayoutQueryVo pointPayoutQueryVo) {
		 
		return this.pointPayoutDao.queryPagination(queryVo, pointPayoutQueryVo);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointPayoutMng#queryPaginationForUser(com.jeecms.point.web.query.QueryVo, java.lang.Integer)
	 */
	@Override
	public Pagination queryPaginationForUser(QueryVo queryVo, Integer userId) {
	 
		return this.queryPaginationForUser(queryVo, userId);
	}
	
	
}
