/**
 * 
 */
package com.jeecms.point.dao.product.impl;

import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.point.dao.product.PointProductSnapshotDao;
import com.jeecms.point.entity.PointProductSnapshot;

/**
 * @author wanglijun
 *
 */
public class PointProductSnapshotDaoImpl  extends HibernateBaseDao<PointProductSnapshot, Long>	  implements PointProductSnapshotDao {

	@Override
	protected Class<PointProductSnapshot> getEntityClass() {
		return PointProductSnapshot.class;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.PointProductSnapshotDao#save(com.jeecms.point.entity.PointProductSnapshot)
	 */
	@Override
	public PointProductSnapshot save(PointProductSnapshot pointProductSnapshot) {
		this.getSession().save(pointProductSnapshot);
		this.getSession().flush();
		return pointProductSnapshot;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.PointProductSnapshotDao#findById(java.lang.Long)
	 */
	@Override
	public PointProductSnapshot findById(Long id) {	 
		return super.get(id);
	}
	
	
}
