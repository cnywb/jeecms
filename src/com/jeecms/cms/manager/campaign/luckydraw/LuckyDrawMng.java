package com.jeecms.cms.manager.campaign.luckydraw;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jeecms.cms.entity.campaign.answercontest.ContestAskAndAnswer;
import com.jeecms.cms.entity.campaign.luckydraw.LuckyDraw;
import com.jeecms.cms.entity.campaign.luckydraw.LuckyDrawResponse;

public interface LuckyDrawMng {
	public LuckyDrawResponse doDraw(HttpServletRequest request,String code);
	public List<LuckyDraw> findAllByCurrentDate();
	public LuckyDrawResponse doDrawForAnswerContest(HttpServletRequest request,String code,List<ContestAskAndAnswer> askAndAnswers);
}
