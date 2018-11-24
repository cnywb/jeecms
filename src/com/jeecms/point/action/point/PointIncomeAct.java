package com.jeecms.point.action.point;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.point.entity.PointCalculateResult;
import com.jeecms.point.manager.point.PointCalculateMng;
import com.jeecms.point.manager.point.PointIncomeMng;
import com.jeecms.point.vo.point.PointIncomeQueryVo;
import com.jeecms.point.web.query.QueryVo;

@Controller
@RequestMapping("/points/")
public class PointIncomeAct {
	
	@Autowired
	private PointIncomeMng pointIncomeMng;
	
	private static final Logger logger = LoggerFactory.getLogger(PointIncomeAct.class);
	
	/** 首页 */
	private static final String PERSON_INDEX_RETURN = "/WEB-INF/t/cms/www/red/points/personal/index.html";
	
	 
	@Autowired
	private BbsUserMng bbsUserMng;
	@Autowired
	private UnifiedUserMng  unifiedUserMng;
	
	@Autowired
	private PointCalculateMng pointCalculateMng;
	
	@RequestMapping("income/index.jhtml")
	@ResponseBody
	public String index(){
		Integer userId=1;
		//oolean result=pointIncomeMng.authUserPoint(RuleConstant.QUESTIONARY, userId,Channel.PC);
		//userId=500050;
		//result=pointIncomeMng.authUserPoint(RuleConstant.AUTH_USER, userId,Channel.PC);
		//result=pointIncomeMng.authUserPoint(RuleConstant.ISSUE_TOPIC,userId,Channel.PC);
		 
		//PointCalculateResult calculateResult=pointCalculateMng.perfactUserInfo(userId, Channel.PC,13);
		PointCalculateResult	calculateResult=null;
		for(int i=0;i<51;i++){
			calculateResult=pointCalculateMng.replyTopic(userId);
		}
		return  String.valueOf(calculateResult.isResult());
	}
	
	@RequestMapping("personal/point.jhtml")
	public String  psersonalPoint(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		CmsUser user = CmsUtils.getUser(request);
		if(user!=null){
			UnifiedUser  unifiedUser=unifiedUserMng.findById(user.getId());
			try {
				response.getWriter().write(unifiedUser.getCurrentPoint()+"");
				response.getWriter().flush();
				response.getWriter().close();
				response.flushBuffer();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
		}
		return StringUtils.EMPTY;
	}
	
	
	@RequestMapping("personal/index.jhtml")
	public String personIndex(QueryVo queryVo,PointIncomeQueryVo pointIncomeQueryVo,HttpServletRequest request, HttpServletResponse response,ModelMap model){
		logger.info("进入积分查询...");
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request,model,site);
		FrontUtils.frontPageData(request, model);
		
		 
		//判断是否登录
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		UnifiedUser  unifiedUser=unifiedUserMng.findById(user.getId());
		BbsUser bbsUser=bbsUserMng.findById(user.getId());
		model.addAttribute("unifiedUser",unifiedUser);
		model.addAttribute("bbsUser",bbsUser);
		//查询日期
		Calendar calendar=Calendar.getInstance();
		Date  newDate=calendar.getTime();
		calendar.add(Calendar.MONTH,-1);
		String endDateDefault=DateUtils.format(newDate,DateUtils.FORMAT_DATE_DEFAULT);
		String startDateDefault=DateUtils.format(calendar.getTime(),DateUtils.FORMAT_DATE_DEFAULT);
		//结束日期
		model.addAttribute("maxDate", endDateDefault);
	
		//加载积分的数据
		if(user.getGroup().getId().equals(2)){
			//查询的开始日期
			if(StringUtils.isEmpty(pointIncomeQueryVo.getStartDate())){
				pointIncomeQueryVo.setStartDate(startDateDefault);
			}
			//查询结束日期
			if(StringUtils.isEmpty(pointIncomeQueryVo.getEndDate())){
				pointIncomeQueryVo.setEndDate(endDateDefault);
			}
			model.addAttribute("endDate",pointIncomeQueryVo.getEndDate());
			model.addAttribute("startDate",pointIncomeQueryVo.getStartDate());
		    //加载个人数据
			pointIncomeQueryVo.setUserId(Long.valueOf(user.getId()));
			Pagination pagination=this.pointIncomeMng.queryPaginationByUserId(queryVo,pointIncomeQueryVo);
			model.addAttribute("points", pagination.getList());
			model.put("pagination", pagination);
			return PERSON_INDEX_RETURN;
		}
		return PERSON_INDEX_RETURN;
	}
}
