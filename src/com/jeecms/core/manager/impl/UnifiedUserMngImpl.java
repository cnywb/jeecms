package com.jeecms.core.manager.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;

import org.apache.commons.lang.StringUtils;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsUserDao;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.cms.dao.main.CmsUserDao;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.common.email.EmailSendTool;
import com.jeecms.common.email.EmailSender;
import com.jeecms.common.email.MessageTemplate;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.security.BadCredentialsException;
import com.jeecms.common.security.UsernameNotFoundException;
import com.jeecms.common.security.encoder.PwdEncoder;
import com.jeecms.common.util.CallbackJS;
import com.jeecms.common.util.Encrypt;
import com.jeecms.common.util.SendMail;
import com.jeecms.common.util.SmsUtils;
import com.jeecms.common.util.StrUtils;
import com.jeecms.core.dao.UnifiedUserDao;
import com.jeecms.core.entity.Config.ConfigLogin;
import com.jeecms.core.entity.Md5pwd;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.ConfigMng;
import com.jeecms.core.manager.Md5pwdMng;
import com.jeecms.core.manager.UnifiedUserMng;

@Service
@Transactional
public class UnifiedUserMngImpl implements UnifiedUserMng {
	public UnifiedUser passwordForgotten(Integer userId, EmailSender email,
			MessageTemplate tpl) {
		UnifiedUser user = findById(userId);
		String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
		user.setResetKey(uuid);
		// String resetPwd = RandomStringUtils.randomNumeric(10);
		Random r = new Random();
		int md5Id = r.nextInt(2499) + 1;
		Md5pwd m = md5pwdMng.findById(md5Id);
		user.setResetPwd(m.getJspwd());
		senderEmail(user.getId(), user.getUsername(), user.getEmail(),
				user.getResetKey(), m.getPwd(), email, tpl);
		return user;
	}

	private void senderEmail(final Integer uid, final String username,
			final String to, final String resetKey, final String resetPwd,
			final EmailSender email, final MessageTemplate tpl) {
		// JavaMailSenderImpl sender = new JavaMailSenderImpl();
		// sender.setHost(email.getHost());
		// sender.setUsername(email.getUsername());
		// sender.setPassword(email.getPassword());
		// sender.setPort(25);
		//
		// sender.send(new MimeMessagePreparator() {
		// public void prepare(MimeMessage mimeMessage)
		// throws MessagingException, UnsupportedEncodingException {
		// MimeMessageHelper msg = new MimeMessageHelper(mimeMessage,
		// false, email.getEncoding());
		// msg.setSubject(tpl.getForgotPasswordSubject());
		// msg.setTo(to);
		// msg.setFrom(email.getUsername(), email.getPersonal());
		// String text = tpl.getForgotPasswordText();
		// text = StringUtils.replace(text, "${uid}", String.valueOf(uid));
		// text = StringUtils.replace(text, "${username}", username);
		// text = StringUtils.replace(text, "${resetKey}", resetKey);
		// text = StringUtils.replace(text, "${resetPwd}", resetPwd);
		// msg.setText(text);
		// }
		// });

		String text = tpl.getForgotPasswordText();
		text = StringUtils.replace(text, "${uid}", String.valueOf(uid));
		text = StringUtils.replace(text, "${username}", username);
		text = StringUtils.replace(text, "${resetKey}", resetKey);
		text = StringUtils.replace(text, "${resetPwd}", resetPwd);
		SendMail.getInstance().sendHtmlMail(text, to,
				tpl.getForgotPasswordSubject(), email);
	}

	private void senderEmail(final String username, final String to,
			final String activationCode, final EmailSender email,
			final MessageTemplate tpl) throws UnsupportedEncodingException,
			MessagingException {
		/*
		 * JavaMailSenderImpl sender = new JavaMailSenderImpl();
		 * sender.setHost(email.getHost());
		 * sender.setUsername(email.getUsername());
		 * sender.setPassword(email.getPassword()); sender.send(new
		 * MimeMessagePreparator() { public void prepare(MimeMessage
		 * mimeMessage) throws MessagingException, UnsupportedEncodingException
		 * { MimeMessageHelper msg; msg = new MimeMessageHelper(mimeMessage,
		 * false, email.getEncoding());
		 * msg.setSubject(tpl.getRegisterSubject()); msg.setTo(to);
		 * msg.setFrom(email.getUsername(), email.getPersonal()); String text =
		 * tpl.getRegisterText(); text = StringUtils.replace(text,
		 * "${username}", username); text = StringUtils.replace(text,
		 * "${activationCode}", activationCode); msg.setText(text); } });
		 */
		String text = tpl.getRegisterText();
		text = StringUtils.replace(text, "${username}", username);
		text = StringUtils.replace(text, "${activationCode}", activationCode);
		EmailSendTool sendEmail = new EmailSendTool(email.getHost(),
				email.getUsername(), email.getPassword(), to,
				tpl.getRegisterSubject(), text, email.getPersonal(), "", "");
		sendEmail.send();
	}

	public UnifiedUser resetPassword(Integer userId) {
		UnifiedUser user = findById(userId);
		// user.setPassword(pwdEncoder.encodePassword(user.getResetPwd()));
		try {
			user.setPassword(Encrypt.encryptString(Encrypt.ENCRY_STYLE_MD5,
					user.getResetPwd()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		user.setResetKey(null);
		user.setResetPwd(null);
		return user;
	}

	public Integer errorRemaining(String username) {
		if (StringUtils.isBlank(username)) {
			return null;
		}
		UnifiedUser user = getByUsername(username);
		if (user == null) {
			return null;
		}
		long now = System.currentTimeMillis();
		ConfigLogin configLogin = configMng.getConfigLogin();
		int maxErrorTimes = configLogin.getErrorTimes();
		int maxErrorInterval = configLogin.getErrorInterval() * 60 * 1000;
		Integer errorCount = user.getErrorCount();
		Date errorTime = user.getErrorTime();
		if (errorCount <= 0 || errorTime == null
				|| errorTime.getTime() + maxErrorInterval < now) {
			return maxErrorTimes;
		}
		return maxErrorTimes - errorCount;
	}

	public UnifiedUser login(String username, String password, String ip)
			throws UsernameNotFoundException, BadCredentialsException {
		UnifiedUser user = getByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("username not found: "
					+ username);
		}
		String validPassword = "";
		try {
			validPassword = Encrypt.encryptString(Encrypt.ENCRY_STYLE_MD5,
					password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// if (!pwdEncoder.isPasswordValid(user.getPassword(), password)) {
		if (!user.getPassword().trim().equals(validPassword)) {
			updateLoginError(user.getId(), ip);
			throw new BadCredentialsException("password invalid");
		}
		if (!user.getActivation()) {
			throw new BadCredentialsException("account not activated");
		}
		updateLoginSuccess(user.getId(), ip);
		return user;
	}

	/**
	 * cookie中存放的password为密文
	 */
	public UnifiedUser loginByCookie(String username, String password, String ip)
			throws UsernameNotFoundException, BadCredentialsException {
		UnifiedUser user = getByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("username not found: "
					+ username);
		}
		if (!user.getPassword().equals(password)) {
			throw new BadCredentialsException("password invalid");
		}
		if (!user.getActivation()) {
			throw new BadCredentialsException("account not activated");
		}
		updateLoginInfo(user.getId(), ip);
		return user;
	}

	public void updateLoginInfo(Integer userId, String ip) {
		Date now = new Timestamp(System.currentTimeMillis());
		UnifiedUser user = findById(userId);

		user.setLoginCount(user.getLoginCount() + 1);
		user.setLastLoginIp(ip);
		user.setLastLoginTime(now);
	}

	public void updateLoginSuccess(Integer userId, String ip) {
		UnifiedUser user = findById(userId);
		Date now = new Timestamp(System.currentTimeMillis());

		user.setLoginCount(user.getLoginCount() + 1);
		user.setLastLoginIp(ip);
		user.setLastLoginTime(now);

		user.setErrorCount(0);
		user.setErrorTime(null);
		user.setErrorIp(null);
	}

	public void updateLoginError(Integer userId, String ip) {
		UnifiedUser user = findById(userId);
		Date now = new Timestamp(System.currentTimeMillis());
		ConfigLogin configLogin = configMng.getConfigLogin();
		int errorInterval = configLogin.getErrorInterval();
		Date errorTime = user.getErrorTime();

		user.setErrorIp(ip);
		if (errorTime == null
				|| errorTime.getTime() + errorInterval * 60 * 1000 < now
						.getTime()) {
			user.setErrorTime(now);
			user.setErrorCount(1);
		} else {
			user.setErrorCount(user.getErrorCount() + 1);
		}
	}

	public boolean usernameExist(String username) {
		return getByUsername(username) != null;
	}

	public boolean emailExist(String email) {
		return dao.countByEmail(email) > 0;
	}

	public UnifiedUser getByUsername(String username) {
		return dao.getByUsername(username);
	}

	public List<UnifiedUser> getByEmail(String email) {
		return dao.getByEmail(email);
	}

	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public UnifiedUser findById(Integer id) {
		UnifiedUser entity = dao.findById(id);
		return entity;
	}

	public UnifiedUser save(String username, String email, String password,
			String ip) {
		Date now = new Timestamp(System.currentTimeMillis());
		UnifiedUser user = new UnifiedUser();
		user.setUsername(username);
		user.setEmail(email);
		// user.setPassword(pwdEncoder.encodePassword(password));
		try {
			user.setPassword(Encrypt.encryptString(Encrypt.ENCRY_STYLE_MD5,
					password));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		user.setRegisterIp(ip);
		user.setRegisterTime(now);
		user.setLastLoginIp(ip);
		user.setLastLoginTime(now);
		// 不强制验证邮箱直接激活
		user.setActivation(true);
		user.init();
		dao.save(user);
		return user;
	}

	public UnifiedUser save(String username, String email, String password,
			String ip, Boolean activation, EmailSender sender,
			MessageTemplate msgTpl) throws UnsupportedEncodingException,
			MessagingException {
		Date now = new Timestamp(System.currentTimeMillis());
		UnifiedUser user = new UnifiedUser();
		user.setUsername(username);
		user.setEmail(email);
		// user.setPassword(pwdEncoder.encodePassword(password));
		try {
			user.setPassword(Encrypt.encryptString(Encrypt.ENCRY_STYLE_MD5,
					password));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		user.setRegisterIp(ip);
		user.setRegisterTime(now);
		user.setLastLoginIp(ip);
		user.setLastLoginTime(now);
		user.setActivation(activation);
		user.init();
		dao.save(user);
		if (!activation) {
			String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
			user.setActivationCode(uuid);
			senderEmail(username, email, uuid, sender, msgTpl);
		}
		return user;
	}

	/**
	 * @see UnifiedUserMng#update(Integer, String, String)
	 */
	public UnifiedUser update(Integer id, String password, String email) {
		UnifiedUser user = findById(id);
		if (!StringUtils.isBlank(email)) {
			user.setEmail(email);
		} else {
			user.setEmail(null);
		}
		if (!StringUtils.isBlank(password)) {
			// user.setPassword(pwdEncoder.encodePassword(password));
			try {
				user.setPassword(Encrypt.encryptString(Encrypt.ENCRY_STYLE_MD5,
						password));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
		return user;
	}

	public boolean isPasswordValid(Integer id, String password) {
		UnifiedUser user = findById(id);
		boolean flag = false;
		try {
			flag = Encrypt.encryptString(Encrypt.ENCRY_STYLE_MD5, password)
					.equals(user.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return flag;
		// return pwdEncoder.isPasswordValid(user.getPassword(), password);
	}

	public UnifiedUser deleteById(Integer id) {
		UnifiedUser bean = dao.deleteById(id);
		return bean;
	}

	public UnifiedUser[] deleteByIds(Integer[] ids) {
		UnifiedUser[] beans = new UnifiedUser[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	public UnifiedUser active(String username, String activationCode) {
		UnifiedUser bean = getByUsername(username);
		bean.setActivation(true);
		bean.setActivationCode(null);
		return bean;
	}

	public UnifiedUser activeLogin(UnifiedUser user, String ip) {
		updateLoginSuccess(user.getId(), ip);
		return user;
	}

	/**
	 * 用户使用微客服用微信openid与手机号注册了用户, 此方法可以让用户使用一个用户名,与之前注册账号绑定
	 * 这样用户就可以使用自己设定的账号在网页端登录
	 * 
	 * @param username
	 * @param mobilePhone
	 * @return
	 */
	public int bindUserCreatedByWechat(String username, String mobilePhone) {
		UnifiedUser user = dao.getByUsername(username);
		if (user != null) {// 该用户名已经存在
			return 0;
		}
		user = dao.getByMobilePhone(mobilePhone);
		if (user == null) {// 未用手机注册(微客服注册)
			return 1;
		}
		if (user.getMobilePhoneAuth() == true) {// 已经做过手机验证
			return 2;
		}

		String password = StrUtils.getRandomString(5);// 随机生成一个五位长的字符
		String encryptByJsPw = CallbackJS.getJSMD5(password);// 之前恶心的设计,密码在前端用js加密过,所以现在也要加密
		try {
			String encryptPw = Encrypt.encryptString(Encrypt.ENCRY_STYLE_MD5,
					encryptByJsPw);
			user.setPassword(encryptPw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int code = SmsUtils.sendMobileSmsByMdao(mobilePhone,
				"福域网站车主信息已绑定成功！您的用户名:" + username + ",密码:" + password
						+ ",为了您的信息安全，请至个人中心修改密码。");
		if (code==0) {
			user.setUsername(username);
			user.setMobilePhoneAuthTime(new Date());
			user.setMobilePhoneAuth(true);
			user.setActivation(true);
			dao.update(user);
			CmsUser cmsUser = cmsUserdao.findById(user.getId());
			cmsUser.setUsername(username);
			cmsUserdao.update(cmsUser);
			BbsUser bbsUser = bbsUserdao.findById(user.getId());
			bbsUser.setUsername(username);
			bbsUserdao.save(bbsUser);
			return 3;
		}
		return 4;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.core.manager.UnifiedUserMng#updatePoint(java.lang.Integer,
	 * java.lang.Long)
	 */
	@Override
	@Transactional
	public UnifiedUser updatePoint(Integer userId, Long pointNum) {
		UnifiedUser unifiedUser = this.lock(userId);
		if (unifiedUser != null) {
			// Long
			// historyPoint=(unifiedUser.getHistoryPoint()==null?pointNum:unifiedUser.getHistoryPoint()+pointNum);
			Long currentPoint = (unifiedUser.getCurrentPoint() == null ? pointNum
					: unifiedUser.getCurrentPoint() + pointNum);
			unifiedUser.setCurrentPoint(currentPoint);
			// unifiedUser.setHistoryPoint(historyPoint);
			this.dao.update(unifiedUser);
			this.unLock(userId);
		}
		return unifiedUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jeecms.core.manager.UnifiedUserMng#lock(java.lang.Integer)
	 */
	@Override
	public UnifiedUser lock(Integer userId) {
		return this.dao.lockUser(userId, LockMode.UPGRADE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jeecms.core.manager.UnifiedUserMng#unLock(java.lang.Integer)
	 */
	@Override
	public UnifiedUser unLock(Integer userId) {
		return this.dao.lockUser(userId, LockMode.NONE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.core.manager.UnifiedUserMng#subtractionPoint(java.lang.Integer
	 * , java.lang.Long)
	 */
	@Override
	public UnifiedUser subtractionPoint(UnifiedUser unifiedUser, Long pointNum) {
		if (unifiedUser != null) {
			Long currentPoint = (unifiedUser.getCurrentPoint() == null ? pointNum
					: unifiedUser.getCurrentPoint() - pointNum);
			unifiedUser.setCurrentPoint(currentPoint);
			unifiedUser = this.dao.update(unifiedUser);
		}
		return unifiedUser;
	}

	
	
	/* (non-Javadoc)
	 * @see com.jeecms.core.manager.UnifiedUserMng#findByOpenId(java.lang.String)
	 */
	@Override
	public UnifiedUser findByOpenId(String openId) {
		return  this.dao.getByOpenId(openId);
	}



	private ConfigMng configMng;
	
	private PwdEncoder pwdEncoder;
	
	private UnifiedUserDao dao;
	
	@Autowired
	private CmsUserDao cmsUserdao;

	@Autowired
	private BbsUserDao bbsUserdao;

	@Autowired
	public void setConfigMng(ConfigMng configMng) {
		this.configMng = configMng;
	}

	@Autowired
	public void setPwdEncoder(PwdEncoder pwdEncoder) {
		this.pwdEncoder = pwdEncoder;
	}

	@Autowired
	public void setDao(UnifiedUserDao dao) {
		this.dao = dao;
	}

	@Autowired
	private Md5pwdMng md5pwdMng;

}