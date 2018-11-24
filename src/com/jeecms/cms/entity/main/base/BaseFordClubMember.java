package com.jeecms.cms.entity.main.base;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseFordClubMember implements Serializable {

	public static String REF = "FordClubMember";
	public static String PRO_VCARD_ID ="vcardId";
	public static String PRO_VCUSTOMER_ID ="vcustomerId";
	public static String PRO_VCARD_NO ="vcardNo";
	public static String PRO_VCAR_ID ="vcarId";
	public static String PRO_VVIN ="vvin";
	public static String PRO_VNAME ="vname";
	public static String PRO_VGENDER ="vgender";
	public static String PRO_VBIRTHDAY ="vbirthday";
	public static String PRO_VIDNMB ="vidnmb";
	public static String PRO_VPROVINCE ="vprovince";
	public static String PRO_VCITY ="vcity";
	public static String PRO_VDIST ="vdist";
	public static String PRO_VADDRESS ="vaddress";
	public static String PRO_VZIPCODE ="vzipcode";
	public static String PRO_VMOBILE ="vmobile";
	public static String PRO_VTEL ="vtel";
	public static String PRO_VEMAIL ="vemail";
	public static String PRO_VLICENSE_NMB ="vlicenseNmb";
	public static String PRO_VCARD_TYPE ="vcardType";
	public static String PRO_VDEALER_ID ="vdealerId";
	public static String PRO_VOPEN_DATE ="vopenDate";
	public static String PRO_VCARD_STATUS ="vcardStatus";
	public static String PRO_NSCORE_GET ="nscoreGet";
	public static String PRO_NSCORE_USE ="nscoreUse";
	public static String PRO_NSCORE ="nscore";
	public static String PRO_VNOTES ="vnotes";
	public static String PRO_VCREATED ="vcreated";
	public static String PRO_DCRT_DATE ="dcrtDate";
	public static String PRO_VUPDATED ="vupdated";
	public static String PRO_DUP_DATE ="dupDate";
	public static String PRO_VCOMTEL ="vcomtel";
	public static String PRO_VBUYREASON ="vbuyreason";
	public static String PRO_VIDTYPE ="vidtype";
	public static String PRO_VPVID ="vpvid";
	public static String PRO_VCIID ="vciid";
	public static String PRO_VCARDNICKNAME ="vcardnickname";
	public static String PRO_USER_ID = "userId";

	public BaseFordClubMember() {
		initialize();
	}

	protected void initialize() {
	}

	public BaseFordClubMember(String vcardId, String vcustomerId,
			String vcardNo, String vcarId, String vvin, String vname,
			String vgender, String vbirthday, String vidnmb, String vprovince,
			String vcity, String vdist, String vaddress, String vzipcode,
			String vmobile, String vtel, String vemail, String vlicenseNmb,
			String vcardType, String vdealerId, String vopenDate,
			String vcardStatus, Long nscoreGet, Long nscoreUse, Long nscore,
			String vnotes, String vcreated, Date dcrtDate, String vupdated,
			Date dupDate, String vcomtel, String vbuyreason, String vidtype,
			Long vpvid, Long vciid, String vcardnickname, Integer userId) {
		super();
		this.vcardId = vcardId;
		this.vcustomerId = vcustomerId;
		this.vcardNo = vcardNo;
		this.vcarId = vcarId;
		this.vvin = vvin;
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
		this.vlicenseNmb = vlicenseNmb;
		this.vcardType = vcardType;
		this.vdealerId = vdealerId;
		this.vopenDate = vopenDate;
		this.vcardStatus = vcardStatus;
		this.nscoreGet = nscoreGet;
		this.nscoreUse = nscoreUse;
		this.nscore = nscore;
		this.vnotes = vnotes;
		this.vcreated = vcreated;
		this.dcrtDate = dcrtDate;
		this.vupdated = vupdated;
		this.dupDate = dupDate;
		this.vcomtel = vcomtel;
		this.vbuyreason = vbuyreason;
		this.vidtype = vidtype;
		this.vpvid = vpvid;
		this.vciid = vciid;
		this.vcardnickname = vcardnickname;
		this.userId = userId;
	}







	// 俱乐部会员编号
	private String vcardId;
	// 客户编号
	private String vcustomerId;
	// 俱乐部卡号
	private String vcardNo;
	// 车辆编号
	private String vcarId;
	// Vin码
	private String vvin;
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
	// 牌照号码
	private String vlicenseNmb;
	// 会员卡类型
	private String vcardType;
	// 经销商编号
	private String vdealerId;
	// 开卡日期(20090101)
	private String vopenDate;
	// 会员卡状态
	private String vcardStatus;
	// 累计获得积分
	private Long nscoreGet;
	// 累计使用积分
	private Long nscoreUse;
	// 当前剩余积分(每天重算)
	private Long nscore;
	// 备注
	private String vnotes;
	// 创建人
	private String vcreated;
	// 创建日期
	private Date dcrtDate;
	// 更新人
	private String vupdated;
	// 更新日期
	private Date dupDate;
	// 公司电话
	private String vcomtel;
	// 01：个人，02：公司
	private String vbuyreason;
	// 证件类型
	private String vidtype;
	// 省编号
	private Long vpvid;
	// 市编号
	private Long vciid;
	// 俱乐部卡号别名
	private String vcardnickname;
	//用户编号
	private Integer userId;

	public String getVcardId() {
		return vcardId;
	}

	public void setVcardId(String vcardId) {
		this.vcardId = vcardId;
	}

	public String getVcustomerId() {
		return vcustomerId;
	}

	public void setVcustomerId(String vcustomerId) {
		this.vcustomerId = vcustomerId;
	}

	public String getVcardNo() {
		return vcardNo;
	}

	public void setVcardNo(String vcardNo) {
		this.vcardNo = vcardNo;
	}

	public String getVcarId() {
		return vcarId;
	}

	public void setVcarId(String vcarId) {
		this.vcarId = vcarId;
	}

	public String getVvin() {
		return vvin;
	}

	public void setVvin(String vvin) {
		this.vvin = vvin;
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

	public String getVlicenseNmb() {
		return vlicenseNmb;
	}

	public void setVlicenseNmb(String vlicenseNmb) {
		this.vlicenseNmb = vlicenseNmb;
	}

	public String getVcardType() {
		return vcardType;
	}

	public void setVcardType(String vcardType) {
		this.vcardType = vcardType;
	}

	public String getVdealerId() {
		return vdealerId;
	}

	public void setVdealerId(String vdealerId) {
		this.vdealerId = vdealerId;
	}

	public String getVopenDate() {
		return vopenDate;
	}

	public void setVopenDate(String vopenDate) {
		this.vopenDate = vopenDate;
	}

	public String getVcardStatus() {
		return vcardStatus;
	}

	public void setVcardStatus(String vcardStatus) {
		this.vcardStatus = vcardStatus;
	}

	public Long getNscoreGet() {
		return nscoreGet;
	}

	public void setNscoreGet(Long nscoreGet) {
		this.nscoreGet = nscoreGet;
	}

	public Long getNscoreUse() {
		return nscoreUse;
	}

	public void setNscoreUse(Long nscoreUse) {
		this.nscoreUse = nscoreUse;
	}

	public Long getNscore() {
		return nscore;
	}

	public void setNscore(Long nscore) {
		this.nscore = nscore;
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

	public String getVcardnickname() {
		return vcardnickname;
	}

	public void setVcardnickname(String vcardnickname) {
		this.vcardnickname = vcardnickname;
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
