package com.jeecms.common.util;





import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.jeecms.common.sms.ResultMessage;
import com.jeecms.common.sms.SMSMessage;
import com.jeecms.common.sms.SendSMS;






public class SmsUtils {
	
	public static void main(String[] args){
		sendMobileSmsByMdao("18621593970", "尊敬的客户，长安福特友情提示：您的微客服车主认证已成功。即刻前往长安福特微客服随时浏览更多信息，专享车主特权。");
	}
	
	
	//中昱短信发送
	public static String sendMobileSmsByZy(String phones,String content){
           
   		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod("http://10658.cc/webservice/api?method=SendSms"); 
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		//多个手机号码请用英文,号隔开	
		        NameValuePair[] data = {//提交短信
			    new NameValuePair("account", "vs_wunderman"), 
			    //new NameValuePair("password", Encrypt.MD5Encode(password)),
			    new NameValuePair("password", "wun@123"),
			    new NameValuePair("mobile", phones),
			    new NameValuePair("pid", "40"),					
			    new NameValuePair("time", ""),
				new NameValuePair("content",content+"【长安福特】"),//最后的签名不能删除否则无法发送
		};
                method.setRequestBody(data);		
		try {
			client.executeMethod(method);	
			String SubmitResult =method.getResponseBodyAsString();
		    Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();
			String code = root.elementText("code");	
			String msg = root.elementText("msg");
			if("2".equals(code)){//发送成功
			   
			}
			return code;
		}catch (Exception e) {
            e.printStackTrace();
		} 
		return null;
   	 }
	
	
   public static int sendMobileSmsByMdao(String mobilePhoneNo,String content){
	   
	   String url = PropUtils.getPropertyValue("sms.properties","sms.dmao.host");
       SMSMessage sm = new SMSMessage();
       /**
        * 先设置Timestamp 取当前时间，
        * SMSMessage 内部会自动生成 Timestamp 要求字符串
        * 一定要在设置密码前面，密码加密用到 Timestamp
        * 密码md5加密也是SMSMessage 内部实现
        * 
        */
       sm.setTimestamp(new Date());
       sm.setCid(PropUtils.getPropertyValue("sms.properties","sms.dmao.cid"));
       sm.setUid(PropUtils.getPropertyValue("sms.properties","sms.dmao.uid"));
       sm.setPwd(PropUtils.getPropertyValue("sms.properties","sms.dmao.pwd"));
       sm.setContent(content);
       String[] mobilePhoneNoArray=mobilePhoneNo.split(",");
       sm.setMobiles(mobilePhoneNoArray);
       ResultMessage rm= SendSMS.sendSMS(url,sm);
       return rm.getStateCode();
   }
	
	
	

}
