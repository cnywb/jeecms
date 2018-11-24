package com.jeecms.cms.action.member;

import static com.jeecms.common.page.SimplePage.cpn;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;

@Controller@Scope("prototype")
@RequestMapping("/member/carownerauthenInfo/*")
public class CarOwnerAuthenInfoAct {
	private static final Logger log = LoggerFactory
			.getLogger(CarOwnerAuthenInfoAct.class);

	@RequestMapping(value="authenApplicationlistInfo.jspx")
	public String authenApplicationlistInfo(String queryUsername, String queryVin,String createTimeMax,String createTimeMin, Integer pageNo,Integer pageSize, 
			HttpServletRequest request, ModelMap model) {
        	    if(pageNo==null){
        		pageNo=1;
        	}
        	if(pageSize==null){
        		pageSize=20;
        	}
		Pagination pagination = manager.getPage(queryUsername, queryVin,createTimeMax,createTimeMin,pageNo,pageSize);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pagination.getPageNo());
		model.addAttribute("queryUsername", queryUsername);
		model.addAttribute("queryVin", queryVin);
		model.addAttribute("createTimeMin", createTimeMin);
		model.addAttribute("createTimeMax", createTimeMax);
	   return "/WEB-INF/jeecms_sys/member/car_owner_authen_info_list.html";
	}
	@Autowired
	private CmsUserMng manager;
}