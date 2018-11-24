package com.jeecms.bbs.action.directive;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.common.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class BbsUserCarOwnersDirective implements TemplateDirectiveModel{
	private final static String USER_ID="userid";
	private final static String CAR_OWNERS = "carowners";

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Integer userId = DirectiveUtils.getInt(USER_ID, params);
		CmsUser cmsuser = userMng.findById(userId);
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(CAR_OWNERS, DEFAULT_WRAPPER.wrap(cmsuser));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
		
	}

	@Autowired
	private CmsUserMng userMng;
}
