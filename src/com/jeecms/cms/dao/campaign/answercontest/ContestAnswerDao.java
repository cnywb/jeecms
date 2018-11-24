package com.jeecms.cms.dao.campaign.answercontest;

import java.util.List;

import com.jeecms.cms.entity.campaign.answercontest.ContestAnswer;

public interface ContestAnswerDao {
	public List<ContestAnswer> findAllByAskId(long askId);

}
