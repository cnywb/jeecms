/**
 * 
 */
package com.jeecms.point.entity;

import java.util.Date;

import com.jeecms.point.entity.base.BasePointOrderDetails;

/**
 * @author wanglijun
 *
 */
public class PointOrderDetails extends BasePointOrderDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7590573332054796293L;

	/**
	 * 
	 */
	public PointOrderDetails() {
		 super();
	}

	/**
	 * @param id
	 */
	public PointOrderDetails(Long id) {
		super(id);
		 
	}

	/**
	 * @param id
	 * @param orderId
	 * @param userId
	 * @param productId
	 * @param productSnapshotId
	 * @param pointNum
	 * @param payoutPointNum
	 * @param payoutDate
	 */
	public PointOrderDetails(Long id, Long orderId, Long userId,
			Long productId, Long productSnapshotId, Long pointNum,
			Long payoutPointNum, Date payoutDate) {
		super(id, orderId, userId, productId, productSnapshotId, pointNum,
				payoutPointNum, payoutDate);
	}
 

}
