package com.jeecms.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jeecms.common.email.EmailSender;



public class SendMail {
	public static SendMail sendmail;
	private static final Logger log = LoggerFactory.getLogger(SendMail.class);

	static synchronized public SendMail getInstance() {
		if (sendmail == null) {
			sendmail = new SendMail();
		}
		return sendmail;
	}

	public void sendHtmlMail(String mailcontext, String addr,String subject) {
		String path = this.getClass().getResource("/").getPath();
		java.io.File f = new java.io.File(path + "mail.properties");
		Properties transportProperties = new Properties();
		try {
			java.io.InputStream in = new java.io.FileInputStream(f);
			try {
				transportProperties.load(in);
			} catch (IOException e) {
				log.error("",e);
			}
		} catch (FileNotFoundException e) {
			log.error("",e);
		}
		String hostName = (String) transportProperties
				.get("apm.mail.smtp.host");
		MailUser.setDisplayName("");
		MailUser.setUserName((String) transportProperties
				.get("apm.mail.username"));
		MailUser.setPassWord((String) transportProperties
				.get("apm.mail.logpass"));
		MailUser.setSmtpServer(hostName);
		MailUser.setValidateNeeded(true);
		String smtpServer = MailUser.getSmtpServer();
		Session mailSession = null;
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", smtpServer);
			props.put("mail.smtp.auth", "true");
			String port = (String)transportProperties.get("apm.mail.smtp.port");
			if(port!=null && !port.equals(""))
				props.put("mail.smtp.port",port );
			if (MailUser.getValidateNeeded())
				mailSession = Session.getInstance(props,
						new MailAuthenticator());
			else
				mailSession = Session.getInstance(props, null);
			Message msg = new MimeMessage(mailSession);
			msg.setSentDate(new Date());
			msg.setFrom(new InternetAddress(MailUser.getUserName() + "@"
					+ (String)transportProperties.get("apm.mail.smtp.endname"),
					(String)transportProperties.get("apm.mail.smtp.alias")));
			msg.setRecipient(Message.RecipientType.TO,
					new InternetAddress(addr));
			msg.setSubject(subject);
			msg.setContent(mailcontext, "text/html;charset=utf-8");
			Transport.send(msg);
		} catch (Exception e) {
			log.error("",e);
		}
	}
	
	public void sendHtmlMail(String mailcontext, String addr,String subject,EmailSender email) {
		String hostName = email.getHost();
		MailUser.setDisplayName("");
		MailUser.setUserName(email.getUsername());
		MailUser.setPassWord(email.getPassword());
		MailUser.setSmtpServer(hostName);
		MailUser.setValidateNeeded(true);
		String smtpServer = MailUser.getSmtpServer();
		Session mailSession = null;
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", smtpServer);
			props.put("mail.smtp.auth", "true");
			String port = email.getPort().toString();
			if(port!=null && !port.equals(""))
				props.put("mail.smtp.port",port );
			if (MailUser.getValidateNeeded())
				mailSession = Session.getInstance(props,
						new MailAuthenticator());
			else
				mailSession = Session.getInstance(props, null);
			Message msg = new MimeMessage(mailSession);
			msg.setSentDate(new Date());
			msg.setFrom(new InternetAddress(MailUser.getUserName() + "@hexun.com",
					"长安福特俱乐部"));
			msg.setRecipient(Message.RecipientType.TO,
					new InternetAddress(addr));
			msg.setSubject(subject);
			msg.setContent(mailcontext, "text/html;charset=utf-8");
			Transport.send(msg);
		} catch (Exception e) {
			log.error("",e);
		}
	}

	public static void main(String args[]) {
//		SendMail sm = new SendMail();
//		sm.sendHtmlMail("testsender", "xwlvwq@163.com","welcome you!");
//		EmailSender email = new 
//		SendMail.getInstance().sendHtmlMail("find password", "xwlvwq@163.com", "找回密码", email)
	}

}
