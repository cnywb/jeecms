package com.jeecms.cms.entity.main.base;

import java.io.Serializable;

public class BaseTempDeal  implements Serializable{
	
	public static String REF = "TempDeal";
	public static String PROP_ID ="id";
	public static String PROP_NAME ="name";
	public static String PROP_VIN ="vin";
	public static String PROP_CAMPAIGN_CODE ="campaignCode";
	public static String PROP_AMOUNT ="amount";
	public static String PROP_USE_AMOUNT ="useAmount";
	public static String PROP_VALID_BEGIN_DATE ="validBeginDate";
	public static String PROP_VALID_END_DATE ="validEndDate";
	public static String PROP_DEALER_SERVICE_CODE ="dealerServiceCode";
	public static String PROP_STATUS ="status";
	public static String PROP_CREATE_DATE ="createDate";
	public static String PROP_USER_NAME ="userName";
	public static String PROP_LOWEST_AMOUNT ="lowestAmount";
	public static String PROP_LIMIT_DEDUCT ="limitDeduct";
	
	
	
	public BaseTempDeal() {
		initialize ();
	}
	

	


	public BaseTempDeal(long id, String name, String vin, String campaignCode,
			int amount, int useAmount, String validBeginDate,
			String validEndDate, String dealerServiceCode, String status,
			String createDate, String userName, int lowestAmount,
			int limitDeduct) {
		this.id = id;
		this.name = name;
		this.vin = vin;
		this.campaignCode = campaignCode;
		this.amount = amount;
		this.useAmount = useAmount;
		this.validBeginDate = validBeginDate;
		this.validEndDate = validEndDate;
		this.dealerServiceCode = dealerServiceCode;
		this.status = status;
		this.createDate = createDate;
		this.userName = userName;
		this.lowestAmount = lowestAmount;
		this.limitDeduct = limitDeduct;
		initialize ();
	}




	protected void initialize () {}
	
	//fields
	private long id;
	private String name;
	private String vin;
	private String campaignCode;
	private int amount;
	private int useAmount;
	private String validBeginDate;
	private String validEndDate;
	private String dealerServiceCode;
	private String status;
	private String createDate;
	private String userName;
	private int lowestAmount;
	private int limitDeduct;

	
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getVin() {
		return vin;
	}


	public void setVin(String vin) {
		this.vin = vin;
	}


	public String getCampaignCode() {
		return campaignCode;
	}


	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public int getUseAmount() {
		return useAmount;
	}


	public void setUseAmount(int useAmount) {
		this.useAmount = useAmount;
	}


	public String getValidBeginDate() {
		return validBeginDate;
	}


	public void setValidBeginDate(String validBeginDate) {
		this.validBeginDate = validBeginDate;
	}


	public String getValidEndDate() {
		return validEndDate;
	}


	public void setValidEndDate(String validEndDate) {
		this.validEndDate = validEndDate;
	}


	public String getDealerServiceCode() {
		return dealerServiceCode;
	}


	public void setDealerServiceCode(String dealerServiceCode) {
		this.dealerServiceCode = dealerServiceCode;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getCreateDate() {
		return createDate;
	}


	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getLowestAmount() {
		return lowestAmount;
	}


	public void setLowestAmount(int lowestAmount) {
		this.lowestAmount = lowestAmount;
	}


	public int getLimitDeduct() {
		return limitDeduct;
	}


	public void setLimitDeduct(int limitDeduct) {
		this.limitDeduct = limitDeduct;
	}


	public String toString () {
		return super.toString();
	}
}
