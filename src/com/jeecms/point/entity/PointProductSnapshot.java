/**
 * 
 */
package com.jeecms.point.entity;

import com.jeecms.point.entity.base.BasePointProductSnapshot;

/**
 * @author wanglijun
 *
 */
public class PointProductSnapshot extends BasePointProductSnapshot {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3794953821160681734L;

	/**
	 * 
	 */
	public PointProductSnapshot() {
		 super();
	}

	/**
	 * @param id
	 */
	public PointProductSnapshot(Long id) {
		super(id);
	 
	}

	/**
	 * @param id
	 * @param productId
	 * @param productNo
	 * @param categoryId
	 * @param name
	 * @param title
	 * @param subTitle
	 * @param price
	 * @param pointNum
	 * @param stockNum
	 * @param status
	 * @param channel
	 * @param memo
	 * @param imageSmallURL
	 * @param imageMiddleURL
	 * @param imageLargeURL
	 */
	public PointProductSnapshot(Long id, Long productId, String productNo,
			Long categoryId, String name, String title, String subTitle,
			Double price, Long pointNum, Long stockNum, Integer status,
			Integer channel, String memo, String imageSmallURL,
			String imageMiddleURL, String imageLargeURL) {
		super(id, productId, productNo, categoryId, name, title, subTitle,
				price, pointNum, stockNum, status, channel, memo,
				imageSmallURL, imageMiddleURL, imageLargeURL);
	 
	}

}
