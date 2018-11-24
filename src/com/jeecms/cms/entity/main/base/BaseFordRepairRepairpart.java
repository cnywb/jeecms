package com.jeecms.cms.entity.main.base;

import java.io.Serializable;
import java.util.Date;

public class BaseFordRepairRepairpart implements Serializable {
	public static String REF = "FordRepairRepairpart";
	public static String PROP_VRPID ="vrpid";
	public static String PROP_VBILL_ID ="vbillId";
	public static String PROP_VRO_ID ="vroId";
	public static String PROP_VSST_ID ="vsstId";
	public static String PROP_VPART_CODE ="vpartCode";
	public static String PROP_VPART_NAME ="vpartName";
	public static String PROP_NPART_NUM ="npartNum";
	public static String PROP_VCHARGE_PARTITION ="vchargePartition";
	public static String PROP_VMANAGE_SORT ="vmanageSort";
	public static String PROP_VISMAIN_PART ="vismainPart";
	public static String PROP_NPART_COST_PRICE ="npartCostPrice";
	public static String PROP_NPART_SALE_PRICE ="npartSalePrice";
	public static String PROP_NPART_COST_FEE ="npartCostFee";
	public static String PROP_NPART_SALE_FEE ="npartSaleFee";
	public static String PROP_NDISCOUNT_RATE ="ndiscountRate";
	public static String PROP_VNOTES ="vnotes";
	public static String PROP_VCREATED ="vcreated";
	public static String PROP_DCRT_DATE ="dcrtDate";
	public static String PROP_VUPDATED ="vupdated";
	public static String PROP_DUP_DATE ="dupDate";
	public BaseFordRepairRepairpart() {
		 initialize();
	}


	public BaseFordRepairRepairpart(Long vrpid, String vbillId, String vroId,
			String vsstId, String vpartCode, String vpartName, Long npartNum,
			String vchargePartition, String vmanageSort, String vismainPart,
			Long npartCostPrice, Long npartSalePrice, Long npartCostFee,
			Double npartSaleFee, Long ndiscountRate, String vnotes,
			String vcreated, Date dcrtDate, String vupdated, Date dupDate) {
		this.vrpid = vrpid;
		this.vbillId = vbillId;
		this.vroId = vroId;
		this.vsstId = vsstId;
		this.vpartCode = vpartCode;
		this.vpartName = vpartName;
		this.npartNum = npartNum;
		this.vchargePartition = vchargePartition;
		this.vmanageSort = vmanageSort;
		this.vismainPart = vismainPart;
		this.npartCostPrice = npartCostPrice;
		this.npartSalePrice = npartSalePrice;
		this.npartCostFee = npartCostFee;
		this.npartSaleFee = npartSaleFee;
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
	private Long vrpid;
	private String vbillId;
	private String vroId;
	private String vsstId;
	private String vpartCode;
	private String vpartName;
	private Long npartNum;
	private String vchargePartition;
	private String vmanageSort;
	private String vismainPart;
	private Long npartCostPrice;
	private Long npartSalePrice;
	private Long npartCostFee;
	private Double npartSaleFee;
	private Long ndiscountRate;
	private String vnotes;
	private String vcreated;
	private Date dcrtDate;
	private String vupdated;
	private Date dupDate;
	
	
	public Long getVrpid() {
		return vrpid;
	}


	public void setVrpid(Long vrpid) {
		this.vrpid = vrpid;
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


	public String getVpartCode() {
		return vpartCode;
	}


	public void setVpartCode(String vpartCode) {
		this.vpartCode = vpartCode;
	}


	public String getVpartName() {
		return vpartName;
	}


	public void setVpartName(String vpartName) {
		this.vpartName = vpartName;
	}


	public Long getNpartNum() {
		return npartNum;
	}


	public void setNpartNum(Long npartNum) {
		this.npartNum = npartNum;
	}


	public String getVchargePartition() {
		return vchargePartition;
	}


	public void setVchargePartition(String vchargePartition) {
		this.vchargePartition = vchargePartition;
	}


	public String getVmanageSort() {
		return vmanageSort;
	}


	public void setVmanageSort(String vmanageSort) {
		this.vmanageSort = vmanageSort;
	}


	public String getVismainPart() {
		return vismainPart;
	}


	public void setVismainPart(String vismainPart) {
		this.vismainPart = vismainPart;
	}


	public Long getNpartCostPrice() {
		return npartCostPrice;
	}


	public void setNpartCostPrice(Long npartCostPrice) {
		this.npartCostPrice = npartCostPrice;
	}


	public Long getNpartSalePrice() {
		return npartSalePrice;
	}


	public void setNpartSalePrice(Long npartSalePrice) {
		this.npartSalePrice = npartSalePrice;
	}


	public Long getNpartCostFee() {
		return npartCostFee;
	}


	public void setNpartCostFee(Long npartCostFee) {
		this.npartCostFee = npartCostFee;
	}


	public Double getNpartSaleFee() {
		return npartSaleFee;
	}


	public void setNpartSaleFee(Double npartSaleFee) {
		this.npartSaleFee = npartSaleFee;
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


	@Override
	public String toString() {
		return super.toString();
	}
}
