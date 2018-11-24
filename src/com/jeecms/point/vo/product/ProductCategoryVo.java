/**
 * 
 */
package com.jeecms.point.vo.product;


/**
 * 产品分类VO
 * @author wanglijun
 *
 */
public class ProductCategoryVo{
	/**ID*/
	private Long id;
	/**分类名称*/
	private String categoryName;
	/**显示顺序*/
	private Integer showOrder;
	/**备注信息*/
	private String memo;
	 
	
	public ProductCategoryVo() {
		 super();
	}

	/**
	 * @param id
	 * @param categoryName
	 * @param showOrder
	 * @param memo
	 */
	public ProductCategoryVo(Long id, String categoryName, Integer showOrder,
			String memo) {
		this.id = id;
		this.categoryName = categoryName;
		this.showOrder = showOrder;
		this.memo = memo;
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

 
	
	
}
