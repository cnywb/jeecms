package com.jeecms.common.util;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator {
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(MailUser.getUserName(), MailUser
				.getPassWord());
	}
}