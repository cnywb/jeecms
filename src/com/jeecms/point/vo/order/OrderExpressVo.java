/**
 * 
 */
package com.jeecms.point.vo.order;

/**
 * 订单快递信息
 * @author wanglijun
 *
 */
public class OrderExpressVo {
	/***
	 * 快递单号
	 **/
	private String expressNo;
	/***
	 * 快递公司
	 **/
	private String expressCompany;
	/***
	 * 快递信息ID
	 **/
	private Long expressId;
	/***
	 * 订单号
	 **/
	private Long id;
	
	public OrderExpressVo() {
		super();
	}

	/**
	 * @return the expressNo
	 */
	public String getExpressNo() {
		return expressNo;
	}

	/**
	 * @param expressNo the expressNo to set
	 */
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	

	/**
	 * @return the expressCompany
	 */
	public String getExpressCompany() {
		return expressCompany;
	}

	/**
	 * @param expressCompany the expressCompany to set
	 */
	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}

	/**
	 * @return the expressId
	 */
	public Long getExpressId() {
		return expressId;
	}

	/**
	 * @param expressId the expressId to set
	 */
	public void setExpressId(Long expressId) {
		this.expressId = expressId;
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
}
