package com.jeecms.common.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jeecms.common.fck.PropertiesLoader;
import com.jeecms.common.util.DateUtils;

/**
 * 执行时间过滤器
 */
public class ProcessTimeFilter implements Filter {
	protected final Logger log = LoggerFactory
			.getLogger(ProcessTimeFilter.class);
	/**
	 * 请求执行开始时间
	 */
	public static final String START_TIME = "_start_time";

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse res = (HttpServletResponse) response;

		String redirecturl = checkUrl(request, res);
		if (!StringUtils.isBlank(redirecturl)) {
			res.sendRedirect(redirecturl);
			return;
		}

		long time = System.currentTimeMillis();
		request.setAttribute(START_TIME, time);
		chain.doFilter(request, response);
		time = System.currentTimeMillis() - time;
		log.debug("process in {} ms: {}", time, request.getRequestURI());
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	private String checkUrl(HttpServletRequest request, HttpServletResponse res) {
		Date limitedate = DateUtils.getDateByFormat("2014-02-17 00:00:00",
				"yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		if (now.after(limitedate)) {
			int actionIndex = request.getRequestURL().indexOf(".action");
			if (actionIndex > 0) {
				//针对index.action
				int indexIndex =  request.getRequestURL().indexOf("index.action");
				if(indexIndex>=0)
					return "http://www.changanfordclub.com/index.jhtml";
				String[] actionurls = PropertiesLoader.getProperty("actionurl")
						.split(",");
				String[] reactionurls = PropertiesLoader.getProperty(
						"reactionurl").split(",");
				for (int i = 0; i < actionurls.length; i++) {
					int index = request.getRequestURL().indexOf(actionurls[i]);
					if (index >= 0) {
						return reactionurls[i];
					}
				}

			}
		}
		return "";
	}
}
