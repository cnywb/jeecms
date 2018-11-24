package com.jeecms.core.entity;

import java.util.Date;

import com.jeecms.core.entity.base.BaseMd5pwd;

public class Md5pwd  extends BaseMd5pwd{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Md5pwd() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Md5pwd(Integer id, String pwd, String jspwd, String encryptpwd,
			String createuser, Date createdate, String updateuser,
			Date updatedate, Integer flag, String notes) {
		super(id, pwd, jspwd, encryptpwd, createuser, createdate, updateuser,
				updatedate, flag, notes);
		// TODO Auto-generated constructor stub
	}
	
	

}
