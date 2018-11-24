package com.jeecms.cms.action.admin.member;



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


@Controller@Scope("prototype")
@RequestMapping("/admin/member/carowner/*")
public class CarOwnerAuthenInfoManageAct {
	private static final Logger log = LoggerFactory
			.getLogger(CarOwnerAuthenInfoManageAct.class);

	@RequestMapping(value="authenInfoList.do")
	public String authenInfoList(String queryUsername, String queryVin,String createTimeMax,String createTimeMin, Integer pageNo,Integer pageSize, 
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
	   return "member/car_owner_authen_info_list";
	}
	@Autowired
	private CmsUserMng manager;
}