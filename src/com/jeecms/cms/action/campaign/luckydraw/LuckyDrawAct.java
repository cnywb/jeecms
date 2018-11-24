package com.jeecms.cms.action.campaign.luckydraw;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.entity.campaign.answercontest.ContestAskAndAnswer;
import com.jeecms.cms.entity.campaign.answercontest.ContestLuckyDrawResquest;
import com.jeecms.cms.manager.campaign.luckydraw.LuckyDrawMng;
import com.jeecms.common.web.ResponseUtils;



@Controller@Scope("prototype")
@RequestMapping("/campaign/luckydraw/*")
public class LuckyDrawAct {
	
	@Autowired
	private LuckyDrawMng luckyDrawMng;
	
	@RequestMapping(value="doDraw.jspx")
	public void doDraw(HttpServletResponse response,HttpServletRequest request,String code){
			ResponseUtils.renderJson(response, luckyDrawMng.doDraw(request, code).toString());
	}

	
	/**
	 * 竞答活动抽奖
	 * @param response
	 * @param request
	 * @param code
	 * @param askAndAnswers
	 */
	@RequestMapping(value="doDrawForAnswerContest.jspx")
	public void doDrawForAnswerContest(HttpServletResponse response,HttpServletRequest request,ContestLuckyDrawResquest req){
			ResponseUtils.renderJson(response, luckyDrawMng.doDrawForAnswerContest(request, req.getCode(), req.getAskAndAnswers()).toString());
	}
	
}
