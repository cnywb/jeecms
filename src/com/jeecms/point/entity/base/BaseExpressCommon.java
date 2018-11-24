/**
 * 
 */
package com.jeecms.point.entity.base;

/**
 * 
 * 用户常用配送地址信息表
 * @author wanglijun
 */
public class BaseExpressCommon  extends BasePoint{

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
	/**是否默认地址*/
	private Integer isDefault;
	/**电话*/
	private String phoneNo;
	/**移动电话*/
	private String mobilePhoneNo;
	/**收货人*/
	private String sendee;

	/**
	 *默认函数 
	 */
	public BaseExpressCommon() {
		  super();
	}

	/**
	 * @param id
	 */
	public BaseExpressCommon(Long id) {
		super(id);
	}
	
	
	

	/**
	 * @param id
	 * @param userId
	 * @param province
	 * @param city
	 * @param disrict
	 * @param address
	 * @param zipCode
	 * @param isDefault
	 * @param phoneNo
	 * @param mobilePhoneNo
	 */
	public BaseExpressCommon(Long id, Long userId, String province,
			String city, String disrict, String address, String zipCode,
			Integer isDefault, String phoneNo, String mobilePhoneNo,String sendee) {
		super(id);
		this.userId = userId;
		this.province = province;
		this.city = city;
		this.disrict = disrict;
		this.address = address;
		this.zipCode = zipCode;
		this.isDefault = isDefault;
		this.phoneNo = phoneNo;
		this.mobilePhoneNo = mobilePhoneNo;
		this.sendee=sendee;
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
	 * @return the isDefault
	 */
	public Integer getIsDefault() {
		return isDefault;
	}

	/**
	 * @param isDefault the isDefault to set
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
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
}
