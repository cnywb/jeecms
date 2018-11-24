/**
 * 
 */
package com.jeecms.point.manager.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.point.dao.product.PointProductSnapshotDao;
import com.jeecms.point.entity.PointProductSnapshot;
import com.jeecms.point.manager.product.PointProductSnapshotMng;

/**
 * @author wanglijun
 *
 */
@Service
@Transactional
public class PointProductSnapshotMngImpl implements PointProductSnapshotMng {
	
	@Autowired
	private PointProductSnapshotDao pointProductSnapshotDao;

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.PointProductSnapshotMng#save(com.jeecms.point.entity.PointProductSnapshot)
	 */
	@Override
	public PointProductSnapshot save(PointProductSnapshot pointProductSnapshot) {
		return pointProductSnapshotDao.save(pointProductSnapshot);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.PointProductSnapshotMng#findById(java.lang.Long)
	 */
	@Override
	public PointProductSnapshot findById(Long id) {
		return this.pointProductSnapshotDao.findById(id);
	}
}
