/**
 * 
 */
package com.jeecms.point.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 刮刮奖抽奖结果实体类
 * @author wanglijun
 *
 */
public class GuaGuaLotteryResult implements Serializable{

	
	 
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -6675115684483350768L;
	/**活动代码*/
	private String activityCode;
	/**是否中奖*/
	private boolean won;	
	/**中奖奖品ID*/
	private Long productId;
	/**中奖奖品代码*/
	private String productNo=StringUtils.EMPTY;
	/**中奖奖品名称*/
	private String productTitle=StringUtils.EMPTY;;
	/**中图片*/
	private String imageMiddleURL=StringUtils.EMPTY;;
	/**大小图*/
	private String imageLargeURL=StringUtils.EMPTY;
	/**消息代码，消息文本*/
	private Map<String,String> messages=new HashMap<String, String>();
	/***
	 * 代码
	 * @param activityCode 活动代码
	 * @param won 是否列表
	 */ 
	public GuaGuaLotteryResult(String activityCode, boolean won){
		this.activityCode=activityCode;
		this.won=won;
	}
	
	
	/**
	 * @param activityCode
	 * @param won
	 * @param messages
	 */
	public GuaGuaLotteryResult(String activityCode, boolean won,
			Map<String, String> messages) {
		this(activityCode,won);
		this.messages = messages;
	}
	
	
	
	/**
	 * @param activityCode
	 * @param won
	 * @param productId
	 * @param productNo
	 * @param productTitle
	 * @param imageLargeURL
	 * @param messages
	 */
	public GuaGuaLotteryResult(String activityCode, boolean won,
			Long productId, String productNo, String productTitle,
			String imageLargeURL, Map<String, String> messages) {
		this(activityCode,won,messages);
		this.productId = productId;
		this.productNo = productNo;
		this.productTitle = productTitle;
		this.imageLargeURL = imageLargeURL;
	 
	}



	/**
	 * @return the activityCode
	 */
	public String getActivityCode() {
		return activityCode;
	}
	/**
	 * @param activityCode the activityCode to set
	 */
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	/**
	 * @return the won
	 */
	public boolean isWon() {
		return won;
	}
	/**
	 * @param won the won to set
	 */
	public void setWon(boolean won) {
		this.won = won;
	}
	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	/**
	 * @return the productNo
	 */
	public String getProductNo() {
		return productNo;
	}
	/**
	 * @param productNo the productNo to set
	 */
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
 
	
	
	/**
	 * @return the productTitle
	 */
	public String getProductTitle() {
		return productTitle;
	}


	/**
	 * @param productTitle the productTitle to set
	 */
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	 
	/**
	 * @return the imageMiddleURL
	 */
	public String getImageMiddleURL() {
		return imageMiddleURL;
	}


	/**
	 * @param imageMiddleURL the imageMiddleURL to set
	 */
	public void setImageMiddleURL(String imageMiddleURL) {
		this.imageMiddleURL = imageMiddleURL;
	}


	/**
	 * @return the imageLargeURL
	 */
	public String getImageLargeURL() {
		return imageLargeURL;
	}


	/**
	 * @param imageLargeURL the imageLargeURL to set
	 */
	public void setImageLargeURL(String imageLargeURL) {
		this.imageLargeURL = imageLargeURL;
	}


	/**
	 * @return the messages
	 */
	public Map<String, String> getMessages() {
		return messages;
	}
	/**
	 * @param messages the messages to set
	 */
	public void setMessages(Map<String, String> messages) {
		this.messages = messages;
	}
	
	/***
	 * 
	 * @param msgCode 消息代码
	 * @param message 消息Body
	 */
	public void addMessage(String key,String message){
		if(this.messages==null){
			this.messages=new HashMap<String, String>();
		}
		this.messages.put(key, message);
	}
	
	/***
	 * 
	 * @param msgCode 消息代码
	 * @param message 消息Body
	 */
	public void addMessage(String key,String message,boolean won){
		if(this.messages==null){
			this.messages=new HashMap<String, String>();
		}
		this.messages.put(key, message);
		this.won=won;
	}
}
