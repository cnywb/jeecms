package com.jeecms.cms.entity.main;

import java.util.Date;

import com.jeecms.cms.entity.main.base.BaseClubDictionary;

public class ClubDictionary extends BaseClubDictionary {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2758848497092602064L;

	public ClubDictionary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClubDictionary(Long cdid, String cdtype, String cdcode,
			String cdname, String createuser, Date createdate,
			String updateuser, Date updatedate, Long flag, String notes) {
		super(cdid, cdtype, cdcode, cdname, createuser, createdate, updateuser,
				updatedate, flag, notes);
		// TODO Auto-generated constructor stub
	}

}
