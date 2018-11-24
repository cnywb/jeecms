package com.jeecms.cms.action.member;

import static com.jeecms.cms.Constants.TPLDIR_MEMBER;
import static com.jeecms.core.action.front.LoginAct.MESSAGE;
import static com.jeecms.core.action.front.LoginAct.PROCESS_URL;
import static com.jeecms.core.action.front.LoginAct.RETURN_URL;
import static com.jeecms.core.manager.AuthenticationMng.AUTH_KEY;

import java.net.URLEncoder;
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.bbs.entity.BbsLoginLog;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.BbsUserOnline;
import com.jeecms.bbs.manager.BbsLoginLogMng;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.bbs.manager.BbsUserOnlineMng;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.security.BadCredentialsException;
import com.jeecms.common.security.DisabledException;
import com.jeecms.common.security.UsernameNotFoundException;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.Authentication;
import com.jeecms.core.entity.Config.ConfigLogin;
import com.jeecms.core.manager.AuthenticationMng;
import com.jeecms.core.manager.ConfigMng;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.core.web.WebErrors;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
public class CasLoginAct {
	private static final Logger log = LoggerFactory
			.getLogger(CasLoginAct.class);

	public static final String COOKIE_ERROR_REMAINING = "_error_remaining";
	public static final String LOGIN_INPUT = "tpl.loginInput";
	public static final String LOGIN_STATUS = "tpl.loginStatus";
	public static final String TPL_INDEX = "tpl.index";
	private static String LAST_KEEP_SESSION_TIME = "last_keep_session_time";
	private static String LOGIN_BBS_USER_ID="login_bbs_user_id";


	@RequestMapping(value = "/login.jspx", method = RequestMethod.GET)
	public String input(HttpServletRequest request, ModelMap model,String returnUrl) {
		CmsSite site = CmsUtils.getSite(request);
		String sol = site.getSolutionPath();
		String message = RequestUtils.getQueryParam(request, MESSAGE);
		String authId = (String) session.getAttribute(request, AUTH_KEY);
		if (authId != null) {
			// 存在认证ID
			Authentication auth = authMng.retrieve(authId);
			// 存在认证信息，且未过期
			if (auth != null) {
				//return "redirect:/";
				//return FrontUtils.getTplPath(request, sol, TPLDIR_MEMBER, LOGIN_INPUT);
			}
		}
		
		model.addAttribute("returnUrl", returnUrl);
		FrontUtils.frontData(request, model, site);
		if (!StringUtils.isBlank(message)) {
			model.addAttribute(MESSAGE, message);
		}
		return FrontUtils.getTplPath(request, sol, TPLDIR_MEMBER, LOGIN_INPUT);
	}

	@RequestMapping(value = "/login.jspx", method = RequestMethod.POST)
	public String submit(String username, String password, String captcha,  String message,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model,String returnUrl) {
		Integer errorRemaining = unifiedUserMng.errorRemaining(username);
		CmsSite site = CmsUtils.getSite(request);
		String sol = site.getSolutionPath();
		WebErrors errors = validateSubmit(username, password, captcha,
				errorRemaining, request, response);
		if (!errors.hasErrors()) {
			try {
				String ip = RequestUtils.getIpAddr(request);
				Authentication auth = authMng.login(username, password, ip,
						request, response, session);
				// 是否需要在这里加上登录次数的更新？按正常的方式，应该在process里面处理的，不过这里处理也没大问题。
				cmsUserMng.updateLoginInfo(auth.getUid(), ip);
				CmsUser user = cmsUserMng.findById(auth.getUid());
				bbsUserMng.updateLoginInfo(auth.getUid(), ip);
				BbsUser bbsUser = bbsUserMng.findById(auth.getUid());
				if (user.getDisabled()) {
					// 如果已经禁用，则推出登录。
					authMng.deleteById(auth.getId());
					session.logout(request, response);
					throw new DisabledException("user disabled");
				}
				
				// 登录记录
				BbsLoginLog loginLog = new BbsLoginLog();
				loginLog.setIp(RequestUtils.getIpAddr(request));
				Calendar calendar = Calendar.getInstance();
				loginLog.setLoginTime(calendar.getTime());
				loginLog.setUser(bbsUser);
				bbsLoginMng.save(loginLog);

				// 在线时长统计
				BbsUserOnline online = bbsUser.getUserOnline();
				if (online != null) {
					// 更新在线信息(这里对最后一次登陆时长做初始化，其余初始化用定时任务)
					online.setOnlineLatest(0d);
					userOnlineMng.update(online);
				} else {
					// 首次登陆
					online = new BbsUserOnline();
					online.setUser(bbsUser);
					online.initial();
					userOnlineMng.save(online);
				}
				session.setAttribute(request, response, LAST_KEEP_SESSION_TIME, calendar.getTime());
				session.setAttribute(request, response,LOGIN_BBS_USER_ID,bbsUser.getId());
				removeCookieErrorRemaining(request, response);
//				Cookie c = new Cookie("JSESSIONID", request.getSession(false)
//						.getId());
//				c.setPath("/");
//				c.setDomain(site.getBaseDomain());
//				response.addCookie(c);
				Cookie c = new Cookie("AUTH_ID", auth.getId());
				c.setPath("/");
				c.setDomain(site.getBaseDomain());
				response.addCookie(c);
				FrontUtils.frontData(request, model, site);
				if(user!=null){
					//登录成功返回首页
					returnUrl=returnUrl.replaceAll("：", ":");//替换转义字符否则无法跳转
					return "redirect:"+ returnUrl;
				}else{
					return "redirect:login.jspx";
				}
			} catch (UsernameNotFoundException e) {
				errors.addErrorString(e.getMessage());
			} catch (BadCredentialsException e) {
				errors.addErrorString(e.getMessage());
			} catch (DisabledException e) {
				errors.addErrorString(e.getMessage());
			}
		}
		// 登录失败
		writeCookieErrorRemaining(errorRemaining, request, response, model);
		errors.toModel(model);
		FrontUtils.frontData(request, model, site);
		if (!StringUtils.isBlank(message)) {
			model.addAttribute(MESSAGE, message);
		}
		return FrontUtils.getTplPath(request, sol, TPLDIR_MEMBER, LOGIN_INPUT);
	}

	@RequestMapping(value = "/logout.jspx")
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		String authId = (String) session.getAttribute(request, AUTH_KEY);
		if (authId != null) {
			authMng.deleteById(authId);
			session.logout(request, response);
		}
		Cookie[] cookies = request.getCookies();
		if(cookies != null)
		{
			for(Cookie c:cookies){	
				if(c.getName().equals("AUTH_ID"))
					CookieUtils.cancleCookie(request, response, c.getName(), CmsUtils.getSite(request).getBaseDomain(),"/");
			}
		}
		String processUrl = RequestUtils.getQueryParam(request, PROCESS_URL);
		String returnUrl = RequestUtils.getQueryParam(request, RETURN_URL);
		String view = getView(processUrl, returnUrl, authId);
		if (view != null) {
			return view;
		} else {
			return "redirect:login.jspx";
		}
	}

	private WebErrors validateSubmit(String username, String password,
			String captcha, Integer errorRemaining, HttpServletRequest request,
			HttpServletResponse response) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifOutOfLength(username, "username", 1, 100)) {
			return errors;
		}
		if (errors.ifOutOfLength(password, "password", 1, 32)) {
			return errors;
		}
		// 如果输入了验证码，那么必须验证；如果没有输入验证码，则根据当前用户判断是否需要验证码。
		if (!StringUtils.isBlank(captcha)
				|| (errorRemaining != null && errorRemaining < 0)) {
			if (errors.ifBlank(captcha, "captcha", 100)) {
				return errors;
			}
			try {
				if (!imageCaptchaService.validateResponseForID(session
						.getSessionId(request, response), captcha)) {
					errors.addErrorCode("error.invalidCaptcha");
					return errors;
				}
			} catch (CaptchaServiceException e) {
				errors.addErrorCode("error.exceptionCaptcha");
				log.warn("", e);
				return errors;
			}
		}
		return errors;
	}

	
	private WebErrors validateSubmitForMobile(String username, String password,
			 Integer errorRemaining, HttpServletRequest request,
			HttpServletResponse response) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifOutOfLength(username, "username", 1, 100)) {
			return errors;
		}
		if (errors.ifOutOfLength(password, "password", 1, 32)) {
			return errors;
		}
	
		return errors;
	}

	
	/**
	 * 获得地址
	 * 
	 * @param processUrl
	 * @param returnUrl
	 * @param authId
	 * @param defaultUrl
	 * @return
	 */
	private String getView(String processUrl, String returnUrl, String authId) {
		if (!StringUtils.isBlank(processUrl)) {
			StringBuilder sb = new StringBuilder("redirect:");
			sb.append(processUrl).append("?").append(AUTH_KEY).append("=")
					.append(authId);
			if (!StringUtils.isBlank(returnUrl)) {
				sb.append("&").append(RETURN_URL).append("=").append(returnUrl);
			}
			return sb.toString();
		} else if (!StringUtils.isBlank(returnUrl)) {
			StringBuilder sb = new StringBuilder("redirect:");
			sb.append(returnUrl);
			return sb.toString();
		} else {
			return null;
		}
	}

	private void writeCookieErrorRemaining(Integer userErrorRemaining,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		// 所有访问的页面都需要写一个cookie，这样可以判断已经登录了几次。
		Integer errorRemaining = getCookieErrorRemaining(request, response);
		ConfigLogin configLogin = configMng.getConfigLogin();
		Integer errorInterval = configLogin.getErrorInterval();
		if (userErrorRemaining != null
				&& (errorRemaining == null || userErrorRemaining < errorRemaining)) {
			errorRemaining = userErrorRemaining;
		}
		int maxErrorTimes = configLogin.getErrorTimes();
		if (errorRemaining == null || errorRemaining > maxErrorTimes) {
			errorRemaining = maxErrorTimes;
		} else if (errorRemaining <= 0) {
			errorRemaining = 0;
		} else {
			errorRemaining--;
		}
		model.addAttribute("errorRemaining", errorRemaining);
		CookieUtils.addCookie(request, response, COOKIE_ERROR_REMAINING,
				errorRemaining.toString(), errorInterval * 60, null);
	}

	private Integer getCookieErrorRemaining(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie cookie = CookieUtils.getCookie(request, COOKIE_ERROR_REMAINING);
		if (cookie != null) {
			String value = cookie.getValue();
			if (NumberUtils.isDigits(value)) {
				return Integer.parseInt(value);
			}
		}
		return null;
	}

	private void removeCookieErrorRemaining(HttpServletRequest request,
			HttpServletResponse response) {
		CookieUtils.cancleCookie(request, response, COOKIE_ERROR_REMAINING,
				null);
	}

	@RequestMapping(value = "/mobileLogin.jspx", method = RequestMethod.GET)
	public String mobileLogin(HttpServletRequest request, ModelMap model,Long registType) {
		CmsSite site = CmsUtils.getSite(request);
		model.addAttribute("registType", registType);
		FrontUtils.frontData(request, model, site);
		return "/WEB-INF/t/cms/www/mobile/login.html";
	}
	
	@RequestMapping(value = "/loginAjax.jspx", method = RequestMethod.POST)
	public void loginAjax(String username, String password, String captcha,  String message,HttpServletRequest request, HttpServletResponse response,ModelMap model,String returnUrl,Long registType) {
		Integer errorRemaining = unifiedUserMng.errorRemaining(username);
		CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateSubmit(username, password, captcha,errorRemaining, request, response);
		JSONObject object = new JSONObject();
		if (!errors.hasErrors()) {
			int status=0;
			try {
				String ip = RequestUtils.getIpAddr(request);
				Authentication auth = authMng.login(username, password, ip,request, response, session);
				// 是否需要在这里加上登录次数的更新？按正常的方式，应该在process里面处理的，不过这里处理也没大问题。
				cmsUserMng.updateLoginInfo(auth.getUid(), ip);
				CmsUser user = cmsUserMng.findById(auth.getUid());
				if(registType!=null&&registType.longValue()!=0L){
					user.setRegistType(registType);
					cmsUserMng.updateUser(user);
				}
				bbsUserMng.updateLoginInfo(auth.getUid(), ip);
				BbsUser bbsUser = bbsUserMng.findById(auth.getUid());
				if (user.getDisabled()) {
					// 如果已经禁用，则推出登录。
					authMng.deleteById(auth.getId());
					session.logout(request, response);
					throw new DisabledException("user disabled");
				}
				
				// 登录记录
				BbsLoginLog loginLog = new BbsLoginLog();
				loginLog.setIp(RequestUtils.getIpAddr(request));
				Calendar calendar = Calendar.getInstance();
				loginLog.setLoginTime(calendar.getTime());
				loginLog.setUser(bbsUser);
				bbsLoginMng.save(loginLog);

				// 在线时长统计
				BbsUserOnline online = bbsUser.getUserOnline();
				if (online != null) {
					// 更新在线信息(这里对最后一次登陆时长做初始化，其余初始化用定时任务)
					online.setOnlineLatest(0d);
					userOnlineMng.update(online);
				} else {
					// 首次登陆
					online = new BbsUserOnline();
					online.setUser(bbsUser);
					online.initial();
					userOnlineMng.save(online);
				}
				session.setAttribute(request, response, LAST_KEEP_SESSION_TIME, calendar.getTime());
				session.setAttribute(request, response,LOGIN_BBS_USER_ID,bbsUser.getId());
				removeCookieErrorRemaining(request, response);
				Cookie c = new Cookie("AUTH_ID", auth.getId());
				c.setPath("/");
				c.setDomain(site.getBaseDomain());
				response.addCookie(c);
				FrontUtils.frontData(request, model, site);
					try {
						if(user!=null){
						    object.put("status",1);
						    object.put("message",user.getId());
						}else{
							object.put("status",0);
							object.put("message","用户不存在！");
						}
						ResponseUtils.renderJson(response, object.toString());
						return;
					} catch (Exception e) {
						e.printStackTrace();
					}
					
			} catch (UsernameNotFoundException e) {
				message="用户名不存在！";
			    status=0;
				errors.addErrorString(e.getMessage());
			} catch (BadCredentialsException e) {
				message="密码错误！";
				 status=2;
				errors.addErrorString(e.getMessage());
			} catch (DisabledException e) {
				message="用户被禁用！";
				 status=3;
				errors.addErrorString(e.getMessage());
			}
			try {
			    object.put("status",status);
			    object.put("message",message);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ResponseUtils.renderJson(response, object.toString());
			
		}else{
		// 登录失败
			message="";
			for(String er:errors.getErrors()){
				message=message+er;
			}
		writeCookieErrorRemaining(errorRemaining, request, response, model);
		FrontUtils.frontData(request, model, site);
		try {
			object.put("status",4);
			object.put("message",message);
			} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
		}
	}
	
	
	@RequestMapping(value = "/mobileLoginAjax.jspx", method = RequestMethod.POST)
	public void mobileLoginAjax(String username, String password, String captcha,  String message,HttpServletRequest request, HttpServletResponse response,ModelMap model,String returnUrl) {
		Integer errorRemaining = unifiedUserMng.errorRemaining(username);
		CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateSubmitForMobile(username, password,errorRemaining, request, response);
		JSONObject object = new JSONObject();
		if (!errors.hasErrors()) {
			int status=0;
			try {
				String ip = RequestUtils.getIpAddr(request);
				Authentication auth = authMng.login(username, password, ip,request, response, session);
				// 是否需要在这里加上登录次数的更新？按正常的方式，应该在process里面处理的，不过这里处理也没大问题。
				cmsUserMng.updateLoginInfo(auth.getUid(), ip);
				CmsUser user = cmsUserMng.findById(auth.getUid());
				bbsUserMng.updateLoginInfo(auth.getUid(), ip);
				BbsUser bbsUser = bbsUserMng.findById(auth.getUid());
				if (user.getDisabled()) {
					// 如果已经禁用，则推出登录。
					authMng.deleteById(auth.getId());
					session.logout(request, response);
					throw new DisabledException("user disabled");
				}
				
				// 登录记录
				BbsLoginLog loginLog = new BbsLoginLog();
				loginLog.setIp(RequestUtils.getIpAddr(request));
				Calendar calendar = Calendar.getInstance();
				loginLog.setLoginTime(calendar.getTime());
				loginLog.setUser(bbsUser);
				bbsLoginMng.save(loginLog);

				// 在线时长统计
				BbsUserOnline online = bbsUser.getUserOnline();
				if (online != null) {
					// 更新在线信息(这里对最后一次登陆时长做初始化，其余初始化用定时任务)
					online.setOnlineLatest(0d);
					userOnlineMng.update(online);
				} else {
					// 首次登陆
					online = new BbsUserOnline();
					online.setUser(bbsUser);
					online.initial();
					userOnlineMng.save(online);
				}
				session.setAttribute(request, response, LAST_KEEP_SESSION_TIME, calendar.getTime());
				session.setAttribute(request, response,LOGIN_BBS_USER_ID,bbsUser.getId());
				removeCookieErrorRemaining(request, response);
				Cookie c = new Cookie("AUTH_ID", auth.getId());
				c.setPath("/");
				c.setDomain(site.getBaseDomain());
				response.addCookie(c);
				FrontUtils.frontData(request, model, site);
					try {
						if(user!=null){
						    object.put("status",1);
						    object.put("message",user.getId());
						}else{
							object.put("status",0);
							object.put("message","用户不存在！");
						}
						ResponseUtils.renderJson(response, object.toString());
						return;
					} catch (Exception e) {
						e.printStackTrace();
					}
					
			} catch (UsernameNotFoundException e) {
				message="用户名不存在！";
			    status=0;
				errors.addErrorString(e.getMessage());
			} catch (BadCredentialsException e) {
				message="密码错误！";
				 status=2;
				errors.addErrorString(e.getMessage());
			} catch (DisabledException e) {
				message="用户被禁用！";
				 status=3;
				errors.addErrorString(e.getMessage());
			}
			try {
			    object.put("status",status);
			    object.put("message",message);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ResponseUtils.renderJson(response, object.toString());
			
		}else{
		// 登录失败
			message="";
			for(String er:errors.getErrors()){
				message=message+er;
			}
		writeCookieErrorRemaining(errorRemaining, request, response, model);
		FrontUtils.frontData(request, model, site);
		try {
			object.put("status",4);
			object.put("message",message);
			} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
		}
	}

	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private ConfigMng configMng;
	@Autowired
	private AuthenticationMng authMng;
	@Autowired
	private UnifiedUserMng unifiedUserMng;
	@Autowired
	private ImageCaptchaService imageCaptchaService;
	@Autowired
	private SessionProvider session;
	@Autowired
	private BbsUserMng bbsUserMng;
	@Autowired
	private BbsLoginLogMng bbsLoginMng;
	@Autowired
	private BbsUserOnlineMng userOnlineMng;
}
