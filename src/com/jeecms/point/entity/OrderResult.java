/**
 * 
 */
package com.jeecms.point.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单提交结果
 * @author wanglijun
 *
 */
public class OrderResult {
	/***未登录*/
	public static final  int MSGCODE_NO_LOGIN=1;
	/**未认证车主*/
	public static final int MEGCODE_NO_AUTH=2;
	/**积分不足*/
	public static final int MEGCODE_NO_POINT=3;
	/**失败*/
	public static final int MEGCODE_FAIL=9;
	/**成功*/
	public static final int MEGCODE_SUCCESS=0;
	/**库存不足*/
	public static final int MESGCODE_NO_STOCK=4;
	/**错误消息*/
	private static Map<Integer,String> messages=new HashMap<Integer, String>();
	
	static{
		messages.put(0,"恭喜您，兑礼成功!<br/>礼品将在7工作日内寄出!<br/>请确认您礼品寄送地址");
		messages.put(1,"您未登录，点击\"<a href=\"/login.jspx?returnUrl=/points/product/index.jhtml\">用户登录</a>\",将跳转到用户页面'");
		messages.put(2,"您目前未认证车主身份，点击\"<a href=\"$/czrz/index.htm\">车主认证</a>\",将跳转到车主认证页面");
		messages.put(3,"您目前积分不足，点击\"<a href=\"/points/rule/index.jhtml\">积分获取</a>\",将告诉您如何获取积分");
		messages.put(4,"您兑换礼品库存不足，请选择其它礼品");
		messages.put(9,"兑礼失败，请联系网站管理员");
	}
	
	 
	/**省*/
	private String province;
	/**市*/
	private String city;
	/**县区*/
	private String county;
	/**地址*/
	private String address;
	/**邮编*/
	private String zipCode;
	/**电话*/
	private String phoneNo;
	/**移动电话*/
	private String mobilePhoneNo;
	/** 备注 */
	private String memo;
	/**收货人*/
	private String sendee;
	/**订单号*/
	private Long orderId;
	/**提示信息*/
	private String message;
	/**提示信息*/
	private Integer messageCode;
	
	public OrderResult() {
		 super();
	}

	/**
	 * @param message
	 * @param messageCode
	 */
	public OrderResult(Integer messageCode) {
		this.message=messages.get(messageCode);
		this.messageCode = messageCode;
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
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
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
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the messageCode
	 */
	public Integer getMessageCode() {
		return messageCode;
	}

	/**
	 * @param messageCode the messageCode to set
	 */
	public void setMessageCode(Integer messageCode) {
		this.messageCode = messageCode;
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
