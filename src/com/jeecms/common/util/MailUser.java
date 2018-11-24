package com.jeecms.common.util;
import java.io.*;

public class MailUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6673282853826265130L;
	static String displayName;// �ʼ���ʾ���
	static String userName;// �û���
	static String passWord;// ����
	static String pop3Server;// POP3������
	static String smtpServer;// SMTP������
	static boolean validateNeeded;// ��֤��Ϣ

	public static void setDisplayName(String DisplayName) {
		displayName = DisplayName;
	}

	public static void setUserName(String UserName) {
		userName = UserName;
	}

	public static void setPassWord(String PassWord) {
		passWord = PassWord;
	}

	public static void setPop3Server(String Pop3Server) {
		pop3Server = Pop3Server;
	}

	public static void setSmtpServer(String SmtpServer) {
		smtpServer = SmtpServer;
	}

	public static void setValidateNeeded(boolean ValidateNeeded) {
		validateNeeded = ValidateNeeded;
	}

	public static String getDisplayName() {
		return displayName;
	}

	public static String getUserName() {
		return userName;
	}

	public static String getPassWord() {
		return passWord;
	}

	public static String getPop3Server() {
		return pop3Server;
	}

	public static String getSmtpServer() {
		return smtpServer;
	}

	public static boolean getValidateNeeded() {
		return validateNeeded;
	}
}