/**
 * 
 */
package com.jeecms.point.dao.product;

import com.jeecms.point.entity.PointProductSnapshot;

/**
 * @author wanglijun
 *
 */
public interface PointProductSnapshotDao {
	/***
	 * 保存产品快照
	 * @param pointProductSnapshot
	 * @return PointProductSnapshot
	 */
	public PointProductSnapshot save(PointProductSnapshot pointProductSnapshot);
	/***
	 * 根据快照ID查询产品信息
	 * @param id 快照ID
	 * @return PointProductSnapshot
	 */
	public PointProductSnapshot findById(Long id);
}
