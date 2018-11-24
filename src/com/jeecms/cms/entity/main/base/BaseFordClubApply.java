package com.jeecms.cms.entity.main.base;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseFordClubApply implements Serializable {

	public static String REF = "FordClubApply";
	public static String PRO_VAPPLY_ID = "vapplyId";
	public static String PRO_VNAME = "vname";
	public static String PRO_VGENDER = "vgender";
	public static String PRO_VBIRTHDAY = "vbirthday";
	public static String PRO_VIDNMB = "vidnmb";
	public static String PRO_VPROVINCE = "vprovince";
	public static String PRO_VCITY = "vcity";
	public static String PRO_VDIST = "vdist";
	public static String PRO_VADDRESS = "vaddress";
	public static String PRO_VZIPCODE = "vzipcode";
	public static String PRO_VMOBILE = "vmobile";
	public static String PRO_VTEL = "vtel";
	public static String PRO_VEMAIL = "vemail";
	public static String PRO_VVIN = "vvin";
	public static String PRO_VLICENSE_NMB = "vlicenseNmb";
	public static String PRO_NAPPLY_TIMES = "napplyTimes";
	public static String PRO_VAPPLY_DATE = "vapplyDate";
	public static String PRO_VAPPLY_STATUS = "vapplyStatus";
	public static String PRO_VCONFIRM_DATE = "vconfirmDate";
	public static String PRO_VCONFIRM_RESULT = "vconfirmResult";
	public static String PRO_VAPPLY_FROM = "vapplyFrom";
	public static String PRO_VAPPLY_SOURCE = "vapplySource";
	public static String PRO_VNOTES = "vnotes";
	public static String PRO_VCREATED = "vcreated";
	public static String PRO_DCRT_DATE = "dcrtDate";
	public static String PRO_VUPDATE = "vupdate";
	public static String PRO_DUP_DATE = "dupDate";
	public static String PRO_VCOMTEL = "vcomtel";
	public static String PRO_VBUYREASON = "vbuyreason";
	public static String PRO_VIDTYPE = "vidtype";
	public static String PRO_VUSERID = "vuserid";
	public static String PRO_VPVID = "vpvid";
	public static String PRO_VCIID = "vciid";
	public static String PRO_USER_ID="userId";

	public BaseFordClubApply() {
		initialize();
	}

	protected void initialize() {
	}

	public BaseFordClubApply(String vapplyId, String vname, String vgender,
			String vbirthday, String vidnmb, String vprovince, String vcity,
			String vdist, String vaddress, String vzipcode, String vmobile,
			String vtel, String vemail, String vvin, String vlicenseNmb,
			Long napplyTimes, String vapplyDate, String vapplyStatus,
			String vconfirmDate, String vconfirmResult, String vapplyFrom,
			String vapplySource, String vnotes, String vcreated, Date dcrtDate,
			String vupdate, Date dupDate, String vcomtel, String vbuyreason,
			String vidtype, String vuserid, Long vpvid, Long vciid,Integer userId) {
		this.vapplyId = vapplyId;
		this.vname = vname;
		this.vgender = vgender;
		this.vbirthday = vbirthday;
		this.vidnmb = vidnmb;
		this.vprovince = vprovince;
		this.vcity = vcity;
		this.vdist = vdist;
		this.vaddress = vaddress;
		this.vzipcode = vzipcode;
		this.vmobile = vmobile;
		this.vtel = vtel;
		this.vemail = vemail;
		this.vvin = vvin;
		this.vlicenseNmb = vlicenseNmb;
		this.napplyTimes = napplyTimes;
		this.vapplyDate = vapplyDate;
		this.vapplyStatus = vapplyStatus;
		this.vconfirmDate = vconfirmDate;
		this.vconfirmResult = vconfirmResult;
		this.vapplyFrom = vapplyFrom;
		this.vapplySource = vapplySource;
		this.vnotes = vnotes;
		this.vcreated = vcreated;
		this.dcrtDate = dcrtDate;
		this.vupdate = vupdate;
		this.dupDate = dupDate;
		this.vcomtel = vcomtel;
		this.vbuyreason = vbuyreason;
		this.vidtype = vidtype;
		this.vuserid = vuserid;
		this.vpvid = vpvid;
		this.vciid = vciid;
		this.userId = userId;
	}

	// 申请编号
	private String vapplyId;
	// 客户名称
	private String vname;
	// 性别
	private String vgender;
	// 出生日期
	private String vbirthday;
	// 证件号码
	private String vidnmb;
	// 省
	private String vprovince;
	// 市
	private String vcity;
	// 区
	private String vdist;
	// 地址
	private String vaddress;
	// 邮编
	private String vzipcode;
	// 手机
	private String vmobile;
	// 座机
	private String vtel;
	// Email地址
	private String vemail;
	// Vin码
	private String vvin;
	// 牌照号码
	private String vlicenseNmb;
	// 申请次数
	private Long napplyTimes;
	// 申请日期(20090101)
	private String vapplyDate;
	// 申请状态(00申请中/01申请成功/02申请失败)
	private String vapplyStatus;
	// 处理日期(20090101)
	private String vconfirmDate;
	// 处理结果(00无效车主申请失败/01号码错误申请失败/02无人接听申请失败/03未联系到此人申请失败/04拒绝沟通申请失败/05拒绝加入申请失败/06成功加入)
	private String vconfirmResult;
	// 申请来源(01 经销商/02 福域网站/03呼叫中心/04 活动现场/05 其他)
	private String vapplyFrom;
	// 申请来源细分)
	private String vapplySource;
	// 备注
	private String vnotes;
	// 创建人
	private String vcreated;
	// 创建日期
	private Date dcrtDate;
	// 更新人
	private String vupdate;
	// 更新日期
	private Date dupDate;
	// 公司电话
	private String vcomtel;
	// 01：个人，02：公司
	private String vbuyreason;
	// 证件类型
	private String vidtype;
	// 网站用户编号
	private String vuserid;
	// 省编号
	private Long vpvid;
	// 市编号
	private Long vciid;
	//网站用户编号
	private Integer userId;

	public String getVapplyId() {
		return vapplyId;
	}

	public void setVapplyId(String vapplyId) {
		this.vapplyId = vapplyId;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getVgender() {
		return vgender;
	}

	public void setVgender(String vgender) {
		this.vgender = vgender;
	}

	public String getVbirthday() {
		return vbirthday;
	}

	public void setVbirthday(String vbirthday) {
		this.vbirthday = vbirthday;
	}

	public String getVidnmb() {
		return vidnmb;
	}

	public void setVidnmb(String vidnmb) {
		this.vidnmb = vidnmb;
	}

	public String getVprovince() {
		return vprovince;
	}

	public void setVprovince(String vprovince) {
		this.vprovince = vprovince;
	}

	public String getVcity() {
		return vcity;
	}

	public void setVcity(String vcity) {
		this.vcity = vcity;
	}

	public String getVdist() {
		return vdist;
	}

	public void setVdist(String vdist) {
		this.vdist = vdist;
	}

	public String getVaddress() {
		return vaddress;
	}

	public void setVaddress(String vaddress) {
		this.vaddress = vaddress;
	}

	public String getVzipcode() {
		return vzipcode;
	}

	public void setVzipcode(String vzipcode) {
		this.vzipcode = vzipcode;
	}

	public String getVmobile() {
		return vmobile;
	}

	public void setVmobile(String vmobile) {
		this.vmobile = vmobile;
	}

	public String getVtel() {
		return vtel;
	}

	public void setVtel(String vtel) {
		this.vtel = vtel;
	}

	public String getVemail() {
		return vemail;
	}

	public void setVemail(String vemail) {
		this.vemail = vemail;
	}

	public String getVvin() {
		return vvin;
	}

	public void setVvin(String vvin) {
		this.vvin = vvin;
	}

	public String getVlicenseNmb() {
		return vlicenseNmb;
	}

	public void setVlicenseNmb(String vlicenseNmb) {
		this.vlicenseNmb = vlicenseNmb;
	}

	public Long getNapplyTimes() {
		return napplyTimes;
	}

	public void setNapplyTimes(Long napplyTimes) {
		this.napplyTimes = napplyTimes;
	}

	public String getVapplyDate() {
		return vapplyDate;
	}

	public void setVapplyDate(String vapplyDate) {
		this.vapplyDate = vapplyDate;
	}

	public String getVapplyStatus() {
		return vapplyStatus;
	}

	public void setVapplyStatus(String vapplyStatus) {
		this.vapplyStatus = vapplyStatus;
	}

	public String getVconfirmDate() {
		return vconfirmDate;
	}

	public void setVconfirmDate(String vconfirmDate) {
		this.vconfirmDate = vconfirmDate;
	}

	public String getVconfirmResult() {
		return vconfirmResult;
	}

	public void setVconfirmResult(String vconfirmResult) {
		this.vconfirmResult = vconfirmResult;
	}

	public String getVapplyFrom() {
		return vapplyFrom;
	}

	public void setVapplyFrom(String vapplyFrom) {
		this.vapplyFrom = vapplyFrom;
	}

	public String getVapplySource() {
		return vapplySource;
	}

	public void setVapplySource(String vapplySource) {
		this.vapplySource = vapplySource;
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

	public String getVupdate() {
		return vupdate;
	}

	public void setVupdate(String vupdate) {
		this.vupdate = vupdate;
	}

	public Date getDupDate() {
		return dupDate;
	}

	public void setDupDate(Date dupDate) {
		this.dupDate = dupDate;
	}

	public String getVcomtel() {
		return vcomtel;
	}

	public void setVcomtel(String vcomtel) {
		this.vcomtel = vcomtel;
	}

	public String getVbuyreason() {
		return vbuyreason;
	}

	public void setVbuyreason(String vbuyreason) {
		this.vbuyreason = vbuyreason;
	}

	public String getVidtype() {
		return vidtype;
	}

	public void setVidtype(String vidtype) {
		this.vidtype = vidtype;
	}

	public String getVuserid() {
		return vuserid;
	}

	public void setVuserid(String vuserid) {
		this.vuserid = vuserid;
	}

	public Long getVpvid() {
		return vpvid;
	}

	public void setVpvid(Long vpvid) {
		this.vpvid = vpvid;
	}

	public Long getVciid() {
		return vciid;
	}

	public void setVciid(Long vciid) {
		this.vciid = vciid;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
