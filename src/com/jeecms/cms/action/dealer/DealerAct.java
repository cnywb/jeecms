package com.jeecms.cms.action.dealer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.manager.dealer.ClubDealerMng;
import com.jeecms.common.util.JSONUtil;
import com.jeecms.common.web.ResponseUtils;



@Controller@Scope("prototype")
@RequestMapping("/dealer/*")
public class DealerAct {
	
	@Autowired
	private ClubDealerMng clubDealerMng;
	
	
	@RequestMapping(value="findAllProvince.jspx")
	public void findAllProvince(HttpServletResponse response,HttpServletRequest request,ModelMap model) {
		String json=JSONUtil.objectToJson(clubDealerMng.findAllProvince());
		ResponseUtils.renderJson(response,json);
	}
	
	@RequestMapping(value="findAllCityByProvinceId.jspx")
	public void findAllCityByProvinceId(HttpServletResponse response,HttpServletRequest request,ModelMap model,Long provinceId) {
		String json=JSONUtil.objectToJson(clubDealerMng.findAllCityByProvinceId(provinceId));
		ResponseUtils.renderJson(response,json);
	}
	
	@RequestMapping(value="findAllDealerByCityId.jspx")
	public void findAllDealerByCityId(HttpServletResponse response,HttpServletRequest request,ModelMap model,Long cityId) {
		String json=JSONUtil.objectToJson(clubDealerMng.findAllDealerByCityId(cityId));
		ResponseUtils.renderJson(response,json);
	}

}
