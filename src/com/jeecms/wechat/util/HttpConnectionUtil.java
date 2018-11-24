package com.jeecms.wechat.util;



import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公众平台通用接口工具类
 * 
 * @author 
 * @date 
 */
public class HttpConnectionUtil {
	private static Logger log = LoggerFactory.getLogger(HttpConnectionUtil.class);

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
		log.info("发起接口请求:"+requestUrl);
		String retVal = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			retVal=buffer.toString();
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return retVal;
	}
	
	
	public static String uploadMedia(String requestUrl,MultipartFile file) {
		log.info("发起接口请求:"+requestUrl);
		String boundary = "------------7da2e536604c8";
		String retVal = null;
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();    
            httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod("POST");
			httpUrlConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			String contentType=file.getContentType();
			String fileExt =file.getOriginalFilename();
			fileExt=fileExt.substring(fileExt.lastIndexOf("."), fileExt.length());
				OutputStream outputStream = httpUrlConn.getOutputStream();
				outputStream.write(("--" + boundary + "\r\n").getBytes());
			    outputStream.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n", fileExt).getBytes());
			    outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());
			    BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
			      byte[] buf = new byte[8096];
			      int size = 0;
			      while ((size = bis.read(buf)) != -1) {
			        // 将媒体文件写到输出流（往微信服务器写数据）
			        outputStream.write(buf, 0, size);
			      }
			      // 请求体结束
			      outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			      outputStream.close();
			      bis.close();
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			retVal=buffer.toString();
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		log.info(retVal);
		return retVal;
	}
	
	
	public static String uploadQyMedia(String requestUrl,MultipartFile file) {
		log.info("发起接口请求:"+requestUrl);
		String boundary = "------------7da2e536604c8";
		String retVal = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod("POST");
			httpUrlConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			String contentType=file.getContentType();
			String fileExt =file.getOriginalFilename();
			fileExt=fileExt.substring(fileExt.lastIndexOf("."), fileExt.length());
				OutputStream outputStream = httpUrlConn.getOutputStream();
				outputStream.write(("--" + boundary + "\r\n").getBytes());
			    outputStream.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n", fileExt).getBytes());
			    outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());
			    BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
			      byte[] buf = new byte[8096];
			      int size = 0;
			      while ((size = bis.read(buf)) != -1) {
			        // 将媒体文件写到输出流（往微信服务器写数据）
			        outputStream.write(buf, 0, size);
			      }
			      // 请求体结束
			      outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			      outputStream.close();
			      bis.close();
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			retVal=buffer.toString();
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		log.info(retVal);
		return retVal;
	}
	
	public static String downloadMedia(String requestUrl, String mediaId,String savePath) {
	    String filePath = null;
	
	    try {
	    	File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
	      URL url = new URL(requestUrl);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();    
	      conn.setDoInput(true);
	      conn.setRequestMethod("GET");

	      if (!savePath.endsWith("/")) {
	        savePath += "/";
	      }
	      // 根据内容类型获取扩展名
	      String fileExt = conn.getHeaderField("Content-Type");
	     
	      log.info("Content-Type"+fileExt);
	      fileExt=fileExt.substring(fileExt.lastIndexOf("/")+1, fileExt.length());
	      // 将mediaId作为文件名
	      filePath = savePath + mediaId +"."+ fileExt;

	      BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
	      FileOutputStream fos = new FileOutputStream(new File(filePath));
	      byte[] buf = new byte[8096];
	      int size = 0;
	      while ((size = bis.read(buf)) != -1)
	        fos.write(buf, 0, size);
	      fos.close();
	      bis.close();

	      conn.disconnect();
	      String info = String.format("下载媒体文件成功，filePath=" + filePath);
	      System.out.println(info);
	    } catch (Exception e) {
	      filePath = null;
	      String error = String.format("下载媒体文件失败：%s", e);
	      System.out.println(error);
	    }
	    return filePath;
	  }
	
	public static String downloadQyMedia(String requestUrl, String mediaId,String savePath) {
	    String filePath = null;
	
	    try {
	    	File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
	      URL url = new URL(requestUrl);
	      HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();    
	      conn.setDoInput(true);
	      conn.setRequestMethod("GET");

	      if (!savePath.endsWith("/")) {
	        savePath += "/";
	      }
	      // 根据内容类型获取扩展名
	      String fileExt = conn.getHeaderField("Content-Type");
	     
	      log.info("Content-Type"+fileExt);
	      fileExt=fileExt.substring(fileExt.lastIndexOf("/")+1, fileExt.length());
	      // 将mediaId作为文件名
	      filePath = savePath + mediaId +"."+ fileExt;

	      BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
	      FileOutputStream fos = new FileOutputStream(new File(filePath));
	      byte[] buf = new byte[8096];
	      int size = 0;
	      while ((size = bis.read(buf)) != -1)
	        fos.write(buf, 0, size);
	      fos.close();
	      bis.close();

	      conn.disconnect();
	      String info = String.format("下载媒体文件成功，filePath=" + filePath);
	      System.out.println(info);
	    } catch (Exception e) {
	      filePath = null;
	      String error = String.format("下载媒体文件失败：%s", e);
	      System.out.println(error);
	    }
	    return filePath;
	  }
}