package com.jeecms.cms.entity.infocollection;

import java.util.Date;

public class PotentialCustomerInfo {

    private Long id;
	
	private String carOwnerName;//车主姓名
	
	private String carOwnerMobilePhoneNo;//车主手机

	private String carModel;//已购车型
	
	private String carOwnerProvince;//车主所在省
	
	private String carOwnerCity;//车主所在市
	
	private String carOwnerDealer;//购车经销商
	
	private String carOwnerBuyTime;//购车时间
	
	private String vin;//所购车vin码
	
	private String customerName;//潜客姓名
	
	private String customerMobilePhoneNo;//潜客手机
	
	private String customerGender;//潜客性别
	
	private String activityName;//活动名称
	
	private String customerProvince;//潜客所在省
	
	private String customerCity;//潜客所在城市
	
	private String channel;//渠道
	
	private String intentionalCarModel;//预计购买车型
	
	private Date intentionalBuyDate;//预计购车日期
	
	private Date createTime=new Date();
	    
    private Date updateTime=new Date();
    
    private String intentionalDealer;//预计购车经销商
    
    private String intentionalBuyDateRange;//预计购车日期范围
    
    private  String budget;//预算
    
    private String fileUrl;//文件路径
    
    private String wechatId;//微信Id

	public Long getId() {
		return id;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarOwnerName() {
		return carOwnerName;
	}

	public void setCarOwnerName(String carOwnerName) {
		this.carOwnerName = carOwnerName;
	}

	public String getCarOwnerMobilePhoneNo() {
		return carOwnerMobilePhoneNo;
	}

	public void setCarOwnerMobilePhoneNo(String carOwnerMobilePhoneNo) {
		this.carOwnerMobilePhoneNo = carOwnerMobilePhoneNo;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobilePhoneNo() {
		return customerMobilePhoneNo;
	}

	public void setCustomerMobilePhoneNo(String customerMobilePhoneNo) {
		this.customerMobilePhoneNo = customerMobilePhoneNo;
	}

	public String getCustomerProvince() {
		return customerProvince;
	}

	public void setCustomerProvince(String customerProvince) {
		this.customerProvince = customerProvince;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getIntentionalCarModel() {
		return intentionalCarModel;
	}

	public void setIntentionalCarModel(String intentionalCarModel) {
		this.intentionalCarModel = intentionalCarModel;
	}

	public Date getIntentionalBuyDate() {
		return intentionalBuyDate;
	}

	public void setIntentionalBuyDate(Date intentionalBuyDate) {
		this.intentionalBuyDate = intentionalBuyDate;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCustomerGender() {
		return customerGender;
	}

	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getIntentionalDealer() {
		return intentionalDealer;
	}

	public void setIntentionalDealer(String intentionalDealer) {
		this.intentionalDealer = intentionalDealer;
	}

	public String getIntentionalBuyDateRange() {
		return intentionalBuyDateRange;
	}

	public void setIntentionalBuyDateRange(String intentionalBuyDateRange) {
		this.intentionalBuyDateRange = intentionalBuyDateRange;
	}

	public String getCarOwnerProvince() {
		return carOwnerProvince;
	}

	public void setCarOwnerProvince(String carOwnerProvince) {
		this.carOwnerProvince = carOwnerProvince;
	}

	public String getCarOwnerCity() {
		return carOwnerCity;
	}

	public void setCarOwnerCity(String carOwnerCity) {
		this.carOwnerCity = carOwnerCity;
	}

	public String getCarOwnerDealer() {
		return carOwnerDealer;
	}

	public void setCarOwnerDealer(String carOwnerDealer) {
		this.carOwnerDealer = carOwnerDealer;
	}

	public String getCarOwnerBuyTime() {
		return carOwnerBuyTime;
	}

	public void setCarOwnerBuyTime(String carOwnerBuyTime) {
		this.carOwnerBuyTime = carOwnerBuyTime;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	


	
	
	
	
}
