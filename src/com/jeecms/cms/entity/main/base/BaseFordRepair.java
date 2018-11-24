package com.jeecms.cms.entity.main.base;

import java.io.Serializable;
import java.util.Date;

public class BaseFordRepair implements Serializable{
	public static String REF = "FordRepair";
	public static String PROP_REPAIR_ID ="repairId";
	public static String PROP_VCAR_ID ="vcarId";
	public static String PROP_VRO_ID ="vroId";
	public static String PROP_VBILL_ID ="vbillId";
	public static String PROP_VSST_ID ="vsstId";
	public static String PROP_VSST_NAME ="vsstName";
	public static String PROP_VVIN ="vvin";
	public static String PROP_VMODEL ="vmodel";
	public static String PROP_VLICENSE_NMB ="vlicenseNmb";
	public static String PROP_VREPAIR_DATE ="vrepairDate";
	public static String PROP_VFINISH_DATE ="vfinishDate";
	public static String PROP_VBILL_DATE ="vbillDate";
	public static String PROP_VBILL_STATUS ="vbillStatus";
	public static String PROP_VREPAIR_TYPE ="vrepairType";
	public static String PROP_NMILEAGE ="nmileage";
	public static String PROP_NLABOR_FEE ="nlaborFee";
	public static String PROP_NREPAIR_MATERIAL_FEE ="nrepairMaterialFee";
	public static String PROP_NSALE_MATERIAL_FEE ="nsaleMaterialFee";
	public static String PROP_NADD_ITEM_FEE ="naddItemFee";
	public static String PROP_NOVER_ITEM_FEE ="noverItemFee";
	public static String PROP_NREPAIR_FEE ="nrepairFee";
	public static String PROP_NBALANCE_FEE ="nbalanceFee";
	public static String PROP_VNOTES ="vnotes";
	public static String PROP_VCREATED ="vcreated";
	public static String PROP_DATE ="dcrtDate";
	public static String PROP_VUPDATED ="vupdated";
	public static String PROP_DUP_DATE ="dupDate";
	
	public BaseFordRepair() {
		initialize ();
	}

	public BaseFordRepair(String repairId, String vcarId, String vroId,
			String vbillId, String vsstId, String vsstName, String vvin,
			String vmodel, String vlicenseNmb, String vrepairDate,
			String vfinishDate, String vbillDate, String vbillStatus,
			String vrepairType, Long nmileage, Double nlaborFee,
			Double nrepairMaterialFee, Double nsaleMaterialFee,
			Double naddItemFee, Double noverItemFee, Double nrepairFee,
			Double nbalanceFee, String vnotes, String vcreated, Date dcrtDate,
			String vupdated, Date dupDate) {
		super();
		this.repairId = repairId;
		this.vcarId = vcarId;
		this.vroId = vroId;
		this.vbillId = vbillId;
		this.vsstId = vsstId;
		this.vsstName = vsstName;
		this.vvin = vvin;
		this.vmodel = vmodel;
		this.vlicenseNmb = vlicenseNmb;
		this.vrepairDate = vrepairDate;
		this.vfinishDate = vfinishDate;
		this.vbillDate = vbillDate;
		this.vbillStatus = vbillStatus;
		this.vrepairType = vrepairType;
		this.nmileage = nmileage;
		this.nlaborFee = nlaborFee;
		this.nrepairMaterialFee = nrepairMaterialFee;
		this.nsaleMaterialFee = nsaleMaterialFee;
		this.naddItemFee = naddItemFee;
		this.noverItemFee = noverItemFee;
		this.nrepairFee = nrepairFee;
		this.nbalanceFee = nbalanceFee;
		this.vnotes = vnotes;
		this.vcreated = vcreated;
		this.dcrtDate = dcrtDate;
		this.vupdated = vupdated;
		this.dupDate = dupDate;
		initialize ();
	}

	protected void initialize () {}
	
	private String repairId;
	private String vcarId;
	private String vroId;
	private String vbillId;
	private String vsstId;
	private String vsstName;
	private String vvin;
	private String vmodel;
	private String vlicenseNmb;
	private String vrepairDate;
	private String vfinishDate;
	private String vbillDate;
	private String vbillStatus;
	private String vrepairType;
	private Long nmileage;
	private Double nlaborFee;
	private Double nrepairMaterialFee;
	private Double nsaleMaterialFee;
	private Double naddItemFee;
	private Double noverItemFee;
	private Double nrepairFee;
	private Double nbalanceFee;
	private String vnotes;
	private String vcreated;
	private Date dcrtDate;
	private String vupdated;
	private Date dupDate;

	public String getRepairId() {
		return repairId;
	}

	public void setRepairId(String repairId) {
		this.repairId = repairId;
	}

	public String getVcarId() {
		return vcarId;
	}

	public void setVcarId(String vcarId) {
		this.vcarId = vcarId;
	}

	public String getVroId() {
		return vroId;
	}

	public void setVroId(String vroId) {
		this.vroId = vroId;
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

	public String getVmodel() {
		return vmodel;
	}

	public void setVmodel(String vmodel) {
		this.vmodel = vmodel;
	}

	public String getVlicenseNmb() {
		return vlicenseNmb;
	}

	public void setVlicenseNmb(String vlicenseNmb) {
		this.vlicenseNmb = vlicenseNmb;
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

	public String getVbillStatus() {
		return vbillStatus;
	}

	public void setVbillStatus(String vbillStatus) {
		this.vbillStatus = vbillStatus;
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

	public Double getNlaborFee() {
		return nlaborFee;
	}

	public void setNlaborFee(Double nlaborFee) {
		this.nlaborFee = nlaborFee;
	}

	public Double getNrepairMaterialFee() {
		return nrepairMaterialFee;
	}

	public void setNrepairMaterialFee(Double nrepairMaterialFee) {
		this.nrepairMaterialFee = nrepairMaterialFee;
	}

	public Double getNsaleMaterialFee() {
		return nsaleMaterialFee;
	}

	public void setNsaleMaterialFee(Double nsaleMaterialFee) {
		this.nsaleMaterialFee = nsaleMaterialFee;
	}

	public Double getNaddItemFee() {
		return naddItemFee;
	}

	public void setNaddItemFee(Double naddItemFee) {
		this.naddItemFee = naddItemFee;
	}

	public Double getNoverItemFee() {
		return noverItemFee;
	}

	public void setNoverItemFee(Double noverItemFee) {
		this.noverItemFee = noverItemFee;
	}

	public Double getNrepairFee() {
		return nrepairFee;
	}

	public void setNrepairFee(Double nrepairFee) {
		this.nrepairFee = nrepairFee;
	}

	public Double getNbalanceFee() {
		return nbalanceFee;
	}

	public void setNbalanceFee(Double nbalanceFee) {
		this.nbalanceFee = nbalanceFee;
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
