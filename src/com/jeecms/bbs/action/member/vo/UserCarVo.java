/**
 * 
 */
package com.jeecms.bbs.action.member.vo;


/**
 * @author xuw
 *
 */
public class UserCarVo {
	/**编号*/
	private Long id;
	/**用户ID*/
	private Integer uuid;
	/**车型*/
	private String vproduct;
	/**购买时间*/
	private String purchasedDate;
	/**购买时间*/
	private String purchasedFullDate;
	/**中文购买时间*/
	private String purchasedDateZh;
	/**爱车图片*/
	private String ocarImg;
	/**carId*/
	private String carId;
	/**车型代码*/
	private String vproductCode;
	/**用户名*/
	private String username;
	
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
	 * @return the uuid
	 */
	public Integer getUuid() {
		return uuid;
	}
	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	/**
	 * @return the vproduct
	 */
	public String getVproduct() {
		return vproduct;
	}
	/**
	 * @param vproduct the vproduct to set
	 */
	public void setVproduct(String vproduct) {
		this.vproduct = vproduct;
	}
	/**
	 * @return the purchasedDate
	 */
	public String getPurchasedDate() {
		return purchasedDate;
	}
	/**
	 * @param purchasedDate the purchasedDate to set
	 */
	public void setPurchasedDate(String purchasedDate) {
		this.purchasedDate = purchasedDate;
	}
	/**
	 * @return the ocarImg
	 */
	public String getOcarImg() {
		return ocarImg;
	}
	/**
	 * @param ocarImg the ocarImg to set
	 */
	public void setOcarImg(String ocarImg) {
		this.ocarImg = ocarImg;
	}
	/**
	 * @return the carId
	 */
	public String getCarId() {
		return carId;
	}
	/**
	 * @param carId the carId to set
	 */
	public void setCarId(String carId) {
		this.carId = carId;
	}
	/**
	 * @return the vproductCode
	 */
	public String getVproductCode() {
		return vproductCode;
	}
	/**
	 * @param vproductCode the vproductCode to set
	 */
	public void setVproductCode(String vproductCode) {
		this.vproductCode = vproductCode;
	}
	
	/**
	 * @return the purchasedDateZh
	 */
	public String getPurchasedDateZh() {
		return purchasedDateZh;
	}
	/**
	 * @param purchasedDateZh the purchasedDateZh to set
	 */
	public void setPurchasedDateZh(String purchasedDateZh) {
		 this.purchasedDateZh = purchasedDateZh;
	}
	
	
	
	/**
	 * @return the purchasedFullDate
	 */
	public String getPurchasedFullDate() {
		return purchasedFullDate;
	}
	/**
	 * @param purchasedFullDate the purchasedFullDate to set
	 */
	public void setPurchasedFullDate(String purchasedFullDate) {
		this.purchasedFullDate = purchasedFullDate;
	}
	
	
	
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserCarVo [id=" + id + ", uuid=" + uuid + ", vproduct="
				+ vproduct + ", purchasedDate=" + purchasedDate
				+ ", purchasedFullDate=" + purchasedFullDate
				+ ", purchasedDateZh=" + purchasedDateZh + ", ocarImg="
				+ ocarImg + ", carId=" + carId + ", vproductCode="
				+ vproductCode + ", username=" + username + "]";
	}
}
