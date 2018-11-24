package com.jeecms.cms.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.manager.main.CmsSiteMng;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.Authentication;
import com.jeecms.core.manager.AuthenticationMng;

/**
 * CMS上下文信息拦截器
 * 
 * 包括登录信息、权限信息、站点信息
 */
public class FrontContextInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
			throws ServletException {
		CmsSite site = null;
		List<CmsSite> list = cmsSiteMng.getListFromCache();
		int size = list.size();
		if (size == 0) {
			throw new RuntimeException("no site record in database!");
		} else if (size == 1) {
			site = list.get(0);
		} else {
			String server = request.getServerName();
			String alias, redirect;
			for (CmsSite s : list) {
				// 检查域名
				if (s.getDomain().equals(server)) {
					site = s;
					break;
				}
				// 检查域名别名
				alias = s.getDomainAlias();
				if (!StringUtils.isBlank(alias)) {
					for (String a : StringUtils.split(alias, ',')) {
						if (a.equals(server)) {
							site = s;
							break;
						}
					}
				}
				// 检查重定向
				redirect = s.getDomainRedirect();
				if (!StringUtils.isBlank(redirect)) {
					for (String r : StringUtils.split(redirect, ',')) {
						if (r.equals(server)) {
							try {
								response.sendRedirect(s.getUrl());
							} catch (IOException e) {
								throw new RuntimeException(e);
							}
							return false;
						}
					}
				}
			}
			if (site == null) {
				throw new SiteNotFoundException(server);
			}
		}
		
		CmsUtils.setSite(request, site);

		CmsUser user = null;
		BbsUser bbsuser = null;

		//把验证ID从cookie中取出
		Cookie[] cookies = request.getCookies();
	    
		String authId = "";
		if(cookies != null)
		{
			for(Cookie c:cookies){			
				if(c.getName().equals("AUTH_ID"))
					authId = c.getValue();
			}
		}
		if("".equals(authId)){
			authId=request.getParameter("AUTH_ID")==null?"":request.getParameter("AUTH_ID").toString();
		}
		Authentication auth = authMng.retrieve(authId);
		Integer userId = null;
		if(auth!=null)
			userId = auth.getUid();
		
		//Integer userId = authMng.retrieveUserIdFromSession(session, request);
		if (userId != null) {
			user = cmsUserMng.findById(userId);
			bbsuser = bbsUserMng.findById(userId);
		}
		if (user != null) {
			CmsUtils.setUser(request, user);
		}
		if (bbsuser != null) {
			CmsUtils.setBbsUser(request, bbsuser);
		}
		return true;
	}

	private SessionProvider session;
	private CmsSiteMng cmsSiteMng;
	private CmsUserMng cmsUserMng;
	private AuthenticationMng authMng;
	@Autowired
	private BbsUserMng bbsUserMng;

	@Autowired
	public void setSession(SessionProvider session) {
		this.session = session;
	}

	@Autowired
	public void setCmsSiteMng(CmsSiteMng cmsSiteMng) {
		this.cmsSiteMng = cmsSiteMng;
	}

	@Autowired
	public void setCmsUserMng(CmsUserMng cmsUserMng) {
		this.cmsUserMng = cmsUserMng;
	}

	@Autowired
	public void setAuthMng(AuthenticationMng authMng) {
		this.authMng = authMng;
	}
}