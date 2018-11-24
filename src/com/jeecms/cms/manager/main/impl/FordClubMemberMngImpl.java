package com.jeecms.cms.manager.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.FordClubMemberDao;
import com.jeecms.cms.entity.main.FordClubMember;
import com.jeecms.cms.manager.main.FordClubMemberMng;
@Service
@Transactional
public class FordClubMemberMngImpl implements FordClubMemberMng {
	
	@Autowired
	private FordClubMemberDao dao;

	@Override
	public FordClubMember getFordClubMemberByVin(String vin) {
		return dao.getFordClubMemberByVin(vin);
	}

	@Override
	public FordClubMember save(FordClubMember bean) {
		return dao.save(bean);
	}

	@Override
	public String getSeq() {
		return dao.getSeq();
	}

	@Override
	public List<FordClubMember> getFordClubMemberByUid(Integer userId) {
		// TODO Auto-generated method stub
		return dao.getFordClubMemberByUid(userId);
	}

}
