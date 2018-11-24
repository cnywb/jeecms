/**
 * 
 */
package com.jeecms.point.entity.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 基础类
 * @author wanglijun
 *
 */
public class BasePoint implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 2326365316605339169L;
	
	public static String PROP_ID = "id";
	
	public static String PROP_CREATED_ID="createdId";
	
	public static String PROP_CREATED_DATE="createdDate";
	
	public static String PROP_UPDATEDID="updatedId";
	
	public static String PROP_UPDATED_DATE="updatedDate";
	/**ID*/
	private Long id;
	/**创建人*/
	private Long createdId;
	/**创建时间*/
	private Date createdDate;
	/**更新人*/
	private Long updatedId;
	/**更新时间*/
	private Date updatedDate;
	
	/**
	 * 默认构造函数
	 */
	public BasePoint() {
		 super();
	}
	
	
	

	/**
	 * @param id
	 */
	public BasePoint(Long id) {
		super();
		this.id = id;
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
	 * @return the createdId
	 */
	public Long getCreatedId() {
		return createdId;
	}




	/**
	 * @param createdId the createdId to set
	 */
	public void setCreatedId(Long createdId) {
		this.createdId = createdId;
	}




	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}




	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}




	/**
	 * @return the updatedId
	 */
	public Long getUpdatedId() {
		return updatedId;
	}




	/**
	 * @param updatedId the updatedId to set
	 */
	public void setUpdatedId(Long updatedId) {
		this.updatedId = updatedId;
	}




	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}




	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
		
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;	 
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasePoint other = (BasePoint) obj;
		 
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
