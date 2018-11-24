/**
 * 
 */
package com.jeecms.point.entity.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 积分产品分类
 * @author wanglijun
 *
 */
public class BaseProductCategory extends BasePoint implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 5837086319975118655L;
	
	public static String REF = "BaseProductCategory";

	public static String PROP_ID = "id";

	public static String PROP_USER_ID = "parentId";

	public static String PROP_POINT_NUM = "categoryName";

	public static String PROP_PAYOUT_SHOWORDER= "showOrder"; 

	public static String PROP_POINT_MEMO = "memo";
	
	/**ID*/
	private Long id;
	/**parentId*/
	private Long  parentId;
	/**分类名称*/
	private String categoryName;
	/**显示顺序*/
	private Integer showOrder;
	/**备注信息*/
	private String memo;	 
	/**产品对象*/
	private Set<BasePointProduct> pointProducts=new HashSet<BasePointProduct>(0);
 
	public BaseProductCategory() {
		 super();
	}

	
	
	
	/**
	 * @param id
	 */
	public BaseProductCategory(Long id) {
		this.id = id;
	}
 

 




	/**
	 * @param id
	 * @param parentId
	 * @param categoryName
	 * @param showOrder
	 * @param memo
	 * @param pointProducts
	 */
	public BaseProductCategory(Long id, Long parentId, String categoryName,
			Integer showOrder, String memo,
			Set<BasePointProduct> pointProducts) {
		this.id = id;
		this.parentId = parentId;
		this.categoryName = categoryName;
		this.showOrder = showOrder;
		this.memo = memo;
 
		this.pointProducts = pointProducts;
	}




	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the showOrder
	 */
	public Integer getShowOrder() {
		return showOrder;
	}

	/**
	 * @param showOrder the showOrder to set
	 */
	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}



 



	/**
	 * @return the pointProducts
	 */
	public Set<BasePointProduct> getPointProducts() {
		return pointProducts;
	}




	/**
	 * @param pointProducts the pointProducts to set
	 */
	public void setPointProducts(Set<BasePointProduct> pointProducts) {
		this.pointProducts = pointProducts;
	}
	
	
}
