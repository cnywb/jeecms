package com.jeecms.bbs.action.directive;

import static com.jeecms.common.web.freemarker.DirectiveUtils.OUT_PAGINATION;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.jeecms.bbs.action.directive.abs.AbstractPostPageDirective;
import com.jeecms.bbs.action.directive.abs.AbstractTopicPageDirective;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.freemarker.DirectiveUtils;
import com.jeecms.common.web.freemarker.DirectiveUtils.InvokeType;
import com.jeecms.cms.entity.main.CmsSite;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class ReplyToMePageDirective extends AbstractPostPageDirective {

	/**
	 * 回复我的贴子
	 */
	public static final String TPL_MY_POST = "mypost_page";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		CmsSite site = FrontUtils.getSite(env);
		int userId = DirectiveUtils.getInt("userId", params);
		Pagination page = bbsPostMng.getReplyToMe(site.getId(),
				userId, FrontUtils.getPageNo(env), FrontUtils
						.getCount(params));
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(OUT_PAGINATION, DEFAULT_WRAPPER.wrap(page));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);

			body.render(env.getOut());
			FrontUtils.includePagination(site, params, env);
		
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}

}
