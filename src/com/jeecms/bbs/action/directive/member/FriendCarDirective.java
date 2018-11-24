/**
 * 
 */
package com.jeecms.bbs.action.directive.member;

import static com.jeecms.common.web.freemarker.DirectiveUtils.OUT_PAGINATION;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.entity.BbsMemberCar;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.manager.member.BbsMemberCarMng;
import com.jeecms.bbs.manager.member.BbsMemberFavoriteMng;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author xuw
 *
 */
public class FriendCarDirective implements TemplateDirectiveModel{
	
	
	private  final Logger logger=LoggerFactory.getLogger(super.getClass());
	
	@Autowired
	private  BbsMemberCarMng bbsMemberCarMng;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		logger.info("execute in  ...");
		CmsSite site = FrontUtils.getSite(env);
		BbsUser user=FrontUtils.getUser(env);
		 
		Integer userId=user.getId();
		Integer pageNo=FrontUtils.getPageNo(env);
		Integer  count=FrontUtils.getCount(params);
		Pagination   page=bbsMemberCarMng.queryFriendCarByUserId(userId, pageNo, count);
		
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);
		paramWrap.put(OUT_PAGINATION, DEFAULT_WRAPPER.wrap(page));
		Map<String, TemplateModel> origMap = DirectiveUtils.addParamsToVariable(env, paramWrap);
	   
		body.render(env.getOut());
		FrontUtils.includePagination(site, params, env);
	 
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
		logger.info("execute  out  ...");
		
	}
	
}
