package com.jeecms.cms.action.manual;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.util.PropUtils;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.UnifiedUserMng;


@Controller@Scope("prototype")
@RequestMapping("/manual/*")
public class OwnerManualAct {
	
	@Autowired
	private UnifiedUserMng unifiedUserMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	
	@RequestMapping(value = "index.jspx")
	public String index(ModelMap model,HttpServletRequest request){
		CmsSite site = CmsUtils.getSite(request);
	    CmsUser user = CmsUtils.getUser(request);
	    if(user==null){
				return FrontUtils.showLogin(request, model, site);
		}
		FrontUtils.frontData(request, model, site);
		if(user.getGroup().getId().longValue()!=2L){
			String baseContextPath=request.getScheme()+"://"+request.getServerName()+(request.getServerPort()==80?"":":"+request.getServerPort())+request.getContextPath();
			return "redirect:"+baseContextPath+"/czrz/index.htm";
		}
		return "/WEB-INF/t/cms/www/red/manual/车主手册.html";
	}
	@RequestMapping(value = "m/index.jspx")
	public String mIndex(ModelMap model,HttpServletRequest request,String openid){
		 CmsSite site = CmsUtils.getSite(request);
		 UnifiedUser unifiedUser=unifiedUserMng.findByOpenId(openid);
		 if(unifiedUser==null){
			 return "redirect:"+PropUtils.getPropertyValue("wechat.properties","wechat_car_owner_authen");
		 }
		FrontUtils.frontData(request, model, site);
		CmsUser user=cmsUserMng.findById(unifiedUser.getId());
		if(user.getGroup().getId().longValue()!=2L){
		
			return "redirect:"+PropUtils.getPropertyValue("wechat.properties","wechat_car_owner_authen");
		}
		return "/WEB-INF/t/cms/www/red/manual/车主手册手机版.html";
	}

}
