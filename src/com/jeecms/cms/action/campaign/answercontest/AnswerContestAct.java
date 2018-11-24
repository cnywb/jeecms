package com.jeecms.cms.action.campaign.answercontest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jeecms.cms.entity.campaign.luckydraw.LuckyDraw;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.manager.campaign.answercontest.ContestAskMng;
import com.jeecms.cms.manager.campaign.luckydraw.LuckyDrawMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.util.JSONUtil;
import com.jeecms.common.web.ResponseUtils;


@Controller@Scope("prototype")
@RequestMapping("/campaign/answercontest/*")
public class AnswerContestAct {
	
	
	@Autowired
	private LuckyDrawMng luckyDrawMng;
	
	@Autowired
	private ContestAskMng contestAskMng;
	
	
	@RequestMapping(value="index.jspx")
	public String index(HttpServletRequest request,ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		List<LuckyDraw> list=luckyDrawMng.findAllByCurrentDate();//得到今天的抽奖活动
		if(list.size()>0){
			model.addAttribute("t", list.get(0));//活动的 code就是竞答的group由此来加载题目
		}
		return "/WEB-INF/t/cms/www/red/campaign/answercontest/index.html";
	}
	
	
	@RequestMapping(value="findAllAskByGroup.jspx")
	public void findAllAskByGroup(HttpServletResponse response,HttpServletRequest request,ModelMap model,String group) {
		String json=JSONUtil.objectToJson(contestAskMng.findAllByGroup(group));
		ResponseUtils.renderJson(response,json);
	}
	
	
	@RequestMapping(value="isAnswersCorrect.jspx")
	public void isAnswersCorrect(HttpServletResponse response,HttpServletRequest request,ModelMap model,String  askCode,String answerCodes) {
		JSONObject object = new JSONObject();
		try {
			boolean status=contestAskMng.isAnswersCorrect(askCode, answerCodes);
			object.put("status",status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
	}
	
	
	
	
	

}
