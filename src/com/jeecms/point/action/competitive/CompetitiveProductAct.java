/**
 * 
 */
package com.jeecms.point.action.competitive;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.CompetitiveProduct;
import com.jeecms.point.manager.competitiveproduct.CompetitiveProductMng;
import com.jeecms.point.vo.competitiveproduct.ImCompetitiveVO;

/**
 * 精品附件
 * 
 * @author ziv.hung
 */
@Controller
@Scope("prototype")
@RequestMapping("/competitive/*")
public class CompetitiveProductAct {

	private static final Logger logger = LoggerFactory.getLogger(CompetitiveProductAct.class);

	@Autowired
	private CompetitiveProductMng competitiveProductMng;

	/** 列表页面 */
	@RequestMapping("index.jspx")
	public String index(HttpServletRequest request, ModelMap model) {
		logger.info("精品附件页面...");
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		List<ImCompetitiveVO> vehicleModelsList = competitiveProductMng.getImByCategory("vehicleModels");
		List<ImCompetitiveVO> classificationList = competitiveProductMng.getImByCategory("classification");
		Pagination pagination = this.competitiveProductMng.getPage(null, null, "ALL", "1", 1, 20);
		model.addAttribute("classification", classificationList);
		model.addAttribute("vehicleModels", vehicleModelsList);
		model.addAttribute("pagination", pagination);
		return "/WEB-INF/t/cms/www/red/competitive/index.html";
	}

	/*** 查询列表信息 */
	@RequestMapping("page/{pageNo}.jspx")
	public String competitiveList(@PathVariable Integer pageNo, HttpServletRequest request, ModelMap model, String productName, String vehicleModels, String classification) {
		Pagination pagination = this.competitiveProductMng.getPage(productName, vehicleModels, classification, "1", pageNo, 20);
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		model.addAttribute("pagination", pagination);
		return "/WEB-INF/t/cms/www/red/competitive/list.html";
	}
	

	/*** 查询列表信息 */
	@RequestMapping("search/{pageNo}.jspx")
	public String competitiveSearch(@PathVariable Integer pageNo, HttpServletRequest request, ModelMap model, String productName, String vehicleModels, String classification) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		model.put("productName", productName);
		Pagination pagination=new Pagination();
		pagination.setList(new ArrayList<CompetitiveProduct>());
		if(!StringUtils.isEmpty(productName)){
			pagination = this.competitiveProductMng.getPage(productName, vehicleModels, classification, "1", pageNo,20);
		}
		model.addAttribute("pagination", pagination);
		return "/WEB-INF/t/cms/www/red/competitive/search.html";
	}
	
	/*** 查询列表信息 */
	@RequestMapping("list/{pageNo}.jspx")
	public String searchList(@PathVariable Integer pageNo, HttpServletRequest request, ModelMap model, String productName, String vehicleModels, String classification) {
		model.put("productName", productName);
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		Pagination pagination=new Pagination();
		pagination.setList(new ArrayList<CompetitiveProduct>());
		if(!StringUtils.isEmpty(productName)){
			pagination = this.competitiveProductMng.getPage(productName, vehicleModels, classification, "1", pageNo,20);
		}
		
		model.addAttribute("pagination", pagination);
		return "/WEB-INF/t/cms/www/red/competitive/searchlist.html";
	}
	
	/***
	 * 浏览次数
	 */
	@RequestMapping("update.jspx")
	@ResponseBody
	public String queryCount(@RequestParam(required=false)Long productId){		
		return ""+competitiveProductMng.updateBrowse(productId);
	}
}
