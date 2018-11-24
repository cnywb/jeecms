/**
 * 
 */
package com.jeecms.point.vo.point;

/**
 * 
 * @author wanglijun
 */
public class AddPointNumVo {
	/**加分类型*/
	private Integer addType;
	/***用户名*/
	private String username;
	/** 帖子ID* */
	private Long topicId;	
	/**积分数量*/
	private Long pointNum;
	/**积分备注*/
	private String memo;
	/***
	 * 默认
	 */
	public AddPointNumVo() {
	  super();
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
	 * @return the topicId
	 */
	public Long getTopicId() {
		return topicId;
	}
	/**
	 * @param topicId the topicId to set
	 */
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	/**
	 * @return the pointNum
	 */
	public Long getPointNum() {
		return pointNum;
	}
	/**
	 * @param pointNum the pointNum to set
	 */
	public void setPointNum(Long pointNum) {
		this.pointNum = pointNum;
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
	 * @return the addType
	 */
	public Integer getAddType() {
		return addType;
	}
	/**
	 * @param addType the addType to set
	 */
	public void setAddType(Integer addType) {
		this.addType = addType;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AddPointNumVo [username=" + username + ", topicId=" + topicId
				+ ", pointNum=" + pointNum + ", memo=" + memo + "]";
	}
	
	
}
