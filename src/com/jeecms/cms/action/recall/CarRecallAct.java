package com.jeecms.cms.action.recall;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.manager.recall.CarRecallMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;


@Controller@Scope("prototype")
@RequestMapping("/recall/*")
public class CarRecallAct {
	
	@Resource
	private CarRecallMng carRecallMng;
	
	
	@RequestMapping(value="queryCarRecall.jspx")
	public String queryCarRecall(HttpServletRequest request,ModelMap model,String vin){
		CmsSite site = CmsUtils.getSite(request);
	    FrontUtils.frontData(request, model, site);
		model.put("vin", vin);
		if(StringUtils.isEmpty(vin)){
			return "/WEB-INF/t/cms/www/red/recall/car_recall_query.html";
		}
		
		
    	model.put("dataList", carRecallMng.findByVin(vin));
	   
	    
    	return "/WEB-INF/t/cms/www/red/recall/car_recall_query.html";
	}
	
	@RequestMapping(value="queryCarRecallForMobile.jspx")
	public String queryCarRecallForMobile(HttpServletRequest request,ModelMap model,String vin){
		CmsSite site = CmsUtils.getSite(request);
	    FrontUtils.frontData(request, model, site);
		model.put("vin", vin);
		if(StringUtils.isEmpty(vin)){
			return "/WEB-INF/t/cms/www/mobile/recall/index.html";
		}
	   	model.put("dataList", carRecallMng.findByVin(vin));
	  	return "/WEB-INF/t/cms/www/mobile/recall/index.html";
	}

}
