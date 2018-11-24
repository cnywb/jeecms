package com.jeecms.cms.entity.main.base;

import java.io.Serializable;
import java.util.Date;


/**
 * This is an object that contains data related to the jc_user_ext table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_user_ext"
 */

public abstract class BaseCmsUserExt  implements Serializable {

	public static String REF = "CmsUserExt";
	public static String PROP_MSN = "msn";
	public static String PROP_BIRTHDAY = "birthday";
	public static String PROP_GENDER = "gender";
	public static String PROP_MOBILE = "mobile";
	public static String PROP_COMEFROM = "comefrom";
	public static String PROP_USER = "user";
	public static String PROP_INTRO = "intro";
	public static String PROP_REALNAME = "realname";
	public static String PROP_QQ = "qq";
	public static String PROP_ID = "id";
	public static String PROP_PHONE = "phone";


	// constructors
	public BaseCmsUserExt () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsUserExt (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String realname;
	private java.lang.Boolean gender;
	private java.util.Date birthday;
	private java.lang.String intro;
	private java.lang.String comefrom;
	private java.lang.String qq;
	private java.lang.String msn;
	private java.lang.String phone;
	private java.lang.String mobile;
	private java.lang.String userImg;
	private java.lang.String userSignature;
	
	
	
	private String drivingLicense;//驾驶证号
	
	public String getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}



	private String email;//电子邮箱,实际字段在 CmsUser 表在jc_user
	
	private String province;//车主所在省
	
	private String city;//车主所在市
	
	private String county;//车主所在县
	
	private Date carBuyingDate;//购车日期
	
	private String carBuyingDealer;//购车经销商
	
	private String carRepairDealer;//维修经销商
	
	private String carModel;//车型
	
	private String carStyle;//车款
	
	private String carColor;//车色
	
    private String buyingDealerProvince;//购车经销商所在省
	
	private String buyingDealerCity;
	
	private String repairDealerProvince;//维修经销商所在省
		
	private String repairDealerCity;
		
	
		
	
	
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Date getCarBuyingDate() {
		return carBuyingDate;
	}

	public void setCarBuyingDate(Date carBuyingDate) {
		this.carBuyingDate = carBuyingDate;
	}

	public String getCarBuyingDealer() {
		return carBuyingDealer;
	}

	public void setCarBuyingDealer(String carBuyingDealer) {
		this.carBuyingDealer = carBuyingDealer;
	}

	public String getCarRepairDealer() {
		return carRepairDealer;
	}

	public void setCarRepairDealer(String carRepairDealer) {
		this.carRepairDealer = carRepairDealer;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarStyle() {
		return carStyle;
	}

	public void setCarStyle(String carStyle) {
		this.carStyle = carStyle;
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public String getBuyingDealerProvince() {
		return buyingDealerProvince;
	}

	public void setBuyingDealerProvince(String buyingDealerProvince) {
		this.buyingDealerProvince = buyingDealerProvince;
	}

	public String getBuyingDealerCity() {
		return buyingDealerCity;
	}

	public void setBuyingDealerCity(String buyingDealerCity) {
		this.buyingDealerCity = buyingDealerCity;
	}

	

	public String getRepairDealerProvince() {
		return repairDealerProvince;
	}

	public void setRepairDealerProvince(String repairDealerProvince) {
		this.repairDealerProvince = repairDealerProvince;
	}

	public String getRepairDealerCity() {
		return repairDealerCity;
	}

	public void setRepairDealerCity(String repairDealerCity) {
		this.repairDealerCity = repairDealerCity;
	}

	



	// one to one
	private com.jeecms.cms.entity.main.CmsUser user;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="foreign"
     *  column="user_id"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: realname
	 */
	public java.lang.String getRealname () {
		return realname;
	}

	/**
	 * Set the value related to the column: realname
	 * @param realname the realname value
	 */
	public void setRealname (java.lang.String realname) {
		this.realname = realname;
	}


	/**
	 * Return the value associated with the column: gender
	 */
	public java.lang.Boolean getGender () {
		return gender;
	}

	/**
	 * Set the value related to the column: gender
	 * @param gender the gender value
	 */
	public void setGender (java.lang.Boolean gender) {
		this.gender = gender;
	}


	/**
	 * Return the value associated with the column: birthday
	 */
	public java.util.Date getBirthday () {
		return birthday;
	}

	/**
	 * Set the value related to the column: birthday
	 * @param birthday the birthday value
	 */
	public void setBirthday (java.util.Date birthday) {
		this.birthday = birthday;
	}


	/**
	 * Return the value associated with the column: intro
	 */
	public java.lang.String getIntro () {
		return intro;
	}

	/**
	 * Set the value related to the column: intro
	 * @param intro the intro value
	 */
	public void setIntro (java.lang.String intro) {
		this.intro = intro;
	}


	/**
	 * Return the value associated with the column: comefrom
	 */
	public java.lang.String getComefrom () {
		return comefrom;
	}

	/**
	 * Set the value related to the column: comefrom
	 * @param comefrom the comefrom value
	 */
	public void setComefrom (java.lang.String comefrom) {
		this.comefrom = comefrom;
	}


	/**
	 * Return the value associated with the column: qq
	 */
	public java.lang.String getQq () {
		return qq;
	}

	/**
	 * Set the value related to the column: qq
	 * @param qq the qq value
	 */
	public void setQq (java.lang.String qq) {
		this.qq = qq;
	}


	/**
	 * Return the value associated with the column: msn
	 */
	public java.lang.String getMsn () {
		return msn;
	}

	/**
	 * Set the value related to the column: msn
	 * @param msn the msn value
	 */
	public void setMsn (java.lang.String msn) {
		this.msn = msn;
	}


	/**
	 * Return the value associated with the column: phone
	 */
	public java.lang.String getPhone () {
		return phone;
	}

	/**
	 * Set the value related to the column: phone
	 * @param phone the phone value
	 */
	public void setPhone (java.lang.String phone) {
		this.phone = phone;
	}


	/**
	 * Return the value associated with the column: mobile
	 */
	public java.lang.String getMobile () {
		return mobile;
	}

	/**
	 * Set the value related to the column: mobile
	 * @param mobile the mobile value
	 */
	public void setMobile (java.lang.String mobile) {
		this.mobile = mobile;
	}
	public java.lang.String getUserImg() {
		return userImg;
	}

	public void setUserImg(java.lang.String userImg) {
		this.userImg = userImg;
	}

	public java.lang.String getUserSignature() {
		return userSignature;
	}

	public void setUserSignature(java.lang.String userSignature) {
		this.userSignature = userSignature;
	}


	/**
	 * Return the value associated with the column: user
	 */
	public com.jeecms.cms.entity.main.CmsUser getUser () {
		return user;
	}

	/**
	 * Set the value related to the column: user
	 * @param user the user value
	 */
	public void setUser (com.jeecms.cms.entity.main.CmsUser user) {
		this.user = user;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.CmsUserExt)) return false;
		else {
			com.jeecms.cms.entity.main.CmsUserExt cmsUserExt = (com.jeecms.cms.entity.main.CmsUserExt) obj;
			if (null == this.getId() || null == cmsUserExt.getId()) return false;
			else return (this.getId().equals(cmsUserExt.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}