package com.jeecms.cms.entity.main.base;

import java.io.Serializable;
import java.util.Date;



public class BaseClubUser implements Serializable{
	public static String REF = "ClubUser";
	public static String PROP_UUID ="uuid";
	public static String PROP_UNAME ="uname";
	public static String PROP_MID ="mid";
	public static String PROP_DLID ="dlid";
	public static String PROP_UPASS ="upass";
	public static String PROP_UEMAIL ="uemail";
	public static String PROP_URNAME ="urname";
	public static String PROP_USEX ="usex";
	public static String PROP_UBIRTH ="ubirth";
	public static String PROP_UADRESS ="uadress";
	public static String PROP_UPROVINCE ="uprovince";
	public static String PROP_UCITY ="ucity";
	public static String PROP_UAREA ="uarea";
	public static String PROP_UZIP ="uzip";
	public static String PROP_UIDENNAME ="uidenname";
	public static String PROP_UIDENNUM ="uidennum";
	public static String PROP_UPHONENUM ="uphonenum";
	public static String PROP_UTEL ="utel";
	public static String PROP_UMSN ="umsn";
	public static String PROP_UQQ ="uqq";
	public static String PROP_UARGSUB ="uargsub";
	public static String PROP_UNICKNAME ="unickname";
	public static String PROP_ULASTDATE ="ulastdate";
	public static String PROP_ULASTIP ="ulastip";
	public static String PROP_UCURRENTDATE ="ucurrentdate";
	public static String PROP_UCURRENTIP ="ucurrentip";
	public static String PROP_ULOGINCOUNT ="ulogincount";
	public static String PROP_USCORE ="uscore";
	public static String PROP_UFORUMSCORE ="uforumscore";
	public static String PROP_USIG ="usig";
	public static String PROP_UIMG ="uimg";
	public static String PROP_UFORUMSTATE ="uforumstate";
	public static String PROP_UCLUBSTATE ="uclubstate";
	public static String PROP_UPVID ="upvid";
	public static String PROP_UCID ="ucid";
	public static String PROP_UHOMETEL ="uhometel";
	public static String PROP_CREATEUSER ="createuser";
	public static String PROP_CREATEDATE ="createdate";
	public static String PROP_UPDATEUSER ="updateuser";
	public static String PROP_UPDATEDATE ="updatedate";
	public static String PROP_FLAG ="flag";
	public static String PROP_NOTES ="notes";
	public static String PROP_RANDOM ="random";
	public static String PROP_RETPWDDATE ="retpwddate";
	public static String PROP_POSTCOUNT ="postcount";
	public static String PROP_THREADCOUNT ="threadcount";
	
	public BaseClubUser() {
		initialize();
		// TODO Auto-generated constructor stub
	}

	public BaseClubUser(String uuid, String uname, Integer mid, String dlid,
			String upass, String uemail, String urname, String usex,
			Date ubirth, String uadress, String uprovince, String ucity,
			String uarea, String uzip, String uidenname, String uidennum,
			String uphonenum, String utel, String umsn, String uqq,
			Long uargsub, String unickname, Date ulastdate, String ulastip,
			Date ucurrentdate, String ucurrentip, Long ulogincount,
			Long uscore, Long uforumscore, String usig, String uimg,
			Long uforumstate, Long uclubstate, Long upvid, Long ucid,
			String uhometel, String createuser, Date createdate,
			String updateuser, Date updatedate, Long flag, String notes,
			String random, Date retpwddate, Long postcount, Long threadcount) {
		this.uuid = uuid;
		this.uname = uname;
		this.mid = mid;
		this.dlid = dlid;
		this.upass = upass;
		this.uemail = uemail;
		this.urname = urname;
		this.usex = usex;
		this.ubirth = ubirth;
		this.uadress = uadress;
		this.uprovince = uprovince;
		this.ucity = ucity;
		this.uarea = uarea;
		this.uzip = uzip;
		this.uidenname = uidenname;
		this.uidennum = uidennum;
		this.uphonenum = uphonenum;
		this.utel = utel;
		this.umsn = umsn;
		this.uqq = uqq;
		this.uargsub = uargsub;
		this.unickname = unickname;
		this.ulastdate = ulastdate;
		this.ulastip = ulastip;
		this.ucurrentdate = ucurrentdate;
		this.ucurrentip = ucurrentip;
		this.ulogincount = ulogincount;
		this.uscore = uscore;
		this.uforumscore = uforumscore;
		this.usig = usig;
		this.uimg = uimg;
		this.uforumstate = uforumstate;
		this.uclubstate = uclubstate;
		this.upvid = upvid;
		this.ucid = ucid;
		this.uhometel = uhometel;
		this.createuser = createuser;
		this.createdate = createdate;
		this.updateuser = updateuser;
		this.updatedate = updatedate;
		this.flag = flag;
		this.notes = notes;
		this.random = random;
		this.retpwddate = retpwddate;
		this.postcount = postcount;
		this.threadcount = threadcount;
		initialize();
	}

	protected void initialize() {
	}
	
	//用户编号
	private String uuid;
	//用户名称
	private String uname;
	//会员类别编号
	private Integer mid;
	//用户选定经销商
	private String dlid;
	//密码
	private String upass;
	//Email
	private String uemail;
	//真实姓名
	private String urname;
	//性别
	private String usex;
	//生日
	private Date ubirth;
	//详细地址
	private String uadress;
	//省
	private String uprovince;
	//市
	private String ucity;
	//区
	private String uarea;
	//邮编
	private String uzip;
	//证件名称
	private String uidenname;
	//证件号码
	private String uidennum;
	//手机
	private String uphonenum;
	//固话
	private String utel;
	//MSN
	private String umsn;
	//QQ
	private String uqq;
	//订阅同意
	private Long uargsub;
	//社区昵称
	private String unickname;
	//上次登录时间
	private Date ulastdate;
	//上次登录IP
	private String ulastip;
	//当前登录时间
	private Date ucurrentdate;
	//当前登录IP
	private String ucurrentip;
	//总计登录次数
	private Long ulogincount;
	//总积分
	private Long uscore;
	//社区总积分
	private Long uforumscore;
	//社区个性签名
	private String usig;
	//社区个性头像
	private String uimg;
	//社区登录状态
	private Long uforumstate;
	//俱乐部登录状态
	private Long uclubstate;
	//省编号
	private Long upvid;
	//市编号
	private Long ucid;
	//家里电话
	private String uhometel;
	//创建人
	private String createuser;
	//创建时间
	private Date createdate;
	//修改人
	private String updateuser;
	//修改时间
	private Date updatedate;
	//状态
	private Long flag;
	//备注
	private String notes;
	//注册后生产随机数，用于邮箱验证
	private String random;
	//找回密码的时间，24小时内只能找回一次
	private Date retpwddate;
	//发帖数
	private Long postcount;
	//回帖数
	private Long threadcount;
	
	

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getDlid() {
		return dlid;
	}

	public void setDlid(String dlid) {
		this.dlid = dlid;
	}

	public String getUpass() {
		return upass;
	}

	public void setUpass(String upass) {
		this.upass = upass;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getUrname() {
		return urname;
	}

	public void setUrname(String urname) {
		this.urname = urname;
	}

	public String getUsex() {
		return usex;
	}

	public void setUsex(String usex) {
		this.usex = usex;
	}

	public Date getUbirth() {
		return ubirth;
	}

	public void setUbirth(Date ubirth) {
		this.ubirth = ubirth;
	}

	public String getUadress() {
		return uadress;
	}

	public void setUadress(String uadress) {
		this.uadress = uadress;
	}

	public String getUprovince() {
		return uprovince;
	}

	public void setUprovince(String uprovince) {
		this.uprovince = uprovince;
	}

	public String getUcity() {
		return ucity;
	}

	public void setUcity(String ucity) {
		this.ucity = ucity;
	}

	public String getUarea() {
		return uarea;
	}

	public void setUarea(String uarea) {
		this.uarea = uarea;
	}

	public String getUzip() {
		return uzip;
	}

	public void setUzip(String uzip) {
		this.uzip = uzip;
	}

	public String getUidenname() {
		return uidenname;
	}

	public void setUidenname(String uidenname) {
		this.uidenname = uidenname;
	}

	public String getUidennum() {
		return uidennum;
	}

	public void setUidennum(String uidennum) {
		this.uidennum = uidennum;
	}

	public String getUphonenum() {
		return uphonenum;
	}

	public void setUphonenum(String uphonenum) {
		this.uphonenum = uphonenum;
	}

	public String getUtel() {
		return utel;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public String getUmsn() {
		return umsn;
	}

	public void setUmsn(String umsn) {
		this.umsn = umsn;
	}

	public String getUqq() {
		return uqq;
	}

	public void setUqq(String uqq) {
		this.uqq = uqq;
	}

	public Long getUargsub() {
		return uargsub;
	}

	public void setUargsub(Long uargsub) {
		this.uargsub = uargsub;
	}

	public String getUnickname() {
		return unickname;
	}

	public void setUnickname(String unickname) {
		this.unickname = unickname;
	}

	public Date getUlastdate() {
		return ulastdate;
	}

	public void setUlastdate(Date ulastdate) {
		this.ulastdate = ulastdate;
	}

	public String getUlastip() {
		return ulastip;
	}

	public void setUlastip(String ulastip) {
		this.ulastip = ulastip;
	}

	public Date getUcurrentdate() {
		return ucurrentdate;
	}

	public void setUcurrentdate(Date ucurrentdate) {
		this.ucurrentdate = ucurrentdate;
	}

	public String getUcurrentip() {
		return ucurrentip;
	}

	public void setUcurrentip(String ucurrentip) {
		this.ucurrentip = ucurrentip;
	}

	public Long getUlogincount() {
		return ulogincount;
	}

	public void setUlogincount(Long ulogincount) {
		this.ulogincount = ulogincount;
	}

	public Long getUscore() {
		return uscore;
	}

	public void setUscore(Long uscore) {
		this.uscore = uscore;
	}

	public Long getUforumscore() {
		return uforumscore;
	}

	public void setUforumscore(Long uforumscore) {
		this.uforumscore = uforumscore;
	}

	public String getUsig() {
		return usig;
	}

	public void setUsig(String usig) {
		this.usig = usig;
	}

	public String getUimg() {
		return uimg;
	}

	public void setUimg(String uimg) {
		this.uimg = uimg;
	}

	public Long getUforumstate() {
		return uforumstate;
	}

	public void setUforumstate(Long uforumstate) {
		this.uforumstate = uforumstate;
	}

	public Long getUclubstate() {
		return uclubstate;
	}

	public void setUclubstate(Long uclubstate) {
		this.uclubstate = uclubstate;
	}

	public Long getUpvid() {
		return upvid;
	}

	public void setUpvid(Long upvid) {
		this.upvid = upvid;
	}

	public Long getUcid() {
		return ucid;
	}

	public void setUcid(Long ucid) {
		this.ucid = ucid;
	}

	public String getUhometel() {
		return uhometel;
	}

	public void setUhometel(String uhometel) {
		this.uhometel = uhometel;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public Long getFlag() {
		return flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public Date getRetpwddate() {
		return retpwddate;
	}

	public void setRetpwddate(Date retpwddate) {
		this.retpwddate = retpwddate;
	}

	public Long getPostcount() {
		return postcount;
	}

	public void setPostcount(Long postcount) {
		this.postcount = postcount;
	}

	public Long getThreadcount() {
		return threadcount;
	}

	public void setThreadcount(Long threadcount) {
		this.threadcount = threadcount;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	
}
