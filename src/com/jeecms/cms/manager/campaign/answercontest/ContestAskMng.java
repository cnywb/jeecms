package com.jeecms.cms.manager.campaign.answercontest;

import java.util.List;

import com.jeecms.cms.entity.campaign.answercontest.ContestAsk;
import com.jeecms.cms.entity.campaign.answercontest.ContestAskAndAnswer;

public interface ContestAskMng {
	public List<ContestAsk> findAllByGroup(String group);
	public boolean isAnswersCorrect(String askCode,String answerCodes);
	public boolean areAllAnswersCorrect(List<ContestAskAndAnswer> askAndAnswers);
}
