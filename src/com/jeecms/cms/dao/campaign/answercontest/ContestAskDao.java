package com.jeecms.cms.dao.campaign.answercontest;

import java.util.List;

import com.jeecms.cms.entity.campaign.answercontest.ContestAsk;

public interface ContestAskDao {
	public List<ContestAsk> findAllByGroup(String group);
	public ContestAsk findByCode(String code);
}
