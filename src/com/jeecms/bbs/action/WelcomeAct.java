package com.jeecms.bbs.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.manager.main.CmsSiteMng;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.web.AdminContextInterceptor;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;

@Controller
public class WelcomeAct {
	@RequestMapping("/index.do")
	public String index() {
		return "index";
	}

	@RequestMapping("/top.do")
	public String top(HttpServletRequest request, ModelMap model) {
		// 需要获得站点列表
		List<CmsSite> siteList = cmsSiteMng.getList();
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		model.addAttribute("siteList", siteList);
		model.addAttribute("site", site);
		model.addAttribute("siteParam", AdminContextInterceptor.SITE_PARAM);
		model.addAttribute("user", user);
		return "top";
	}

	@RequestMapping("/main.do")
	public String main() {
		return "main";
	}

	@RequestMapping("/left.do")
	public String left() {
		return "left";
	}

	@RequestMapping("/right.do")
	public String right() {
		return "right";
	}

	
	@RequestMapping(value="/copyRight.jhtml")
	public String copyRight(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		model.addAttribute("site", site);
		FrontUtils.frontData(request, model, site);
		return "/WEB-INF/t/cms/www/red/footerInfo/copy_right.html";
	}
	
	@RequestMapping(value="/contact.jhtml")
	public String contact(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		model.addAttribute("site", site);
		FrontUtils.frontData(request, model, site);
		return "/WEB-INF/t/cms/www/red/footerInfo/contact.html";
	}
	
	@RequestMapping(value="/privateProtect.jhtml")
	public String privateProtect(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		model.addAttribute("site", site);
		FrontUtils.frontData(request, model, site);
		return "/WEB-INF/t/cms/www/red/footerInfo/private_protect.html";
	}
	
	@RequestMapping(value="/siteMap.jhtml")
	public String siteMap(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		model.addAttribute("site", site);
		FrontUtils.frontData(request, model, site);
		return "/WEB-INF/t/cms/www/red/footerInfo/site_map.html";
	}
	@Autowired
	private CmsSiteMng cmsSiteMng;
}
