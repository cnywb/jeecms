package com.jeecms.cms.action.maintenance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.manager.maintenance.MaintenanceMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.util.JSONUtil;
import com.jeecms.common.web.ResponseUtils;


@Controller@Scope("prototype")
@RequestMapping("/maintenance/package/*")
public class MaintenanceAct {
	
	@Autowired
	private MaintenanceMng maintenanceMng;
	
	@RequestMapping(value="ssp/index.jspx")
	public String ssp(HttpServletRequest request,String vin,ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		model.addAttribute("carModelList", maintenanceMng.findAllCarModel("SSP"));
		return "/WEB-INF/t/cms/www/mobile/maintenance/ssp.html";
	}
	
	

	
	/**
	 * 按VIN查询SSP套餐
	 * @param response
	 * @param request
	 * @param vin
	 * @param model
	 */
	@RequestMapping(value="order/findAllForSSP.jspx")
	public void findAllMaintenancePackageOrderForSSP(HttpServletResponse response,HttpServletRequest request,String vin,ModelMap model) {
		String json=JSONUtil.objectToJson(maintenanceMng.findAllMaintenancePackageOrder(vin,"Schedule Service Plan"));
		ResponseUtils.renderJson(response,json);
	}
	
	
	/**
	 * 按车型查询保养套餐
	 * @param response
	 * @param request
	 * @param carModel
	 * @param model
	 * @param group
	 */
	@RequestMapping(value="find.jspx")
	public void findMaintenancePackage(HttpServletResponse response,HttpServletRequest request,String carModel,ModelMap model,String packageType) {
		String json=JSONUtil.objectToJson(maintenanceMng.findMaintenancePackage(carModel,packageType));
		ResponseUtils.renderJson(response,json);
	}
	
	
	@RequestMapping(value="ew/index.jspx")
	public String ew(HttpServletRequest request,String vin,ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		model.addAttribute("carModelList", maintenanceMng.findAllCarModel("EW"));
		return "/WEB-INF/t/cms/www/mobile/maintenance/ew.html";
	}

	/**
	 * EW查询
	 * @param response
	 * @param request
	 * @param vin
	 * @param model
	 */
	@RequestMapping(value="order/findAllForEW.jspx")
	public void findAllMaintenancePackageOrderForEW(HttpServletResponse response,HttpServletRequest request,String vin,ModelMap model) {
		String json=JSONUtil.objectToJson(maintenanceMng.findAllMaintenancePackageOrder(vin,"Extended Warranty"));
		ResponseUtils.renderJson(response,json);
	}

}
