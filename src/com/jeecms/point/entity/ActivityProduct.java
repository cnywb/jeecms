package com.jeecms.point.entity;

import com.jeecms.point.entity.base.BaseActivityProduct;

public class ActivityProduct extends BaseActivityProduct{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3885882447916293697L;

	public ActivityProduct() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ActivityProduct [getProductId()=" + getProductId()
				+ ", getActivityId()=" + getActivityId() + ", getProduct()="
				+ getProduct() + ", getActivity()=" + getActivity()
				+ ", getId()=" + getId() + ", getCreatedId()=" + getCreatedId()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedId()=" + getUpdatedId() + ", getUpdatedDate()="
				+ getUpdatedDate() + ", hashCode()=" + hashCode()
				+ ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
	
}
