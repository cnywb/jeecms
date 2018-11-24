/**
 * 
 */
package com.jeecms.point.dao.express.impl;

import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.point.dao.express.ExpressCommonDao;
import com.jeecms.point.entity.ExpressCommon;

/**
 * @author wanglijun
 *
 */
public class ExpressCommonDaoImpl extends HibernateBaseDao<ExpressCommon, Long> implements ExpressCommonDao {

	@Override
	protected Class<ExpressCommon> getEntityClass() {	 
		return ExpressCommon.class;
	}

}
