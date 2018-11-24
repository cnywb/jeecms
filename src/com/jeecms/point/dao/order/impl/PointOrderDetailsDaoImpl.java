/**
 * 
 */
package com.jeecms.point.dao.order.impl;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.point.dao.order.PointOrderDetailsDao;
import com.jeecms.point.entity.PointOrderDetails;

/**
 * @author wanglijun
 *  添加
 */
@Repository
public class PointOrderDetailsDaoImpl extends HibernateBaseDao<PointOrderDetails,Long>  implements PointOrderDetailsDao {

	@Override
	protected Class<PointOrderDetails> getEntityClass() {
		return PointOrderDetails.class;
	} 
		
}
