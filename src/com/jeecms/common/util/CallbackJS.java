package com.jeecms.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public final class CallbackJS {
	public static String getJSMD5(String password) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		try {
			Invocable invocableEngine = (Invocable) engine;
			Reader reader = new BufferedReader(new InputStreamReader(CallbackJS.class.getResourceAsStream("/jsmd5.js")));
			// 开始执行ajava.js里的程序
			engine.eval(reader);
			// 不带参数调用sayHello方法
			// invocableEngine.invokeFunction("sayHello");
			// 带参数调用sayHello方法
			String res = (String) invocableEngine.invokeFunction("md5",password);
			//System.out.println(Encrypt.encryptString(Encrypt.ENCRY_STYLE_MD5,res));//99c00d1610cbba7ba966b699cbc317a1

			return res;
		} catch (Exception ex) {
			ex.printStackTrace();//1FGrOLSGo5bGlKy/p2Vwog==
		}
		return "";
	}
	
	public static void main(String[] args) {
		CallbackJS.getJSMD5("119119");
	}
}
