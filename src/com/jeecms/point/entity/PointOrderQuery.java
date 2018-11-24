/**
 * 
 */
package com.jeecms.point.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wanglijun
 *
 */
public class PointOrderQuery  implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -6012207380877638190L;
	
	private Long id;

	private Long    userId;
	
	private Long 	totalPointNum;
	
	private Date 	orderDate;
	
	private Integer status;
	
	private Integer type;
	
	private String  memo;
	
	private Long    num;
	
	private Date    payoutDate;
	/**电话*/
	private String phoneNo;
	/**移动电话*/
	private String mobilePhoneNo;
	/**收货人*/
	private String sendee;
	/**用户名*/
	private String username;
	/**发货日期*/
	private Date sendDate;
	/**渠道*/
	private Integer channel;
	
	public PointOrderQuery() {
		 super();
	}



	


	/**
	 * @param id
	 * @param phoneNo
	 * @param mobilePhoneNo
	 * @param sendee
	 * @param username
	 */
	public PointOrderQuery(Long id, String phoneNo, String mobilePhoneNo,
			String sendee, String username) {
		this.id = id;
		this.phoneNo = phoneNo;
		this.mobilePhoneNo = mobilePhoneNo;
		this.sendee = sendee;
		this.username = username;
	}






	/**
	 * @param id
	 * @param userId
	 * @param totalPointNum
	 * @param orderDate
	 * @param status
	 * @param type
	 * @param memo
	 * @param num
	 * @param payoutDate
	 * @param phoneNo
	 * @param mobilePhoneNo
	 * @param sendee
	 * @param username
	 */
	public PointOrderQuery(Long id, Long userId, Long totalPointNum,
			Date orderDate, Integer status, Integer type, String memo,
			Long num, Date payoutDate, String phoneNo, String mobilePhoneNo,
			String sendee, String username,Date sendDate,Integer channel) {
		this.id = id;
		this.userId = userId;
		this.totalPointNum = totalPointNum;
		this.orderDate = orderDate;
		this.status = status;
		this.type = type;
		this.memo = memo;
		this.num = num;
		this.payoutDate = payoutDate;
		this.phoneNo = phoneNo;
		this.mobilePhoneNo = mobilePhoneNo;
		this.sendee = sendee;
		this.username = username;
		this.sendDate=sendDate;
		this.channel=channel;
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
	 * @return the totalPointNum
	 */
	public Long getTotalPointNum() {
		return totalPointNum;
	}


	/**
	 * @param totalPointNum the totalPointNum to set
	 */
	public void setTotalPointNum(Long totalPointNum) {
		this.totalPointNum = totalPointNum;
	}


	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}


	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}


	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
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
	 * @return the num
	 */
	public Long getNum() {
		return num;
	}


	/**
	 * @param num the num to set
	 */
	public void setNum(Long num) {
		this.num = num;
	}


	/**
	 * @return the payoutDate
	 */
	public Date getPayoutDate() {
		return payoutDate;
	}


	/**
	 * @param payoutDate the payoutDate to set
	 */
	public void setPayoutDate(Date payoutDate) {
		this.payoutDate = payoutDate;
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






	/**
	 * @return the sendDate
	 */
	public Date getSendDate() {
		return sendDate;
	}






	/**
	 * @param sendDate the sendDate to set
	 */
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}






	/**
	 * @return the channel
	 */
	public Integer getChannel() {
		return channel;
	}






	/**
	 * @param channel the channel to set
	 */
	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	
	
}
