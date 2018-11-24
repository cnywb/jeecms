package com.jeecms.point.entity.base;

import java.io.Serializable;

public class BaseActivityProduct extends BasePoint implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 103721534512467527L;
	
	private Long productId;
	private Long activityId;
	private BasePointProduct product;
	private BasePointActivity activity;
	
	
	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	/**
	 * @return the activityId
	 */
	public Long getActivityId() {
		return activityId;
	}
	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	/**
	 * @return the product
	 */
	public BasePointProduct getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(BasePointProduct product) {
		this.product = product;
	}
	/**
	 * @return the activity
	 */
	public BasePointActivity getActivity() {
		return activity;
	}
	/**
	 * @param activity the activity to set
	 */
	public void setActivity(BasePointActivity activity) {
		this.activity = activity;
	}
	
}
