/**
 * 
 */
package com.jeecms.point.entity;

import com.jeecms.point.entity.base.BasePointProduct;

/**
 * @author wanglijun
 *
 */
public class PointProduct extends BasePointProduct {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7836699968209738632L;

	/**
	 * 序列化
	 */
	public PointProduct() {
	   super();
	}

	/**
	 * @param id
	 */
	public PointProduct(Long id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PointProduct [getProductNo()=" + getProductNo()
				+ ", getCategoryId()=" + getCategoryId() + ", getTitle()="
				+ getTitle() + ", getSubTitle()=" + getSubTitle()
				+ ", getPrice()=" + getPrice() + ", getPointNum()="
				+ getPointNum() + ", getStatus()=" + getStatus()
				+ ", getChannel()=" + getChannel() + ", getMemo()=" + getMemo()
				+ ", getImageSmallURL()=" + getImageSmallURL()
				+ ", getImageMiddleURL()=" + getImageMiddleURL()
				+ ", getImageLargeURL()=" + getImageLargeURL()
				+ ", getProductCategory()=" + getProductCategory()
				+ ", getInfo()=" + getInfo() + ", getLotteryStockNum()="
				+ getLotteryStockNum() + ", getSeckillStockNum()="
				+ getSeckillStockNum() + ", getExchangeStockNum()="
				+ getExchangeStockNum() + ", toString()=" + super.toString()
				+ ", getId()=" + getId() + ", getCreatedId()=" + getCreatedId()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedId()=" + getUpdatedId() + ", getUpdatedDate()="
				+ getUpdatedDate() + ", hashCode()=" + hashCode()
				+ ", getClass()=" + getClass() + "]";
	}

	

	

	

}
