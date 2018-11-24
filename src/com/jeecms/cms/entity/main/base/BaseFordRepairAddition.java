package com.jeecms.cms.entity.main.base;

import java.io.Serializable;
import java.util.Date;

public class BaseFordRepairAddition implements Serializable {
	public static String REF = "FordRepairAddition";

	public static String PROP_VAID = "vaid";
	public static String PROP_VBILL_ID = "vbillId";
	public static String PROP_VRO_ID = "vroId";
	public static String PROP_VSST_ID = "vsstId";
	public static String PROP_VITEM_TYPE = "vitemType";
	public static String PROP_VITEM_CODE = "vitemCode";
	public static String PROP_VITEM_NAME = "vitemName";
	public static String PROP_NADDITION_FEE = "nadditionFee";
	public static String PROP_NDISCOUNT_RATE = "ndiscountRate";
	public static String PROP_VNOTES = "vnotes";
	public static String PROP_VCREATED = "vcreated";
	public static String PROP_DCRT_DATE = "dcrtDate";
	public static String PROP_VUPDATED = "vupdated";
	public static String PROP_DUP_DATE = "dupDate";

	public BaseFordRepairAddition() {
		initialize();
	}

	public BaseFordRepairAddition(Long vaid, String vbillId, String vroId,
			String vsstId, String vitemType, String vitemCode,
			String vitemName, Long nadditionFee, Long ndiscountRate,
			String vnotes, String vcreated, Date dcrtDate, String vupdated,
			Date dupDate) {
		super();
		this.vaid = vaid;
		this.vbillId = vbillId;
		this.vroId = vroId;
		this.vsstId = vsstId;
		this.vitemType = vitemType;
		this.vitemCode = vitemCode;
		this.vitemName = vitemName;
		this.nadditionFee = nadditionFee;
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

	private Long vaid;
	private String vbillId;
	private String vroId;
	private String vsstId;
	private String vitemType;
	private String vitemCode;
	private String vitemName;
	private Long nadditionFee;
	private Long ndiscountRate;
	private String vnotes;
	private String vcreated;
	private Date dcrtDate;
	private String vupdated;
	private Date dupDate;

	public Long getVaid() {
		return vaid;
	}

	public void setVaid(Long vaid) {
		this.vaid = vaid;
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

	public String getVitemType() {
		return vitemType;
	}

	public void setVitemType(String vitemType) {
		this.vitemType = vitemType;
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

	public Long getNadditionFee() {
		return nadditionFee;
	}

	public void setNadditionFee(Long nadditionFee) {
		this.nadditionFee = nadditionFee;
	}

	public Long getNdiscountRate() {
		return ndiscountRate;
	}

	public void setNdiscountRate(Long ndiscountRate) {
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

	public String toString() {
		return super.toString();
	}
}
