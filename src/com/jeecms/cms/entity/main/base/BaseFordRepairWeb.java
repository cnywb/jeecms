package com.jeecms.cms.entity.main.base;

import java.io.Serializable;
import java.util.Date;

public class BaseFordRepairWeb implements Serializable{
	public static String REF = "FordRepairWeb";
	public static String PROP_VRO_ID ="vroId";
	public static String PROP_VCARD_NO ="vcardNo";
	public static String PROP_VBILL_ID ="vbillId";
	public static String PROP_VSST_ID ="vsstId";
	public static String PROP_VSST_NAME ="vsstName";
	public static String PROP_VVIN ="vvin";
	public static String PROP_VREPAIR_DATE ="vrepairDate";
	public static String PROP_VFINISH_DATE ="vfinishDate";
	public static String PROP_VBILL_DATE ="vbillDate";
	public static String PROP_VREPAIR_TYPE ="vrepairType";
	public static String PROP_NMILEAGE ="nmileage";
	public static String PROP_NBALANCE_FEE ="nbalanceFee";
	public static String PROP_VCREATED ="vcreated";
	public static String PROP_DCRT_DATE ="dcrtDate";
	
	/**
	 * 
	 */
	public BaseFordRepairWeb() {
		initialize ();
	}
	
	
	public BaseFordRepairWeb(String vroId, String vcardNo, String vbillId,
			String vsstId, String vsstName, String vvin, String vrepairDate,
			String vfinishDate, String vbillDate, String vrepairType,
			Long nmileage, Long nbalanceFee, String vcreated, Date dcrtDate) {
		this.vroId = vroId;
		this.vcardNo = vcardNo;
		this.vbillId = vbillId;
		this.vsstId = vsstId;
		this.vsstName = vsstName;
		this.vvin = vvin;
		this.vrepairDate = vrepairDate;
		this.vfinishDate = vfinishDate;
		this.vbillDate = vbillDate;
		this.vrepairType = vrepairType;
		this.nmileage = nmileage;
		this.nbalanceFee = nbalanceFee;
		this.vcreated = vcreated;
		this.dcrtDate = dcrtDate;
		initialize ();
	}


	protected void initialize () {}
	
	private String vroId;
	private String vcardNo;
	private String vbillId;
	private String vsstId;
	private String vsstName;
	private String vvin;
	private String vrepairDate;
	private String vfinishDate;
	private String vbillDate;
	private String vrepairType;
	private Long nmileage;
	private Long nbalanceFee;
	private String vcreated;
	private Date dcrtDate;

	public String getVroId() {
		return vroId;
	}
	public void setVroId(String vroId) {
		this.vroId = vroId;
	}
	public String getVcardNo() {
		return vcardNo;
	}
	public void setVcardNo(String vcardNo) {
		this.vcardNo = vcardNo;
	}
	public String getVbillId() {
		return vbillId;
	}
	public void setVbillId(String vbillId) {
		this.vbillId = vbillId;
	}
	public String getVsstId() {
		return vsstId;
	}
	public void setVsstId(String vsstId) {
		this.vsstId = vsstId;
	}
	public String getVsstName() {
		return vsstName;
	}
	public void setVsstName(String vsstName) {
		this.vsstName = vsstName;
	}
	public String getVvin() {
		return vvin;
	}
	public void setVvin(String vvin) {
		this.vvin = vvin;
	}
	public String getVrepairDate() {
		return vrepairDate;
	}
	public void setVrepairDate(String vrepairDate) {
		this.vrepairDate = vrepairDate;
	}
	public String getVfinishDate() {
		return vfinishDate;
	}
	public void setVfinishDate(String vfinishDate) {
		this.vfinishDate = vfinishDate;
	}
	public String getVbillDate() {
		return vbillDate;
	}
	public void setVbillDate(String vbillDate) {
		this.vbillDate = vbillDate;
	}
	public String getVrepairType() {
		return vrepairType;
	}
	public void setVrepairType(String vrepairType) {
		this.vrepairType = vrepairType;
	}
	public Long getNmileage() {
		return nmileage;
	}
	public void setNmileage(Long nmileage) {
		this.nmileage = nmileage;
	}
	public Long getNbalanceFee() {
		return nbalanceFee;
	}
	public void setNbalanceFee(Long nbalanceFee) {
		this.nbalanceFee = nbalanceFee;
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
	@Override
	public String toString() {
		return super.toString();
	}
	
	
}
