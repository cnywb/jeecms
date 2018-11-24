package com.jeecms.point.action.point;

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
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.point.manager.activity.ActivityMng;
import com.jeecms.point.manager.point.PointRuleMng;
import com.jeecms.point.manager.product.ActivityProductMng;
/**
 * 
 * 积分规则
 * @author maojiawei
 */
@Controller
@RequestMapping("/points/integral/")
public class PointIntegralAct {
	private static final Logger logger = LoggerFactory.getLogger(PointIntegralAct.class);
	/** 首页 */
	private static final String INDEX_RETURN = "/WEB-INF/t/cms/www/red/points/integral/index.html";
	/** 存储层 */
	@Autowired
	private PointRuleMng pointRuleMng;
	@Autowired
	private ActivityMng activityMng;
	@Autowired
	private ActivityProductMng activityProductMng;
	@Autowired
	private UnifiedUserMng  unifiedUserMng;
	/** 列表页面 */
	@RequestMapping("/index.jhtml")
	public String index(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		if(user!=null){
			UnifiedUser  unifiedUser=unifiedUserMng.findById(user.getId());
			model.addAttribute("unifiedUser",unifiedUser);
		}
		logger.info("进入积分规则页面...");
		return INDEX_RETURN;
	}
}
