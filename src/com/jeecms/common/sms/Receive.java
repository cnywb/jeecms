package com.jeecms.common.sms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * 接收推送实现
 * 
 * @author user
 * 
 */
public class Receive extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		// String json = IOUtils.toString(request.getInputStream());
		//
		// System.out.print(json);
		// 提供的用户名
		String user = request.getParameter("uid");
		// 提供的密码明文
		String pwd = request.getParameter("pwd");
		String type = request.getParameter("type");
		String jsonData = request.getParameter("jsonData");
		if (type.equals("mo")) {
			// 上行短信
			System.out.print(jsonData);
		} else {
			// 发送状态
			System.out.print(jsonData);
		}
	}

}
