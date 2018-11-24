package com.jeecms.point.entity;

import java.util.Set;

import com.jeecms.point.entity.base.BasePointProduct;
import com.jeecms.point.entity.base.BaseProductCategory;

public class ProductCategory extends BaseProductCategory {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 3128531240835427971L;

	public ProductCategory() {
		super();
	}

	public ProductCategory(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param parentId
	 * @param categoryName
	 * @param showOrder
	 * @param memo
	 * @param pointProducts
	 */
	public ProductCategory(Long id, Long parentId, String categoryName,
			Integer showOrder, String memo, Set<BasePointProduct> pointProducts) {
		super(id, parentId, categoryName, showOrder, memo, pointProducts);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductCategory [getId()=" + getId() + ", getParentId()="
				+ getParentId() + ", getCategoryName()=" + getCategoryName()
				+ ", getShowOrder()=" + getShowOrder() + ", getMemo()="
				+ getMemo() + ", getPointProducts()=" + getPointProducts()
				+ ", getCreatedId()=" + getCreatedId() + ", getCreatedDate()="
				+ getCreatedDate() + ", getUpdatedId()=" + getUpdatedId()
				+ ", getUpdatedDate()=" + getUpdatedDate() + ", hashCode()="
				+ hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
	
	

}
