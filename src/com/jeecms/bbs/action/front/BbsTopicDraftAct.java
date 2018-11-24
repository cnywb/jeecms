package com.jeecms.bbs.action.front;




import static com.jeecms.common.page.SimplePage.cpn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.bbs.entity.BbsTopicDraft;
import com.jeecms.bbs.manager.BbsTopicDraftMng;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.MemberConfig;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.JSONUtil;
import com.jeecms.common.web.ResponseUtils;


@Controller@Scope("prototype")
@RequestMapping("/topic/*")
public class BbsTopicDraftAct {
	
   @Autowired
   private BbsTopicDraftMng bbsTopicDraftMng;

   
    @RequestMapping(value="findAllBbsTopicDraft.jspx",method = RequestMethod.POST)
	public void findAll(HttpServletResponse response,HttpServletRequest request,ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		 CmsUser user = CmsUtils.getUser(request);
		String json=JSONUtil.objectToJson(bbsTopicDraftMng.findAllByUserId(user.getId()));
		ResponseUtils.renderJson(response,json);
	}
    
    @RequestMapping(value="saveBbsTopicDraft.jspx",method = RequestMethod.POST)
 	public void save(HttpServletResponse response,HttpServletRequest request,ModelMap model,BbsTopicDraft t) {
 		CmsSite site = CmsUtils.getSite(request);
 		FrontUtils.frontData(request, model, site);
 		 CmsUser user = CmsUtils.getUser(request);
 		 t.setUserId(user.getId());
 		String json=JSONUtil.objectToJson(bbsTopicDraftMng.save(t));
 		ResponseUtils.renderJson(response,json);
 	}
    
    @RequestMapping(value="deleteBbsTopicDraft.jspx",method=RequestMethod.POST)
 	public void delete(HttpServletResponse response,HttpServletRequest request,ModelMap model,long id) {
 		CmsSite site = CmsUtils.getSite(request);
 		FrontUtils.frontData(request, model, site);
 	     CmsUser user = CmsUtils.getUser(request);
  		String json=String.valueOf(bbsTopicDraftMng.deleteByIdAndUserId(id, user.getId()));
 		ResponseUtils.renderJson(response,json);
 	}
    
	@RequestMapping(value = "/myTopicDraft.jspx")
	public String myguestbook(Integer pageNo, Integer ctgId,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		Pagination pagination = bbsTopicDraftMng.getPage(cpn(pageNo),10);
		model.addAttribute("pagination", pagination);
		model.addAttribute("url","myguestbook.jspx");
		model.addAttribute("ctgId",ctgId);
		return "/WEB-INF/t/cms/www/blue/member/topic_draft.html";
	}

   
}
