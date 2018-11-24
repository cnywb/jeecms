package com.jeecms.cms.entity.main.base;

import java.io.Serializable;
import java.util.Date;

public class BaseFordRepairLabor implements Serializable {
	public static String REF = "FordRepairLabor";
	public static String PROP_VLID ="vlid";
	public static String PROP_VBILL_ID ="vbillId";
	public static String PROP_VRO_ID ="vroId";
	public static String PROP_VSST_ID ="vsstId";
	public static String PROP_VITEM_CODE ="vitemCode";
	public static String PROP_VITEM_NAME ="vitemName";
	public static String PROP_NITEM_COST ="nitemCost";
	public static String PROP_NLABOR_HOUR ="nlaborHour";
	public static String PROP_NLABOR_FEE ="nlaborFee";
	public static String PROP_NDISCOUNT_RATE ="ndiscountRate";
	public static String PROP_VNOTES ="vnotes";
	public static String PROP_VCREATED ="vcreated";
	public static String PROP_DCRT_DATE ="dcrtDate";
	public static String PROP_VUPDATED ="vupdated";
	public static String PROP_DUP_DATE ="dupDate";
	public BaseFordRepairLabor() {
		initialize();
	}


	public BaseFordRepairLabor(Long vlid, String vbillId, String vroId,
			String vsstId, String vitemCode, String vitemName,
			Double nitemCost, Double nlaborHour, Double nlaborFee,
			Double ndiscountRate, String vnotes, String vcreated,
			Date dcrtDate, String vupdated, Date dupDate) {
		this.vlid = vlid;
		this.vbillId = vbillId;
		this.vroId = vroId;
		this.vsstId = vsstId;
		this.vitemCode = vitemCode;
		this.vitemName = vitemName;
		this.nitemCost = nitemCost;
		this.nlaborHour = nlaborHour;
		this.nlaborFee = nlaborFee;
		this.ndiscountRate = ndiscountRate;
		this.vnotes = vnotes;
		this.vcreated = vcreated;
		this.dcrtDate = dcrtDate;
		this.vupdated = vupdated;
		this.dupDate = dupDate;
		initialize();
	}


	protected void initialize() {
	}
	private Long vlid;
	private String vbillId;
	private String vroId;
	private String vsstId;
	private String vitemCode;
	private String vitemName;
	private Double nitemCost;
	private Double nlaborHour;
	private Double nlaborFee;
	private Double ndiscountRate;
	private String vnotes;
	private String vcreated;
	private Date dcrtDate;
	private String vupdated;
	private Date dupDate;
	
	
	public Long getVlid() {
		return vlid;
	}


	public void setVlid(Long vlid) {
		this.vlid = vlid;
	}


	public String getVbillId() {
		return vbillId;
	}


	public void setVbillId(String vbillId) {
		this.vbillId = vbillId;
	}


	public String getVroId() {
		return vroId;
	}


	public void setVroId(String vroId) {
		this.vroId = vroId;
	}


	public String getVsstId() {
		return vsstId;
	}


	public void setVsstId(String vsstId) {
		this.vsstId = vsstId;
	}


	public String getVitemCode() {
		return vitemCode;
	}


	public void setVitemCode(String vitemCode) {
		this.vitemCode = vitemCode;
	}


	public String getVitemName() {
		return vitemName;
	}


	public void setVitemName(String vitemName) {
		this.vitemName = vitemName;
	}


	public Double getNitemCost() {
		return nitemCost;
	}


	public void setNitemCost(Double nitemCost) {
		this.nitemCost = nitemCost;
	}


	public Double getNlaborHour() {
		return nlaborHour;
	}


	public void setNlaborHour(Double nlaborHour) {
		this.nlaborHour = nlaborHour;
	}


	public Double getNlaborFee() {
		return nlaborFee;
	}


	public void setNlaborFee(Double nlaborFee) {
		this.nlaborFee = nlaborFee;
	}


	public Double getNdiscountRate() {
		return ndiscountRate;
	}


	public void setNdiscountRate(Double ndiscountRate) {
		this.ndiscountRate = ndiscountRate;
	}


	public String getVnotes() {
		return vnotes;
	}


	public void setVnotes(String vnotes) {
		this.vnotes = vnotes;
	}


	public String getVcreated() {
		return vcreated;
	}


	public void setVcreated(String vcreated) {
		this.vcreated = vcreated;
	}


	public Date getDcrtDate() {
		return dcrtDate;
	}


	public void setDcrtDate(Date dcrtDate) {
		this.dcrtDate = dcrtDate;
	}


	public String getVupdated() {
		return vupdated;
	}


	public void setVupdated(String vupdated) {
		this.vupdated = vupdated;
	}


	public Date getDupDate() {
		return dupDate;
	}


	public void setDupDate(Date dupDate) {
		this.dupDate = dupDate;
	}


	@Override
	public String toString() {
		return super.toString();
	}
}
