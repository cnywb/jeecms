package com.jeecms.cms.entity.main.base;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseFordMemberForm implements Serializable {

	public static String REF = "FordMemberForm";
	public static String PRO_VFORM_ID = "vformId";
	public static String PRO_VFORM_NAME="vformName";
	public static String PRO_VFORM_TEL="vformTel";
	public static String PRO_VVIN = "vvin";
	public static String PRO_VCUSTOMERID = "vcustomerid";
	public static String PRO_VCARID = "vcarid";
	public static String PRO_VCONTACTPID = "vcontactpid";
	public static String PRO_VCREATED = "vcreated";
	public static String PRO_DCRTDATE = "dcrtDate";
	public static String PRO_VUPDATE = "vupdated";
	public static String PRO_DUPDATE = "dupDate";
	public static String PRO_VNOTES = "vnotes";
	public static String PRO_VDMS_OWNER_ID = "vdmsOwnerId";

	public BaseFordMemberForm() {
		initialize();
	}

	protected void initialize() {
	}
	
	

	public BaseFordMemberForm(String vformId, String vformName,
			String vformTel, String vvin, String vcustomerid, String vcarid,
			String vcontactpid, String vcreated, Date dcrtDate,
			String vupdated, Date dupDate, String vnotes, String vdmsOwnerId) {
		this.vformId = vformId;
		this.vformName = vformName;
		this.vformTel = vformTel;
		this.vvin = vvin;
		this.vcustomerid = vcustomerid;
		this.vcarid = vcarid;
		this.vcontactpid = vcontactpid;
		this.vcreated = vcreated;
		this.dcrtDate = dcrtDate;
		this.vupdated = vupdated;
		this.dupDate = dupDate;
		this.vnotes = vnotes;
		this.vdmsOwnerId = vdmsOwnerId;
		initialize();
	}



	// fields
	// 认证表单编号
	private String vformId;
	// 客户名称
	private String vformName;
	// 客户电话
	private String vformTel;
	// Vin码
	private String vvin;
	// 客户编号
	private String vcustomerid;
	// 车辆编号
	private String vcarid;
	// 联系人编号
	private String vcontactpid;
	// 创建人
	private String vcreated;
	// 创建日期
	private Date dcrtDate;
	// 更新人
	private String vupdated;
	// 更新日期
	private Date dupDate;
	// 备注
	private String vnotes;
	// DMS原始编号
	private String vdmsOwnerId;

	public String getVformId() {
		return vformId;
	}

	public void setVformId(String vformId) {
		this.vformId = vformId;
	}

	public String getVformName() {
		return vformName;
	}

	public void setVformName(String vformName) {
		this.vformName = vformName;
	}

	public String getVformTel() {
		return vformTel;
	}

	public void setVformTel(String vformTel) {
		this.vformTel = vformTel;
	}

	public String getVvin() {
		return vvin;
	}

	public void setVvin(String vvin) {
		this.vvin = vvin;
	}

	public String getVcustomerid() {
		return vcustomerid;
	}

	public void setVcustomerid(String vcustomerid) {
		this.vcustomerid = vcustomerid;
	}

	public String getVcarid() {
		return vcarid;
	}

	public void setVcarid(String vcarid) {
		this.vcarid = vcarid;
	}

	public String getVcontactpid() {
		return vcontactpid;
	}

	public void setVcontactpid(String vcontactpid) {
		this.vcontactpid = vcontactpid;
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

	public String getVnotes() {
		return vnotes;
	}

	public void setVnotes(String vnotes) {
		this.vnotes = vnotes;
	}

	public String getVdmsOwnerId() {
		return vdmsOwnerId;
	}

	public void setVdmsOwnerId(String vdmsOwnerId) {
		this.vdmsOwnerId = vdmsOwnerId;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	
}
