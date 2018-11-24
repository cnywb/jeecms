/**
 * 
 */
package com.jeecms.point.entity.base;

/**
 * 
 * 用户常用配送地址信息表
 * @author wanglijun
 */
public class BaseExpress  extends BasePoint{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2271307030251149025L;
	/**用户ID*/
	private Long userId;
	/**省*/
	private String province;
	/**市*/
	private String city;
	/**县区*/
	private String disrict;
	/**地址*/
	private String address;
	/**邮编*/
	private String zipCode;
	/**电话*/
	private String phoneNo;
	/**移动电话*/
	private String mobilePhoneNo;
	/**收货人*/
	private String sendee;
	/**订单号*/
	private Long orderId;
	/**快递号*/
	private String expressNo;
	/**快递公司*/
	private String expressCompany;
	/**备注*/
	private String memo;

	/**
	 *默认函数 
	 */
	public BaseExpress() {
		  super();
	}

	/**
	 * @param id
	 */
	public BaseExpress(Long id) {
		super(id);
	}
	
	
	
 

	/**
	 * @param userId
	 * @param province
	 * @param city
	 * @param disrict
	 * @param address
	 * @param zipCode
	 * @param phoneNo
	 * @param mobilePhoneNo
	 * @param sendee
	 * @param orderId
	 * @param expressNo
	 * @param expressCompany
	 */
	public BaseExpress(Long userId, String province, String city,
			String disrict, String address, String zipCode, String phoneNo,
			String mobilePhoneNo, String sendee, Long orderId,
			String expressNo, String expressCompany) {
		this.userId = userId;
		this.province = province;
		this.city = city;
		this.disrict = disrict;
		this.address = address;
		this.zipCode = zipCode;
		this.phoneNo = phoneNo;
		this.mobilePhoneNo = mobilePhoneNo;
		this.sendee = sendee;
		this.orderId = orderId;
		this.expressNo = expressNo;
		this.expressCompany = expressCompany;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the disrict
	 */
	public String getDisrict() {
		return disrict;
	}

	/**
	 * @param disrict the disrict to set
	 */
	public void setDisrict(String disrict) {
		this.disrict = disrict;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	
	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * @return the mobilePhoneNo
	 */
	public String getMobilePhoneNo() {
		return mobilePhoneNo;
	}

	/**
	 * @param mobilePhoneNo the mobilePhoneNo to set
	 */
	public void setMobilePhoneNo(String mobilePhoneNo) {
		this.mobilePhoneNo = mobilePhoneNo;
	}

	/**
	 * @return the sendee
	 */
	public String getSendee() {
		return sendee;
	}

	/**
	 * @param sendee the sendee to set
	 */
	public void setSendee(String sendee) {
		this.sendee = sendee;
	}

	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
