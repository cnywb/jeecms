package com.jeecms.bbs.action.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.manager.BbsForumModeratorMng;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.manager.AuthenticationMng;


@Controller@Scope("prototype")

@RequestMapping("/jeebbs/forum/*")
public class BbsForumModeratorAct {

	@Autowired
	private BbsForumModeratorMng bbsForumModeratorMng;
	@Autowired
	private SessionProvider session;
	@Autowired
	private AuthenticationMng authenticationMng;
	@Autowired
	private BbsUserMng bbsUserMng;
	
	  @RequestMapping(value="deleteBbsForumModerator.jspx")
	 	public void delete(HttpServletResponse response,HttpServletRequest request,ModelMap model,Long id) {
	 		CmsSite site = CmsUtils.getSite(request);
	 		FrontUtils.frontData(request, model, site);
	 		
	 		 BbsUser user = CmsUtils.getBbsUser(request);
	 		if(!user.getUsername().equals("admin")){
	 			ResponseUtils.renderJson(response,3+"");
	 		}
	 		String json=bbsForumModeratorMng.deleteById(id)+"";
	 		ResponseUtils.renderJson(response,json);
	 	}
	
	@RequestMapping(value="bbsForumModeratorList.jspx")
	public String applicationlist(String forumId,String createTimeMin,String createTimeMax,String updateTimeMin,String updateTimeMax, Integer pageNo,Integer pageSize, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		Integer userId=authenticationMng.retrieveUserIdFromSession(session, request);
		BbsUser user=bbsUserMng.findById(userId);
	 	if(user==null){
	 			return "";
	 	}
	 	if(!user.getAdmin()){
 			return "";
 	    }
		if(pageNo==null){
			pageNo=1;
		}
		if(pageSize==null){
			pageSize=10;
		}
		Pagination pagination = bbsForumModeratorMng.getPage(forumId,createTimeMin, createTimeMax, updateTimeMin, updateTimeMax, pageNo, pageSize);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pagination.getPageNo());
 		model.addAttribute("forumId",forumId);
		model.addAttribute("createTimeMin", createTimeMin);
		model.addAttribute("createTimeMax", createTimeMax);
		model.addAttribute("updateTimeMin", updateTimeMin);
		model.addAttribute("updateTimeMax", updateTimeMax);
		return "/WEB-INF/jeebbs_sys/forum/forum_moderator_list.html";
	}
}
