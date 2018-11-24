package com.jeecms.core.entity.base;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseMd5pwd implements Serializable {
	public static String REF = "Md5pwd";
	public static String PROP_PWD = "pwd";
	public static String PROP_JSPWD = "jspwd";
	public static String PROP_ENCRYPTPWD = "encryptpwd";
	public static String PROP_CREATEUSER = "createuser";
	public static String PROP_CREATEDATE = "createdate";
	public static String PROP_UPDATEUSER = "updateuser";
	public static String PROP_UPDATEDATE = "updatedate";
	public static String PROP_FLAG = "flag";
	public static String PROP_NOTES = "notes";

	public BaseMd5pwd() {
		initialize();
	}

	public BaseMd5pwd(Integer id, String pwd, String jspwd, String encryptpwd,
			String createuser, Date createdate, String updateuser,
			Date updatedate, Integer flag, String notes) {
		this.id = id;
		this.pwd = pwd;
		this.jspwd = jspwd;
		this.encryptpwd = encryptpwd;
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

	private Integer id;
	private String pwd;
	private String jspwd;
	private String encryptpwd;
	private String createuser;
	private Date createdate;
	private String updateuser;
	private Date updatedate;
	private Integer flag;
	private String notes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getJspwd() {
		return jspwd;
	}

	public void setJspwd(String jspwd) {
		this.jspwd = jspwd;
	}

	public String getEncryptpwd() {
		return encryptpwd;
	}

	public void setEncryptpwd(String encryptpwd) {
		this.encryptpwd = encryptpwd;
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

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
