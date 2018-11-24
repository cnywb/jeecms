/**
 * 
 */
package com.jeecms.point.action.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.point.entity.ProductCategory;
import com.jeecms.point.manager.product.ActivityProductMng;
import com.jeecms.point.manager.product.PointProductMng;
import com.jeecms.point.manager.product.ProductCategoryMng;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
@Controller
@RequestMapping("/points/product")
public class PointProductAct {
	private static final Logger logger = LoggerFactory
			.getLogger(PointProductAct.class);
	
	/** 首页 */
	private static final String INDEX_RETURN = "/WEB-INF/t/cms/www/red/points/product/index.html";
	@Autowired
	private PointProductMng pointProductMng;
	@Autowired
	private ProductCategoryMng productCategoryMng;
	@Autowired
	private ActivityProductMng activityProductMng;	
	@Autowired
	private UnifiedUserMng  unifiedUserMng;
	
	/** 列表页面 */
	@RequestMapping("/index.jhtml")
	public String product(QueryVo queryVo, HttpServletRequest request,
			HttpServletResponse response,Long categoryId, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request,model,site);
		FrontUtils.frontPageData(request, model);
		if(user!=null){
			UnifiedUser  unifiedUser=unifiedUserMng.findById(user.getId());
			model.addAttribute("unifiedUser",unifiedUser);
		}
		logger.info("积分商品页面...");
		List<ProductCategory> categoryList = this.productCategoryMng.getAllProductCategory();
		model.put("categoryList", categoryList);
		queryVo.setRows(9);
		if(categoryId==null){
			if(categoryList!=null && categoryList.size()>0){
				Pagination pagination = this.activityProductMng.getPageOnline(queryVo);
				model.put("categoryId", categoryList.get(0).getId());
				model.put("productList", pagination.getList());
				model.put("pagination", pagination);
			}
		}else{
			model.put("categoryId", categoryId);
			Pagination pagination = this.activityProductMng.getPageOnline(queryVo);
			model.put("productList", pagination.getList());
			model.put("pagination", pagination);
		}
		return INDEX_RETURN;
	}
	
//	/** 列表页面 */
//	@RequestMapping("/list.jspx")
//	public String list(QueryVo queryVo,Long categoryId, ModelMap model){
//		System.out.println("list");
//		List<ProductCategory> categoryList = this.productCategoryMng.getAllProductCategory();
//		model.put("categoryList", categoryList);
//		Pagination pagination = this.pointProductMng.getPage(queryVo, null, categoryId);
//		model.put("productList", pagination.getList());
//		String str = JSONParser.toJSONString(pagination.getList());
//		return str;
//	}
}
