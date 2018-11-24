package com.jeecms.cms.action.member;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.manager.main.CarOwnerAuthenApplicationMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.core.manager.AuthenticationMng;

@Controller@Scope("prototype")
@RequestMapping("/member/carownerauthen/*")
public class CarOwnerAuthenApplicationAct {
	
	@Autowired
	private CarOwnerAuthenApplicationMng carOwnerAuthenApplicationMng;
	
	
	
	
	@RequestMapping(value="index.jspx")
	public String index(HttpServletRequest request,ModelMap model,String vvin,String vname,String vmobile) {
		CmsSite site = CmsUtils.getSite(request);
		model.addAttribute("vvin", vvin);
		model.addAttribute("vname", vname);
		model.addAttribute("vmobile", vmobile);
		FrontUtils.frontData(request, model, site);
		return "/WEB-INF/t/cms/www/red/alone/单页_车主认证.html";
	}
	
	
	@RequestMapping(value="add.jspx",method=RequestMethod.POST)
	public void add(String vvin, String vname,String vmobile, String certImageUrl,Long tempInfoId,HttpServletResponse response,HttpServletRequest request){
		JSONObject object = new JSONObject();
		CmsUser user = CmsUtils.getUser(request);
		if(user==null){
			try {
				object.put("status","99");
				ResponseUtils.renderJson(response, object.toString());
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			int status=carOwnerAuthenApplicationMng.add(user,vvin.toUpperCase(),vname,vmobile,certImageUrl);
			if(tempInfoId!=null&&tempInfoId.longValue()!=0L){
				carOwnerAuthenApplicationMng.updateCarOwnerAuthenApplicationTempInfo(user.getId(), tempInfoId);
			}
			object.put("status",status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
	}
	@RequestMapping(value="saveTempInfo.jspx",method=RequestMethod.POST)
	public void saveTempInfo(String vvin, String vname,String vmobile,HttpServletResponse response,HttpServletRequest request){
		JSONObject object = new JSONObject();
		CmsUser user = CmsUtils.getUser(request);
		if(user==null){
			try {
				object.put("status","99");
				ResponseUtils.renderJson(response, object.toString());
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			int status=carOwnerAuthenApplicationMng.saveTempInfo(user,vvin.toUpperCase(),vname,vmobile);
			object.put("status",status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
	}

	
	@RequestMapping(value="updateTempInfo.jspx",method=RequestMethod.POST)
	public void updateTempInfo(long id,HttpServletResponse response,HttpServletRequest request){
		JSONObject object = new JSONObject();
		CmsUser user = CmsUtils.getUser(request);
		if(user==null){
			try {
				object.put("status","99");
				ResponseUtils.renderJson(response, object.toString());
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			int status=carOwnerAuthenApplicationMng.updateCarOwnerAuthenApplicationTempInfo(user.getId(), id);
			object.put("status",status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
	}
	
	@RequestMapping(value="authenApplicationlist.jspx")
	public String authenApplicationlist(String status,String name,String mobile,String vin,String createTimeMin,String createTimeMax,String updateTimeMin,String updateTimeMax, Integer pageNo,Integer pageSize, HttpServletRequest request,
			ModelMap model) {
		CmsUser user = CmsUtils.getUser(request);
		Integer userId = authMng.retrieveUserIdFromSession(session, request);
		if (userId != null) {
			user = cmsUserMng.findById(userId);
		}
		if(user.getAdmin()!=true){
			return "";
		}
		if(pageNo==null){
			pageNo=1;
		}
		if(pageSize==null){
			pageSize=10;
		}
		
		Pagination pagination = carOwnerAuthenApplicationMng.getPage(status,name, mobile, vin, createTimeMin, createTimeMax, updateTimeMin, updateTimeMax, pageNo, pageSize);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pagination.getPageNo());
		model.addAttribute("name", name);
		model.addAttribute("mobile", mobile);
		model.addAttribute("vin", vin);
		model.addAttribute("status", status);
		model.addAttribute("createTimeMin", createTimeMin);
		model.addAttribute("createTimeMax", createTimeMax);
		model.addAttribute("updateTimeMin", updateTimeMin);
		model.addAttribute("updateTimeMax", updateTimeMax);
		return "/WEB-INF/jeecms_sys/member/car_owner_authen_application_list.html";
	}
	
	
	@RequestMapping(value="authenApplicationDetail.jspx")
	public String authenApplicationDetail(long id, HttpServletRequest request,
			ModelMap model) {
		CmsUser user = CmsUtils.getUser(request);
		Integer userId = authMng.retrieveUserIdFromSession(session, request);
		if (userId != null) {
			user = cmsUserMng.findById(userId);
		}
		if(user.getAdmin()!=true){
			return "";
		}
		model.addAttribute("carOwnerAuthenApplication", carOwnerAuthenApplicationMng.findById(id));
		return "/WEB-INF/jeecms_sys/member/car_owner_authen_application_detail.html";
	}
	@RequestMapping(value="authenPass.jspx",method=RequestMethod.POST)
	public void authenPass(long id,HttpServletResponse response,HttpServletRequest request){
		CmsUser user = CmsUtils.getUser(request);
		Integer userId = authMng.retrieveUserIdFromSession(session, request);
		JSONObject object = new JSONObject();
		if (userId != null) {
			user = cmsUserMng.findById(userId);
		}
		try {
		    if(user.getAdmin()!=true){
				object.put("status","99");
		    }else{
				object.put("status",carOwnerAuthenApplicationMng.authenPass(id));
		    }
			ResponseUtils.renderJson(response, object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="authenUnPass.jspx",method=RequestMethod.POST)
	public void authenUnPass(long id,String memo,HttpServletResponse response,HttpServletRequest request){
		CmsUser user = CmsUtils.getUser(request);
		Integer userId = authMng.retrieveUserIdFromSession(session, request);
		JSONObject object = new JSONObject();
		if (userId != null) {
			user = cmsUserMng.findById(userId);
		}
		try {
		    if(user.getAdmin()!=true){
				object.put("status","99");
		    }else{
				object.put("status",carOwnerAuthenApplicationMng.authenUnPass(id,""));
		    }
			ResponseUtils.renderJson(response, object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	

	
	
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private AuthenticationMng authMng; 
	
	
	@Autowired
	private SessionProvider session;


	public SessionProvider getSession() {
		return session;
	}


	public void setSession(SessionProvider session) {
		this.session = session;
	}

}
