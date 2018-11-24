package com.jeecms.cms.action.member;

import static com.jeecms.cms.Constants.TPLDIR_ALONE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.manager.main.FordMemberFormMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.point.manager.point.PointCalculateMng;

@Controller
public class AuthenticationCarOwnerAct {
	private static final Logger log = LoggerFactory.getLogger(AuthenticationCarOwnerAct.class);
	
	private static final String ALONE_CAR_AUTHENTICATION = "tpl.carAuthentication";
	
	
	@RequestMapping(value = "/czrz/check.jspx")//
	public String carOwnerCheck(String vname, String vmobile,
			String vvin,HttpServletRequest request, HttpServletResponse response,
			ModelMap model,Integer channel)
	{
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);	
		FrontUtils.frontData(request, model, site);
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		int flag = memberMng.authenticationCarOwner(user, vname, vmobile, vvin);
		
		model.put("flag", String.valueOf(flag));
		
		if(flag==1){
			if(channel==null){
			    pointCalculateMng.authUserPoint(user.getId());
			}else{
				pointCalculateMng.authUserPoint(user.getId(),channel);
			}
		}
		return FrontUtils.getTplPath(request, site.getSolutionPath(),TPLDIR_ALONE, ALONE_CAR_AUTHENTICATION);
	}
	
	@RequestMapping(value = "/czrz/carOwnerCheckAjax.jspx", method = RequestMethod.POST)//
	public void carOwnerCheckAjax(String vname, String vmobile,
			String vvin,HttpServletRequest request, HttpServletResponse response,
			ModelMap model,Integer channel){
		JSONObject object = new JSONObject();
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);	
		FrontUtils.frontData(request, model, site);
		try{
		if (user == null) {
			object.put("status","99");
			ResponseUtils.renderJson(response, object.toString());
			return;
		}
		int flag = memberMng.authenticationCarOwner(user, vname, vmobile, vvin);
		object.put("status",flag);
		if(flag==1){
			if(channel==null){
			    pointCalculateMng.authUserPoint(user.getId());
			}else{
				pointCalculateMng.authUserPoint(user.getId(),channel);
			}
		}
		
	   } catch (Exception e) {
		e.printStackTrace();
	   }
	   ResponseUtils.renderJson(response, object.toString());
	}
	
	
	@RequestMapping(value="/czrz/mobileCarOwnerAuthen.jspx")
	public String mobileCarOwnerAuthen(HttpServletRequest request,ModelMap model,String registType) {
		return "/WEB-INF/t/cms/www/mobile/czrz.html";
	}

	@Autowired
	private FordMemberFormMng memberMng;
	
	@Autowired
	private PointCalculateMng pointCalculateMng;
	
}
