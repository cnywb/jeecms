package com.jeecms.cms.action.member;

import static com.jeecms.cms.Constants.TPLDIR_MEMBER;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.bbs.entity.BbsUserExt;
import com.jeecms.bbs.entity.BbsUserGroup;
import com.jeecms.bbs.manager.BbsConfigMng;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.cms.entity.campaign.sfdj.MemberStory;
import com.jeecms.cms.entity.campaign.sfdj.MemberStoryPraise;
import com.jeecms.cms.entity.main.CmsConfig;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.CmsUserExt;
import com.jeecms.cms.entity.main.MemberConfig;
//import com.jeecms.cms.manager.main.ClubUserMng;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.cms.web.WebErrors;
import com.jeecms.common.email.EmailSender;
import com.jeecms.common.email.MessageTemplate;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.AuthenticationMng;
import com.jeecms.core.manager.ConfigMng;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.point.manager.point.PointCalculateMng;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * 前台会员注册Action
 */
@Controller
public class RegisterAct {
	private static final Logger log = LoggerFactory
			.getLogger(RegisterAct.class);

	public static final String REGISTER = "tpl.register";
	public static final String REGISTER_RESULT = "tpl.registerResult";
	public static final String REGISTER_ACTIVE_SUCCESS = "tpl.registerActiveSuccess";
	public static final String LOGIN_INPUT = "tpl.loginInput";

	@RequestMapping(value = "/register.jspx", method = RequestMethod.GET)
	public String input(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		// 没有开启会员注册
		if (!mcfg.isRegisterOn()) {
			return FrontUtils.showMessage(request, model,
					"member.registerClose");
		}
		FrontUtils.frontData(request, model, site);
		model.addAttribute("mcfg", mcfg);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, REGISTER);
	}

	@RequestMapping(value="mobileRegister.jspx")
	public String mobileRegister(HttpServletRequest request,ModelMap model,String registType) {
		model.put("registType",registType);
		return "/WEB-INF/t/cms/www/mobile/register.html";
	}

	
	@RequestMapping(value = "/register.jspx", method = RequestMethod.POST)
	public String submit(String username, String email, String password,
			CmsUserExt userExt, BbsUserExt buserExt, String captcha, String nextUrl,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model,Integer channel) throws IOException {
		CmsSite site = CmsUtils.getSite(request);
		CmsConfig config = site.getConfig();
		WebErrors errors = validateSubmit(username, email, password, captcha,
				site, request, response);
		if (errors.hasErrors()) {
			return FrontUtils.showError(request, response, model, errors);
		}
		String ip = RequestUtils.getIpAddr(request);
		CmsUser cmsuser = null;
		if (config.getEmailValidate()) {
			EmailSender sender = configMng.getEmailSender();
			MessageTemplate msgTpl = configMng.getRegisterMessageTemplate();
			if (sender == null) {
				// 邮件服务器没有设置好
				model.addAttribute("status", 4);
			} else if (msgTpl == null) {
				// 邮件模板没有设置好
				model.addAttribute("status", 5);
			} else {
				try {
					cmsuser=cmsUserMng.registerMember(username, email, password, ip,
							null, userExt, false, sender, msgTpl);
					Integer groupId = null;
					BbsUserGroup bbsgroup = bbsConfigMng.findById(2).getRegisterGroup();
					if (bbsgroup != null) {
						groupId = bbsgroup.getId();
					}
					bbsUserMng.registerMember(username, email, password, ip, groupId,
							buserExt, cmsuser.getId());
					model.addAttribute("status", 0);
				} catch (UnsupportedEncodingException e) {
					// 发送邮件异常
					model.addAttribute("status", 100);
					model.addAttribute("message", e.getMessage());
					log.error("send email exception.", e);
				} catch (MessagingException e) {
					// 发送邮件异常
					model.addAttribute("status", 101);
					model.addAttribute("message", e.getMessage());
					log.error("send email exception.", e);
				}
			}
			log.info("member register success. username={}", username);
			FrontUtils.frontData(request, model, site);
		
			if (!StringUtils.isBlank(nextUrl)) {
				response.sendRedirect(nextUrl);
				return null;
			} else {
				return FrontUtils.getTplPath(request, site.getSolutionPath(),
						TPLDIR_MEMBER, REGISTER_RESULT);
			}
		} else {
			cmsuser=cmsUserMng.registerMember(username, email, password, ip, null,null,userExt);
			Integer groupId = null;
			BbsUserGroup bbsgroup = bbsConfigMng.findById(2).getRegisterGroup();
			if (bbsgroup != null) {
				groupId = bbsgroup.getId();
			}
			bbsUserMng.registerMember(username, email, password, ip, groupId,
					buserExt, cmsuser.getId());
			log.info("member register success. username={}", username);
			FrontUtils.frontData(request, model, site);
			FrontUtils.frontPageData(request, model);
			model.addAttribute("success", true);
			
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_MEMBER, LOGIN_INPUT);
		}

	}
	
	@RequestMapping(value = "/registerAjax.jspx", method = RequestMethod.POST)
	public void registerAjax(String username, String email, String password,
			CmsUserExt userExt, BbsUserExt buserExt, String captcha, String nextUrl,Long registType,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model,Integer channel) throws IOException {
		if(registType==null){
			registType=1L;
		}
		CmsSite site = CmsUtils.getSite(request);
		CmsConfig config = site.getConfig();
		WebErrors errors = validateSubmit(username, email, password, captcha,site, request, response);
		String message="";
		int status=0;
		JSONObject object = new JSONObject();
		if (errors.hasErrors()) {
			status=0;
			for(String er:errors.getErrors()){
				message=message+er;
			}
			setJSONObject(message, status, object);
			ResponseUtils.renderJson(response, object.toString());
			return;
		}
		String ip = RequestUtils.getIpAddr(request);
		CmsUser cmsuser = null;
		if (config.getEmailValidate()) {
			EmailSender sender = configMng.getEmailSender();
			MessageTemplate msgTpl = configMng.getRegisterMessageTemplate();
			if (sender == null) {
				// 邮件服务器没有设置好
				model.addAttribute("status", 4);
			} else if (msgTpl == null) {
				// 邮件模板没有设置好
				model.addAttribute("status", 5);
			} else {
				try {
					cmsuser=cmsUserMng.registerMember(username, email, password, ip,
							null, userExt, false, sender, msgTpl,registType);
					Integer groupId = null;
					BbsUserGroup bbsgroup = bbsConfigMng.findById(2).getRegisterGroup();
					if (bbsgroup != null) {
						groupId = bbsgroup.getId();
					}
					bbsUserMng.registerMember(username, email, password, ip, groupId,
							buserExt, cmsuser.getId());
					model.addAttribute("status", 0);
				} catch (UnsupportedEncodingException e) {
					// 发送邮件异常
					model.addAttribute("status", 100);
					model.addAttribute("message", e.getMessage());
					log.error("send email exception.", e);
				} catch (MessagingException e) {
					// 发送邮件异常
					model.addAttribute("status", 101);
					model.addAttribute("message", e.getMessage());
					log.error("send email exception.", e);
				}
			}
			log.info("member register success. username={}", username);
			FrontUtils.frontData(request, model, site);
			
			status=1;
			message="注册成功!";
			setJSONObject(message, status, object);
			ResponseUtils.renderJson(response, object.toString());
			return;
		} else {
			cmsuser=cmsUserMng.registerMember(username, email, password, ip, null,null,userExt,registType);
			Integer groupId = null;
			BbsUserGroup bbsgroup = bbsConfigMng.findById(2).getRegisterGroup();
			if (bbsgroup != null) {
				groupId = bbsgroup.getId();
			}
			bbsUserMng.registerMember(username, email, password, ip, groupId,buserExt, cmsuser.getId());
			log.info("member register success. username={}", username);
			FrontUtils.frontData(request, model, site);
			FrontUtils.frontPageData(request, model);
			status=1;
			message="注册成功!";
			setJSONObject(message, status, object);
			ResponseUtils.renderJson(response, object.toString());
			return;
		}

	}

	private void setJSONObject(String message, int status, JSONObject object) {
		try {
			object.put("status",status);
			object.put("message",message);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	@RequestMapping(value = "/active.jspx", method = RequestMethod.GET)
	public String active(String username, String key,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {
		CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateActive(username, key, request, response);
		if (errors.hasErrors()) {
			return FrontUtils.showError(request, response, model, errors);
		}
		UnifiedUser user = unifiedUserMng.active(username, key);
		String ip = RequestUtils.getIpAddr(request);
		authMng.activeLogin(user, ip, request, response, session);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, REGISTER_ACTIVE_SUCCESS);
	}
	*/

	@RequestMapping(value = "/username_unique.jspx")
	public void usernameUnique(HttpServletRequest request,
			HttpServletResponse response) {
		String username = RequestUtils.getQueryParam(request, "username");
		// 用户名为空，返回false。
		if (StringUtils.isBlank(username)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		CmsSite site = CmsUtils.getSite(request);
		CmsConfig config = site.getConfig();
		// 保留字检查不通过，返回false。
		if (!config.getMemberConfig().checkUsernameReserved(username)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		// 用户名存在，返回false。
		if (unifiedUserMng.usernameExist(username)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		ResponseUtils.renderJson(response, "true");
	}

	@RequestMapping(value = "/email_unique.jspx")
	public void emailUnique(HttpServletRequest request,
			HttpServletResponse response) {
		String email = RequestUtils.getQueryParam(request, "email");
		// email为空，返回false。
		if (StringUtils.isBlank(email)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		// email存在，返回false。
		if (unifiedUserMng.emailExist(email)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		ResponseUtils.renderJson(response, "true");
	}

	private WebErrors validateSubmit(String username, String email,
			String password, String captcha, CmsSite site,
			HttpServletRequest request, HttpServletResponse response) {
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		WebErrors errors = WebErrors.create(request);
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
		if (errors.ifOutOfLength(username, "username",
				mcfg.getUsernameMinLen(), 100)) {
			return errors;
		}
		if (errors.ifNotUsername(username, "username",
				mcfg.getUsernameMinLen(), 100)) {
			return errors;
		}
		if (errors.ifOutOfLength(password, "password",
				mcfg.getPasswordMinLen(), 100)) {
			return errors;
		}
		if (errors.ifNotEmail(email, "email", 100)) {
			return errors;
		}
		// 保留字检查不通过，返回false。
		if (!mcfg.checkUsernameReserved(username)) {
			errors.addErrorCode("error.usernameReserved");
			return errors;
		}
		// 用户名存在，返回false。
		//if (unifiedUserMng.usernameExist(username) || clubUserMng.checkName(username)) {
		if (unifiedUserMng.usernameExist(username)) {
			errors.addErrorCode("error.usernameExist");
			return errors;
		}
		return errors;
	}

	private WebErrors validateActive(String username, String activationCode,
			HttpServletRequest request, HttpServletResponse response) {
		WebErrors errors = WebErrors.create(request);
		if (StringUtils.isBlank(username)
				|| StringUtils.isBlank(activationCode)) {
			errors.addErrorCode("error.exceptionParams");
			return errors;
		}
		UnifiedUser user = unifiedUserMng.getByUsername(username);
		if (user == null) {
			errors.addErrorCode("error.usernameNotExist");
			return errors;
		}
		if (user.getActivation()
				|| StringUtils.isBlank(user.getActivationCode())) {
			errors.addErrorCode("error.usernameActivated");
			return errors;
		}
		if (!user.getActivationCode().equals(activationCode)) {
			errors.addErrorCode("error.exceptionActivationCode");
			return errors;
		}
		return errors;
	}

	
	@RequestMapping(value="bindUserCreatedByWechat.jspx")
	public void bindUserCreatedByWechat(HttpServletResponse response,HttpServletRequest request,String username,String mobilePhone,String validateCode){
		if (!imageCaptchaService.validateResponseForID(session.getSessionId(request, response), validateCode)) {
			ResponseUtils.renderJson(response,"5");
			return;
		}
		
		int retVal=unifiedUserMng.bindUserCreatedByWechat(username, mobilePhone);
		/*CmsUser user = CmsUtils.getUser(request);
		if(retVal==3){
			pointCalculateMng.regUserPoint(user.getId());
		}*/
		ResponseUtils.renderJson(response,String.valueOf(retVal));
	}
	

	@RequestMapping(value="userCreatedByWechatBinding.jspx")
	public String userCreatedByWechatBinding(HttpServletResponse response,HttpServletRequest request,ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		return "/WEB-INF/t/cms/www/red/member/user_created_by_wechat_binding.html";
	}
	
	@Autowired
	private UnifiedUserMng unifiedUserMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private SessionProvider session;
	@Autowired
	private ImageCaptchaService imageCaptchaService;
	@Autowired
	private ConfigMng configMng;
	@Autowired
	private AuthenticationMng authMng;
	@Autowired
	private BbsConfigMng bbsConfigMng;
	@Autowired
	private BbsUserMng bbsUserMng;
	@Autowired
	private PointCalculateMng pointCalculateMng;
}
