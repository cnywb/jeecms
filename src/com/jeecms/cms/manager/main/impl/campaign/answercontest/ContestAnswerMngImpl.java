package com.jeecms.cms.manager.main.impl.campaign.answercontest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.campaign.answercontest.ContestAnswerDao;
import com.jeecms.cms.manager.campaign.answercontest.ContestAnswerMng;


@Service
@Transactional(rollbackFor=Exception.class) 
public class ContestAnswerMngImpl implements ContestAnswerMng {

	
	@Autowired
	private ContestAnswerDao contestAnswerDao;
	
	
	
	
}
