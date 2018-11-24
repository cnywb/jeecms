/**
 * 
 */
package com.jeecms.bbs.entity.base;

import java.util.Date;

import com.jeecms.bbs.entity.BbsMemberCar;

/**
 * @author xuw
 * 
 */
public class BaseBbsMemberCar {

	public static String REF = BbsMemberCar.class.getName();

	public static String PROP_UUID = "uuid";

	public static String PROP_VPRODUCT= "vproduct";

	public static String PROP_CREATE_DATE = "createDate";

	public static String PROP_CREATE_USER = "createUser";

	public static String PROP_UPDATE_DATE = "updateDate";

	public static String PROP_UPDATE_USER = "updateUser";

	public static String PROP_ID = "id";

	public static String PROP_PURCHASE_DATE= "purchasedDate";

	public static String PROP_OCARIMG = "ocarImg";

	public static String PROP_URL = "url";
	
	public static String PROP_CAR_ID="carId";
	
	/**编号*/
	private Long id;
	/**用户ID*/
	private Integer uuid;
	/**车型*/
	private String vproduct;
	/**购买时间*/
	private Date purchasedDate;
	/**爱车图片*/
	private String ocarImg; 
	/**爱车ID*/
	private String carId;
	
	private Date createDate;

	private Integer createUser;

	private Date updateDate;

	private Integer updateUser;

	public BaseBbsMemberCar() {
		super();
		this.initialize();
	}

	public BaseBbsMemberCar(Long id, Integer uuid, String vproduct,
			Date purchasedDate, String ocarImg, Date createDate,
			Integer createUser, Date updateDate, Integer updateUser) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.vproduct = vproduct;
		this.purchasedDate = purchasedDate;
		this.ocarImg = ocarImg;
		this.createDate = createDate;
		this.createUser = createUser;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		this.initialize();
	}
	
	protected void initialize () {}

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

	public String getVproduct() {
		return vproduct;
	}

	public void setVproduct(String vproduct) {
		this.vproduct = vproduct;
	}

	public Date getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(Date purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public String getOcarImg() {
		return ocarImg;
	}

	public void setOcarImg(String ocarImg) {
		this.ocarImg = ocarImg;
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

	public BaseBbsMemberCar(Long id, Integer uuid, String vproduct,
			Date purchasedDate, String ocarImg, String carId, Date createDate,
			Integer createUser, Date updateDate, Integer updateUser) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.vproduct = vproduct;
		this.purchasedDate = purchasedDate;
		this.ocarImg = ocarImg;
		this.carId = carId;
		this.createDate = createDate;
		this.createUser = createUser;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BaseBbsMemberCar [id=" + id + ", uuid=" + uuid + ", vproduct="
				+ vproduct + ", purchasedDate=" + purchasedDate + ", ocarImg="
				+ ocarImg + ", carId=" + carId + ", createDate=" + createDate
				+ ", createUser=" + createUser + ", updateDate=" + updateDate
				+ ", updateUser=" + updateUser + "]";
	}
	
	
}
