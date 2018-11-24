package com.jeecms.cms.action.member;

import static com.jeecms.cms.Constants.TPLDIR_CSI;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.bbs.manager.BbsMessageMng;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.web.RequestUtils;

@Controller
public class LoginAct {
	public static final String LOGIN_CSI = "tpl.loginCsi";
	public static final String TPL_SPACE = "tpl.space";
	
	
	@Autowired
	private BbsMessageMng bbsMessageMng;

	/**
	 * 客户端包含
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login_csi.jspx")
	public String csi(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		 CmsUser user = CmsUtils.getUser(request);
		// 将request中所有参数
		model.putAll(RequestUtils.getQueryParams(request));
		if(user!=null){
		int messageCount=bbsMessageMng.getTotalCountByReceiverAndTypeIdAndStatus(user.getId(), 4, false);
		   model.put("messageCount", messageCount);
		}
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_CSI, LOGIN_CSI);
	}

}
