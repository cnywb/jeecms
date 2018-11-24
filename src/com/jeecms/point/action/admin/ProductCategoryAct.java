/**
 * 
 */
package com.jeecms.point.action.admin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.cms.web.WebErrors;
import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.ProductCategory;
import com.jeecms.point.manager.product.ProductCategoryMng;
import com.jeecms.point.vo.product.ProductCategoryVo;
import com.jeecms.point.web.json.JSONParser;
import com.jeecms.point.web.query.QueryVo;

/**
 * 产品管理
 * @author wanglijun
 */
@Controller
@RequestMapping("/admin/points/category")
public class ProductCategoryAct {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductCategoryAct.class);
	
	/** 首页 */
	private static final String INDEX_RETURN = "points/category/index";
	/** 添加页面 */
	private static final String ADD_RETURN = "points/category/add";
	
	@Autowired
	private ProductCategoryMng productCategoryMng;
	
	/** 列表页面 */
	@RequestMapping("/index.do")
	public String index() {
		logger.info("积分规则页面...");
		return INDEX_RETURN;
	}
	
	
	/** 添加页面 */
	@RequestMapping("/add.do")
	public String add(ModelMap model) {
		ProductCategory productCategory = new ProductCategory();
		model.put("productCategory", productCategory);
		return ADD_RETURN;
	}
	
	/** 保存页面 */
	@RequestMapping("/save.do")
	public String save(ProductCategoryVo productCategoryVo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = WebErrors.create(request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		if (productCategoryVo.getId() == null) {
			ProductCategory productCategory = new ProductCategory();
			BeanUtils.copyProperties(productCategoryVo, productCategory);
			// 创建时间，更新时间
			Date createdDate = new Date();
			productCategory.setCreatedId(1L);
			productCategory.setCreatedDate(createdDate);
			productCategory.setUpdatedId(1L);
			productCategory.setUpdatedDate(createdDate);

			productCategory=this.productCategoryMng.save(productCategory);
			logger.info("id:"+productCategory.getId());
		}else{
			ProductCategory productCategory=this.productCategoryMng.findById(productCategoryVo.getId());
			BeanUtils.copyProperties(productCategoryVo, productCategory);
			Date  updatedDate = new Date();
			productCategory.setUpdatedDate(updatedDate);
			this.productCategoryMng.update(productCategory);
		}
		

		return INDEX_RETURN;
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
		this.productCategoryMng.remove(id);
		return "删除成功!";
	}

	/*** 查询列表信息 */
	@RequestMapping("/list.do")
	@ResponseBody
	public String list(QueryVo queryVo,ProductCategoryVo productCategoryVo) {
		Pagination pagination = this.productCategoryMng.queryPagination(queryVo,productCategoryVo.getCategoryName());
		return JSONParser.toDataGridString(pagination);
	}
	/***
	 * 编辑
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit.do")
	public String edit(@RequestParam(required = false) Long id, ModelMap model) {
		ProductCategory productCategory = this.productCategoryMng.findById(id);
		model.put("productCategory", productCategory);
		return ADD_RETURN;
	}

}
