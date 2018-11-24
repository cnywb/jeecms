/**
 * 
 */
package com.jeecms.bbs.entity.base;

import java.io.Serializable;
import java.util.Date;

import com.jeecms.bbs.entity.BbsMemberCarFocus;

/**
 * @author xuw
 *
 */
public class BaseBbsMemberCarFocus  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5551301593343809358L;
	
	
	public static String REF=BbsMemberCarFocus.class.getName();
	
	public static String PROP_UUID = "uuid";
	
	public static String PROP_CAR_TYPE = "carType";
 
	public static String PROP_CREATE_DATE = "createDate";
	
	public static String PROP_CREATE_USER= "createUser";
	
	public static String PROP_UPDATE_DATE = "updateDate";
	
	public static String PROP_UPDATE_USER= "updateUser";
	
	public static String PROP_ID = "id";

	
	private Long id;
	
	private Integer uuid;
	
	private String carType;
	
	private Date createDate;
	
	private Integer  createUser;
	
	private Date  updateDate;
	
	private Integer updateUser;
	
	
	
	public BaseBbsMemberCarFocus() {
		super();
		initialize ();
	}
	
	
	

	public BaseBbsMemberCarFocus(Long id) {
		super();
		this.id = id;
		initialize ();
	}




	public BaseBbsMemberCarFocus(Long id, Integer uuid, String carType,
			Date createDate, Integer createUser, Date updateDate,
			Integer updateUser) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.carType = carType;
		this.createDate = createDate;
		this.createUser = createUser;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		initialize ();
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

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
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
	
	
	public String toString () {
		return super.toString();
	}
}	
