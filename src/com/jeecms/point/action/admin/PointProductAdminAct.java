
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: PointProductAdminAct.java 9552 May 22, 2015 10:43:02 AM MaoJiaWei$
*/
package com.jeecms.point.action.admin; 

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.cms.web.WebErrors;
import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.ActivityProduct;
import com.jeecms.point.entity.PointProduct;
import com.jeecms.point.entity.ProductCategory;
import com.jeecms.point.entity.base.BasePointProduct;
import com.jeecms.point.manager.product.ActivityProductMng;
import com.jeecms.point.manager.product.PointProductMng;
import com.jeecms.point.manager.product.ProductCategoryMng;
import com.jeecms.point.vo.product.PointProductVo;
import com.jeecms.point.web.json.JSONParser;
import com.jeecms.point.web.query.QueryVo;

/**
 * <p>
 * Title: 
 * </p>
 * <p>
 * Description: 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * 
 * @author MaoJiaWei
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/points/product")
public class PointProductAdminAct {
	private static final Logger logger = LoggerFactory
			.getLogger(PointProductAdminAct.class);
	/** 首页 */
	private static final String INDEX_RETURN = "points/product/index";
	/** 添加页面 */
	private static final String ADD_RETURN = "points/product/add";
	/** 存储层 */
	@Autowired
	private PointProductMng pointProductMng;
	@Autowired
	private ProductCategoryMng productCategoryMng;
	@Autowired
	private ActivityProductMng activityProductMng;

	/** 列表页面 */
	@RequestMapping("/index.do")
	public String index() {
		logger.info("积分商品页面...");
		return INDEX_RETURN;
	}
	
	
	/** 添加页面 */
	@RequestMapping("/add.do")
	public String add(ModelMap model) {
		logger.info("加载首页...");
		PointProduct pointProduct = new PointProduct();
		model.put("pointProduct", pointProduct);
		List<ProductCategory> categoryList = productCategoryMng.getAllProductCategory();
		model.put("categoryList", categoryList);
		return ADD_RETURN;
	}
	
	/*** 查询列表信息 */
	@RequestMapping("/list.do")
	@ResponseBody
	public String list(QueryVo queryVo, PointProductVo productVo) {
		Pagination pagination = this.pointProductMng.getPage(queryVo,
				productVo.getTitle(), productVo.getCategoryId());
		return JSONParser.toDataGridString2(pagination);
	}
	
	
	
	/** 保存页面 */
	@RequestMapping("/save.do")
	public String save(PointProductVo pointProductVo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = WebErrors.create(request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		if (pointProductVo.getId() == null) {
			PointProduct pointProduct = new PointProduct();
			BeanUtils.copyProperties(pointProductVo, pointProduct);
			pointProduct.setMonthLeftLotteryStockNum(pointProduct.getMonthLotteryStockNum());
			pointProduct.setMonth(Calendar.getInstance().get(Calendar.MONTH));
			// 创建时间，更新时间
			Date createdDate = new Date();
			pointProduct.setCreatedId(1L);
			pointProduct.setCreatedDate(createdDate);
			pointProduct.setUpdatedId(1L);
			pointProduct.setUpdatedDate(createdDate);
			pointProduct=this.pointProductMng.save(pointProduct);
			logger.info("id:"+pointProduct.getId());
		}else{
			PointProduct pointProduct=this.pointProductMng.findById(pointProductVo.getId());
			BeanUtils.copyProperties(pointProductVo, pointProduct);
			Date  updatedDate = new Date();
			pointProduct.setUpdatedDate(updatedDate);
			this.pointProductMng.update(pointProduct);
			if(pointProduct.getStatus()!=1){
				List<ActivityProduct> list = activityProductMng.getActivityProductsByproductId(pointProduct.getId());
				if(list!=null&&list.size()>0){
					for(ActivityProduct activityProduct:list){
						activityProductMng.remove(activityProduct.getId());
					}
				}
			}
		}
		return INDEX_RETURN;
	}
	@RequestMapping("/getCategory.do")
	@ResponseBody
	public String getCategory(){
		List<ProductCategory> list = productCategoryMng.getAllProductCategory();
		return JSONParser.toJSONString(list);
	}
	
	/**
	 * 删除数据
	 * 
	 * @param ruleVo对象
	 * @return
	 */
	@RequestMapping("/remove.do")
	@ResponseBody
	public String remove(@RequestParam(required = false) Long id) {
		this.pointProductMng.remove(id);
		return "删除成功!";
	}
	
	/***
	 * 编辑
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit.do")
	public String edit(@RequestParam(required = false) Long id, ModelMap model) {
		PointProduct pointProduct = this.pointProductMng.findById(id);
		model.put("pointProduct", pointProduct);
		List<ProductCategory> categoryList = productCategoryMng.getAllProductCategory();
		model.put("categoryList", categoryList);
		return ADD_RETURN;
	}
	
	/**活动商品*/
	
	/** 列表页面 */
	@RequestMapping("/product.do")
	public String product(Model model,Long activityId) {
		logger.info("积分商品页面...");
		List<BasePointProduct> list = this.activityProductMng.getProducts(activityId);
		model.addAttribute("productlist", JSONParser.toJSONString(list));  
		model.addAttribute("activityId", activityId);  
		//这个只有值没有键的情况下,使用Object的类型作为key,String-->string  
		model.addAttribute("ok");  
		return "points/product/product";
	}
	
	/*** 查询授权的 */
	@RequestMapping("/authProduct.do")
	@ResponseBody
	public String authProduct(Long activityId,QueryVo queryVo, PointProductVo productVo,Model model) {
		Pagination pagination = this.pointProductMng.getPage(queryVo,
				productVo.getTitle(), productVo.getCategoryId());
		return JSONParser.toDataGridString2(pagination);
	}
	
	/*** 查询授权的 */
	@RequestMapping("/submitproduct.do")
	@ResponseBody
	public String submitproduct(Long activityId, @RequestParam(value="list[]") long[] list,QueryVo queryVo, Model model) {
		List<ActivityProduct> activityProductList = activityProductMng.getActivityProducts(activityId);
		outer:
		for(int i=0;i<list.length;i++){
			for(ActivityProduct activityProduct:activityProductList){
				if(activityProduct.getProductId()==list[i]){
					activityProductMng.remove(activityProduct.getId());
					continue outer;
				}
			}
			ActivityProduct activityProduct = new ActivityProduct();
			activityProduct.setActivityId(activityId);
			activityProduct.setProductId(list[i]);
			activityProductMng.save(activityProduct);
		}
		return "true";
	}
	
	
	
}

	