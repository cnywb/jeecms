package com.jeecms.common.web.freemarker;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import com.jeecms.common.util.StrUtils;
import com.jeecms.core.web.HtmlParserUtil;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 文本字符串截断
 * 
 * 需要拦截器com.jeecms.common.web.ProcessTimeFilter支持
 */
public class TextCutDirective implements TemplateDirectiveModel {
	public static final String PARAM_S = "s";
	public static final String PARAM_LEN = "len";
	public static final String PARAM_APPEND = "append";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String s = DirectiveUtils.getString(PARAM_S, params);
		s=HtmlParserUtil.getPlainTextFromHtml(s);//去除富文本
		s=HtmlParserUtil.getPlainTextFromHtml(s);//去除富文本,因为第一次是转义成html中可显示的html字符,所以此处必须再转换一次
		Integer len = DirectiveUtils.getInt(PARAM_LEN, params);
		String append = DirectiveUtils.getString(PARAM_APPEND, params);
		if (s != null) {
			Writer out = env.getOut();
			if (len != null) {
				out.append(StrUtils.textCut(s, len, append));
			} else {
				out.append(s);
			}
		}
	}
}
