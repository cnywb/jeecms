package com.jeecms.cms.action.campaign.sfdj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jeecms.cms.entity.campaign.sfdj.MemberStoryPraise;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.manager.campaign.sfdj.MemberStoryPraiseMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.common.web.ResponseUtils;

@Controller@Scope("prototype")
@RequestMapping("/campaign/sfdj/*")
public class MemberStoryPraiseAct {
	@Autowired
	private MemberStoryPraiseMng memberStoryPraiseMng;

	
	@RequestMapping(value="addMemberStoryPraise.jspx")
	public void add(HttpServletResponse response,HttpServletRequest request,ModelMap model,MemberStoryPraise t) {
		CmsUser user = CmsUtils.getUser(request);
		t.setCreater(user);
		JSONObject object = new JSONObject();
		try {
			int status=memberStoryPraiseMng.add(t);
			object.put("status",status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
	}
	
	@RequestMapping(value="deleteMemberStoryPraise.jspx")
	public void delete(HttpServletResponse response,HttpServletRequest request,ModelMap model,Long memberStoryId) {
		CmsUser user = CmsUtils.getUser(request);
		JSONObject object = new JSONObject();
		try {
			int status=memberStoryPraiseMng.delete(memberStoryId, user.getId());
			object.put("status",status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
	}
}
