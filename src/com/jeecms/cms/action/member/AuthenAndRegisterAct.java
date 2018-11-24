package com.jeecms.cms.action.member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.manager.main.RegisterMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.ResponseUtils;
@Controller
public class AuthenAndRegisterAct {

	
	@RequestMapping(value="/m/carOwnerAuthenAndRegister.jspx")
	public String mobileCarOwnerAuthenAndReg(HttpServletRequest request,ModelMap model,String vmobile,String vvin,String registType) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		model.addAttribute("vmobile", vmobile);
		model.addAttribute("vvin", vvin);
		model.addAttribute("registType", registType);
		return "/WEB-INF/t/cms/www/mobile/authen_and_register.html";
	}
	
	
	@RequestMapping(value = "/m/authenAndRegister.jspx", method = RequestMethod.POST)//
	public void carOwnerCheckAjax(String vname, String vmobile,String vvin,String registType,HttpServletRequest request, HttpServletResponse response){
		String ip = RequestUtils.getIpAddr(request);
		ResponseUtils.renderJson(response, registerMng.carOwnerAuthenAndRegister(vname, vmobile, vvin,ip,registType).toString());
	}
	
	
	
	@Autowired
	private RegisterMng registerMng ;
	
	
}
