/**
 * 
 */
package com.jeecms.point.entity.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jeecms.point.entity.PointOrderDetails;

/**
 * @author wanglijun
 *
 */
public class BasePointOrder extends BasePoint {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3451597404871312614L;
	
	private Long    userId;
	
	private Long 	totalPointNum;
	
	private Date 	orderDate;
	
	private Integer status;
	
	private Integer type;
	
	private String  memo;
	
	private Long    num;
	
	private Date    payoutDate;
	/**发货日期*/
	private Date sendDate;
	/**渠道*/
	private Integer channel;
	/**订单明细*/
	private Set<PointOrderDetails> pointOrderDetails=new HashSet<PointOrderDetails>(0);
	/**订单明细*/
	private List<PointOrderDetails> orderDetails=new ArrayList<PointOrderDetails>();

	/**
	 * 默认函数
	 */
	public BasePointOrder() {
		super();
	}

	/**
	 * @param id
	 */
	public BasePointOrder(Long id) {
		super(id);
	 
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
	 */
	public BasePointOrder(Long id, Long userId, Long totalPointNum,
			Date orderDate, Integer status, Integer type, String memo,
			Long num, Date payoutDate) {
		super(id);
		this.userId = userId;
		this.totalPointNum = totalPointNum;
		this.orderDate = orderDate;
		this.status = status;
		this.type = type;
		this.memo = memo;
		this.num = num;
		this.payoutDate = payoutDate;
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
	 * @return the pointOrderDetails
	 */
	public Set<PointOrderDetails> getPointOrderDetails() {
		return pointOrderDetails;
	}

	/**
	 * @param pointOrderDetails the pointOrderDetails to set
	 */
	public void setPointOrderDetails(Set<PointOrderDetails> pointOrderDetails) {
		this.pointOrderDetails = pointOrderDetails;
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

	/**
	 * @return the orderDetails
	 */
	public List<PointOrderDetails> getOrderDetails() {
		return orderDetails;
	}

	/**
	 * @param orderDetails the orderDetails to set
	 */
	public void setOrderDetails(List<PointOrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	
	public void addOrderDetails(PointOrderDetails orderDetail){
		this.orderDetails.add(orderDetail);
	}
}
