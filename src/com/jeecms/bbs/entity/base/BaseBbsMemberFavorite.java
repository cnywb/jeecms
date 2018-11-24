/**
 * 
 */
package com.jeecms.bbs.entity.base;

import java.util.Date;

import com.jeecms.bbs.entity.BbsMemberFavorite;

/**
 * @author xuw
 *
 */
public class BaseBbsMemberFavorite  {
	
	public static String REF=BbsMemberFavorite.class.getName();
	
	public static String PROP_UUID = "uuid";
	
	public static String PROP_TYPE = "type";
 
	public static String PROP_CREATE_DATE = "createDate";
	
	public static String PROP_CREATE_USER= "createUser";
	
	public static String PROP_UPDATE_DATE = "updateDate";
	
	public static String PROP_UPDATE_USER= "updateUser";
	
	public static String PROP_ID = "id";
	
	public static String PROP_TOPIC_ID="topicId";
	
	public static String PROP_BORAD_ID="boardId";
	
	public static String PROP_URL="url";
	
	
	private Long  id;
	
	private Integer uuid;
	
	private Integer  type;
	
	private Integer  topicId;
	
	private Integer boardId;
	
	private String url;
	
	
	private Date createDate;
	
	private Integer  createUser;
	
	private Date  updateDate;
	
	private Integer updateUser; 
	

	
	
	
	
	public BaseBbsMemberFavorite() {
		super();
	}
	
	
	
	public BaseBbsMemberFavorite(Long id, Integer uuid, Integer type,
			Integer topicId, Integer boardId, String url, Date createDate,
			Integer createUser, Date updateDate, Integer updateUser) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.type = type;
		this.topicId = topicId;
		this.boardId = boardId;
		this.url = url;
		this.createDate = createDate;
		this.createUser = createUser;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
	}



	public Long getId() {
		return id;
	} 
	public void setId(Long id) {
		this.id = id;
	} 
	public Integer getUuid() {
		return uuid;
	}

 

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
 
	public Integer getType() {
		return type;
	}




	public void setType(Integer type) {
		this.type = type;
	}




	public Integer getTopicId() {
		return topicId;
	}




	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}




	public Integer getBoardId() {
		return boardId;
	}




	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}




	public String getUrl() {
		return url;
	}




	public void setUrl(String url) {
		this.url = url;
	}




	public Date getCreateDate() {
		return createDate;
	}




	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}




	public Integer getCreateUser() {
		return createUser;
	}




	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}




	public Date getUpdateDate() {
		return updateDate;
	}




	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}




	public Integer getUpdateUser() {
		return updateUser;
	}




	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}


	protected void initialize () {}

	@Override
	public String toString() {
		return "BbsMemberFavorite [id=" + id + ", uuid=" + uuid + ", type="
				+ type + ", topicId=" + topicId + ", boardId=" + boardId
				+ ", url=" + url + ", createDate=" + createDate
				+ ", createUser=" + createUser + ", updateDate=" + updateDate
				+ ", updateUser=" + updateUser + "]";
	}
}
