package com.jeecms.cms.entity.main.base;

import java.io.Serializable;

public class BaseTempElec implements Serializable{

	public static String REF = "TempElec";
	public static String PROP_ACCOUNTID ="accountId";
	public static String PROP_VIN ="vin";
	public static String PROP_RO_NO ="roNo";
	public static String PROP_DEALER_SERVICE_CODE ="dealerServiceCode";
	public static String PROP_USED_DATE ="usedDate";
	public static String PROP_USED_AMOUNT ="usedAmount";
	public static String PROP_LOST_AMOUNT ="lostAmount";
	public static String PROP_ELE_TYPE ="eleType";
	

	/**
	 * 
	 */
	public BaseTempElec() {
		initialize ();
	}



	public BaseTempElec(long accountId, String vin, String roNo,
			String dealerServiceCode, String usedDate, long usedAmount,
			long lostAmount, String eleType) {
		super();
		this.accountId = accountId;
		this.vin = vin;
		this.roNo = roNo;
		this.dealerServiceCode = dealerServiceCode;
		this.usedDate = usedDate;
		this.usedAmount = usedAmount;
		this.lostAmount = lostAmount;
		this.eleType = eleType;
		initialize ();
	}



	protected void initialize () {}
	
	private long accountId;
	private String vin;
	private String roNo;
	private String dealerServiceCode;
	private String usedDate;
	private long usedAmount;
	private long lostAmount;
	private String eleType;

	
	
	
	public long getAccountId() {
		return accountId;
	}



	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}



	public String getVin() {
		return vin;
	}



	public void setVin(String vin) {
		this.vin = vin;
	}



	public String getRoNo() {
		return roNo;
	}



	public void setRoNo(String roNo) {
		this.roNo = roNo;
	}



	public String getDealerServiceCode() {
		return dealerServiceCode;
	}



	public void setDealerServiceCode(String dealerServiceCode) {
		this.dealerServiceCode = dealerServiceCode;
	}



	public String getUsedDate() {
		return usedDate;
	}



	public void setUsedDate(String usedDate) {
		this.usedDate = usedDate;
	}



	public long getUsedAmount() {
		return usedAmount;
	}



	public void setUsedAmount(long usedAmount) {
		this.usedAmount = usedAmount;
	}



	public long getLostAmount() {
		return lostAmount;
	}



	public void setLostAmount(long lostAmount) {
		this.lostAmount = lostAmount;
	}



	public String getEleType() {
		return eleType;
	}



	public void setEleType(String eleType) {
		this.eleType = eleType;
	}






	public String toString () {
		return super.toString();
	}
}
