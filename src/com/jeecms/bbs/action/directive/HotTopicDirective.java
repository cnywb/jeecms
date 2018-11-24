package com.jeecms.bbs.action.directive;

import static com.jeecms.common.web.freemarker.DirectiveUtils.OUT_PAGINATION;
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
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class HotTopicDirective implements TemplateDirectiveModel{

	public static final String ORDERBY = "orderby";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		CmsSite site = FrontUtils.getSite(env);
		int orderby = getOrderby(params);
		Pagination newTopicList = bbsTopicMng.getNewList(orderby,FrontUtils.getPageNo(env),
				FrontUtils.getCount(params));
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(OUT_PAGINATION, DEFAULT_WRAPPER.wrap(newTopicList));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		FrontUtils.includePagination(site, params, env);
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);

	}

	public static int getOrderby(Map<String, TemplateModel> params)
			throws TemplateException {
		Integer orderby = DirectiveUtils.getInt(ORDERBY, params);
		if (orderby == null) {
			return 1;
		}
		return orderby;
	}

	@Autowired
	private BbsTopicMng bbsTopicMng;

}
