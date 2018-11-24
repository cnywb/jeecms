package com.jeecms.cms.action.normal;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;

@Controller
public class MaintenanceSuggestAct {
	
	@RequestMapping(value = "/bytime.htm")
	public String index(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);

		return "/WEB-INF/t/cms/www/red/channel/maintentbytime.html";
	}
}
