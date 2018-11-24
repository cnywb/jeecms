package com.jeecms.cms.entity.main;

import java.util.Date;

import com.jeecms.cms.entity.main.base.BaseFordMemberForm;

public class FordMemberForm extends BaseFordMemberForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4454848159049158756L;

	public FordMemberForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FordMemberForm(String vformId, String vformName, String vformTel,
			String vvin, String vcustomerid, String vcarid, String vcontactpid,
			String vcreated, Date dcrtDate, String vupdated, Date dupDate,
			String vnotes, String vdmsOwnerId) {
		super(vformId, vformName, vformTel, vvin, vcustomerid, vcarid, vcontactpid,
				vcreated, dcrtDate, vupdated, dupDate, vnotes, vdmsOwnerId);
		// TODO Auto-generated constructor stub
	}
	
	

}
