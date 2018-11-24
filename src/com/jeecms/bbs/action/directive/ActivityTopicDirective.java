package com.jeecms.bbs.action.directive;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.manager.BbsTopicMng;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.common.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class ActivityTopicDirective  implements TemplateDirectiveModel{
	private static final String TAG_LIST = "tag_list";
	private static final String COUNT="count";

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		CmsSite site = FrontUtils.getSite(env);
		Integer count = DirectiveUtils.getInt(COUNT, params);
		List<BbsTopic> topic = bbsTopicMng.getHeadlineByForumId(7, count);
		if(topic.size()==0){
			topic = bbsTopicMng.getBeanByForumId(7, count);//7表示活动版块，1表示取最新的一条主题
		}
		
		
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(TAG_LIST, DEFAULT_WRAPPER.wrap(topic));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		FrontUtils.includePagination(site, params, env);
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);	
	}

	@Autowired
	private BbsTopicMng bbsTopicMng;
}
