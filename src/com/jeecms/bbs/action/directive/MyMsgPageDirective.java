package com.jeecms.bbs.action.directive;

import static com.jeecms.common.web.freemarker.DirectiveUtils.OUT_PAGINATION;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.entity.BbsMessage;
import com.jeecms.bbs.manager.BbsMessageMng;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class MyMsgPageDirective implements TemplateDirectiveModel {
	public static final String PARAM_USERID = "userId";
	public static final String PARAM_TYPEID = "typeId";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Integer userId = getUserId(params);
		Integer typeId=getTypeId(params);
		
		Integer senderId=getSenderId(params);
		Pagination pagination=null;
		if(senderId==null){
			pagination = bbsMessageMng.getPageByUserId(userId,typeId,
					FrontUtils.getPageNo(env), FrontUtils.getCount(params));
		}else{
			pagination = bbsMessageMng.getPageBySenderUserId(userId,typeId,
					FrontUtils.getPageNo(env), FrontUtils.getCount(params));
		}
		
		
		
		
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		
		int msgCount=bbsMessageMng.getTotalCountByReceiverAndTypeIdAndStatus(userId, typeId, false);
		
		
		//获取所有未读信息条数
		
		
		paramWrap.put("msgCount", DEFAULT_WRAPPER.wrap(msgCount));
		

		paramWrap.put(OUT_PAGINATION, DEFAULT_WRAPPER.wrap(pagination));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}

	private Integer getUserId(Map<String, TemplateModel> params)
			throws TemplateException {
		Integer userId = DirectiveUtils.getInt(PARAM_USERID, params);
		return userId == null ? 0 : userId;
	}

	private Integer getTypeId(Map<String, TemplateModel> params)
			throws TemplateException {
		
		Integer typeId = DirectiveUtils.getInt(PARAM_TYPEID, params);

		return typeId == null ? 1 : typeId;
	}

	private Integer getSenderId(Map<String, TemplateModel> params)
			throws TemplateException {
		
		Integer typeId = DirectiveUtils.getInt("senderId", params);

		return typeId ;
	}

	
	@Autowired
	private BbsMessageMng bbsMessageMng;
}
