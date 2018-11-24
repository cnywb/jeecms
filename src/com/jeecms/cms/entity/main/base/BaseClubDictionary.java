package com.jeecms.cms.entity.main.base;

import java.io.Serializable;
import java.util.Date;

public class BaseClubDictionary implements Serializable {
	public static String REF = "ClubDictionary";
	public static String PROP_CDID ="cdid";
	public static String PROP_CDTYPE ="cdtype";
	public static String PROP_CDCODE ="cdcode";
	public static String PROP_CDNAME ="cdname";
	public static String PROP_CREATEUSER ="createuser";
	public static String PROP_CREATEDATE ="createdate";
	public static String PROP_UPDATEUSER ="updateuser";
	public static String PROP_UPDATEDATE ="updatedate";
	public static String PROP_FLAG ="flag";
	public static String PROP_NOTES ="notes";
	
	
	public BaseClubDictionary() {
		 initialize();
	}

	public BaseClubDictionary(Long cdid, String cdtype, String cdcode,
			String cdname, String createuser, Date createdate,
			String updateuser, Date updatedate, Long flag, String notes) {
		this.cdid = cdid;
		this.cdtype = cdtype;
		this.cdcode = cdcode;
		this.cdname = cdname;
		this.createuser = createuser;
		this.createdate = createdate;
		this.updateuser = updateuser;
		this.updatedate = updatedate;
		this.flag = flag;
		this.notes = notes;
		initialize();
	}

	protected void initialize() {
	}

	// 字典编号
	private Long cdid;
	// 变量类别
	private String cdtype;
	// 变量代码
	private String cdcode;
	// 变量名称
	private String cdname;

	// 创建人
	private String createuser;
	// 创建时间
	private Date createdate;
	// 修改人
	private String updateuser;
	// 修改时间
	private Date updatedate;
	// 状态
	private Long flag;
	// 备注
	private String notes;
	
	public Long getCdid() {
		return cdid;
	}

	public void setCdid(Long cdid) {
		this.cdid = cdid;
	}

	public String getCdtype() {
		return cdtype;
	}

	public void setCdtype(String cdtype) {
		this.cdtype = cdtype;
	}

	public String getCdcode() {
		return cdcode;
	}

	public void setCdcode(String cdcode) {
		this.cdcode = cdcode;
	}

	public String getCdname() {
		return cdname;
	}

	public void setCdname(String cdname) {
		this.cdname = cdname;
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

	public String toString () {
		return super.toString();
	}

}
