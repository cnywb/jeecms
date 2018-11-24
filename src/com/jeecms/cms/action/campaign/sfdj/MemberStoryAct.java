package com.jeecms.cms.action.campaign.sfdj;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.entity.campaign.sfdj.MemberStory;
import com.jeecms.cms.entity.campaign.sfdj.MemberStoryPraise;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.manager.campaign.sfdj.MemberStoryMng;
import com.jeecms.cms.manager.campaign.sfdj.MemberStoryPraiseMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.JSONUtil;
import com.jeecms.common.web.ResponseUtils;


@Controller@Scope("prototype")
@RequestMapping("/campaign/sfdj/*")
public class MemberStoryAct {
	
	@Autowired
	private MemberStoryMng memberStoryMng;
	
	@Autowired
	private MemberStoryPraiseMng memberStoryPraiseMng;

	
	
	
	@RequestMapping(value="index.jspx")
	public String index(HttpServletRequest request,ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		 CmsUser user = CmsUtils.getUser(request);
		 if(user!=null&&user.getUsername().equals("admin")){
			 Pagination pagination =memberStoryMng.getPage("", "", "", "", 1, 2); 
			 model.addAttribute("pagination", pagination);
		 }else{
	        Pagination pagination=memberStoryMng.getPageForPass(1, 2);
		    model.addAttribute("pagination", pagination);
		}
		
		return "/WEB-INF/t/cms/www/red/campaign/sfdj/index.html";
	}
	
	@RequestMapping(value="share.jspx")
	public String share(HttpServletRequest request,ModelMap model,Long id,String bdUrl) {
		CmsSite site = CmsUtils.getSite(request);
		bdUrl=bdUrl.replaceAll("：",":"); // 符号":"作为参数值提交时会被转成 "：" 来防止与http:中的:搞乱，所以我们在此处还原回来,此处不能用request.getHeader("Referer")来得到bdUrl ，因为当前会从https跳转到http协议
		MemberStory memberStory=memberStoryMng.findById(id);
		model.addAttribute("bdText", "我的全家福");
		model.addAttribute("bdDesc", memberStory==null?"":memberStory.getContent());
		model.put("bdUrl",bdUrl);
		FrontUtils.frontData(request, model, site);
		return "/WEB-INF/t/cms/www/red/campaign/sfdj/share.html";
	}
	
	@RequestMapping(value="memberStoryList.jspx")
	public String memberStoryList(HttpServletRequest request,ModelMap model,Integer pageNo,Integer pageSize, String status, String userName, String createTimeMin, String createTimeMax ) {
		CmsUser user = CmsUtils.getUser(request);
		CmsSite site = CmsUtils.getSite(request);
		if(pageNo==null){
			pageNo=1;
		}
		if(pageSize==null){
			pageSize=10;
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		if(!"admin".equals(user.getUsername())){
		 Pagination pagination =memberStoryMng.getPage(status, userName, createTimeMin, createTimeMax, pageNo, pageSize);
		 model.addAttribute("pagination", pagination);
		}
		return "/WEB-INF/jeecms_sys/member/car_owner_authen_application_detail.html";
	}
	
	
	@RequestMapping(value="myMemberStoryList.jspx")
	public String myMemberStoryList(HttpServletRequest request,ModelMap model,Integer pageNo,Integer pageSize, String status, String createTimeMin, String createTimeMax ) {
		CmsUser user = CmsUtils.getUser(request);
		CmsSite site = CmsUtils.getSite(request);
		if(pageNo==null){
			pageNo=1;
		}
		if(pageSize==null){
			pageSize=10;
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		 Pagination pagination =memberStoryMng.getPage(status, user.getUsername(), createTimeMin, createTimeMax, pageNo, pageSize);
		 model.addAttribute("pagination", pagination);
		return "/WEB-INF/jeecms_sys/member/car_owner_authen_application_detail.html";
	}
	
	
	@RequestMapping(value="memberStoryDetail.jspx")
	public String memberStoryDetail(HttpServletRequest request,ModelMap model,long id) {
		MemberStory memberStory=memberStoryMng.findById(id);
		List<MemberStoryPraise> praiseList=memberStoryPraiseMng.findAllByMemberStoryId(id);
		model.put("memberStory",memberStory);
		model.put("praiseList",praiseList);
		return "/WEB-INF/jeecms_sys/member/car_owner_authen_application_detail.html";
	}
	
	@RequestMapping(value="addMemberStory.jspx")
	public void add(HttpServletResponse response,HttpServletRequest request,ModelMap model,MemberStory t) {
		CmsUser user = CmsUtils.getUser(request);
		t.setCreater(user);
		JSONObject object = new JSONObject();
		try {
			int status=memberStoryMng.add(t);
			object.put("status",status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
	}

	@RequestMapping(value="updateMemberStory.jspx")
	public void update(HttpServletResponse response,HttpServletRequest request,ModelMap model,MemberStory t) {
		CmsUser user = CmsUtils.getUser(request);
		t.setCreater(user);
		JSONObject object = new JSONObject();
		try {
			int status=memberStoryMng.update(t);
			object.put("status",status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
	}
	
	@RequestMapping(value="memberStoryPass.jspx")
	public void updateStatusToPass(HttpServletResponse response,HttpServletRequest request,ModelMap model,MemberStory t) {
		CmsUser user = CmsUtils.getUser(request);
		t.setCreater(user);
		t.setStatus(1);
		JSONObject object = new JSONObject();
		try {
			int status=memberStoryMng.updateStatus(t);
			object.put("status",status);
			} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
	}

	@RequestMapping(value="memberStoryUnPass.jspx")
	public void updateStatusToUnPass(HttpServletResponse response,HttpServletRequest request,ModelMap model,MemberStory t) {
		CmsUser user = CmsUtils.getUser(request);
		t.setCreater(user);
		t.setStatus(2);
		JSONObject object = new JSONObject();
		try {
			int status=memberStoryMng.updateStatus(t);
			object.put("status",status);
			} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
	}
	
	@RequestMapping(value="memberStoryPassList.jspx")
	public void memberStoryPassList(HttpServletResponse response,HttpServletRequest request,ModelMap model,Integer pageNo,Integer pageSize ) {
		String retVal=JSONUtil.objectToJson(memberStoryMng.getPageForPass(pageNo, pageSize));
		ResponseUtils.renderJson(response, retVal);
	}
	
	@RequestMapping("/page/{pageNo}.jspx")
	public String memberStroryPassInfiniteScroll(@PathVariable Integer pageNo, ModelMap model,HttpServletRequest request){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		 CmsUser user = CmsUtils.getUser(request);
		 if(user!=null&&user.getUsername().equals("admin")){
			 Pagination pagination =memberStoryMng.getPage("", "", "", "", pageNo, 2); 
			 model.addAttribute("pagination", pagination);
		 }else{
			 Pagination pagination=memberStoryMng.getPageForPass(pageNo, 2);
			 model.addAttribute("pagination", pagination);
		 }
		return "/WEB-INF/t/cms/www/red/campaign/sfdj/page/scroll_page.html";
	}
	
}
