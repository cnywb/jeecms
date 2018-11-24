package com.jeecms.cms.entity.main;

import java.util.Date;

import com.jeecms.cms.entity.main.base.BaseClubUser;

public class ClubUser extends BaseClubUser{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2529141573009309068L;

	public ClubUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClubUser(String uuid, String uname, Integer mid, String dlid,
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
		super(uuid, uname, mid, dlid, upass, uemail, urname, usex, ubirth, uadress,
				uprovince, ucity, uarea, uzip, uidenname, uidennum, uphonenum, utel,
				umsn, uqq, uargsub, unickname, ulastdate, ulastip, ucurrentdate,
				ucurrentip, ulogincount, uscore, uforumscore, usig, uimg, uforumstate,
				uclubstate, upvid, ucid, uhometel, createuser, createdate, updateuser,
				updatedate, flag, notes, random, retpwddate, postcount, threadcount);
		// TODO Auto-generated constructor stub
	}
	
	

}
