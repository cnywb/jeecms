<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.json.simple.*" %>
<%@page import="com.jeecms.common.util.ImageScale"%>
<%@page import="java.util.Properties"%>
<%@page import="com.jeecms.common.util.UploadImage"%>
<%@page import="java.util.concurrent.locks.Lock"%>
<%@page import="java.util.concurrent.locks.ReentrantLock"%>
<%@page import="org.slf4j.Logger"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%

/**
 * KindEditor JSP
 * 
 * 本JSP程序是演示程序，建议不要直接在实际项目中使用。
 * 如果您确定直接使用本程序，使用之前请仔细确认相关安全设置。
 * 
 */
Properties prop = new Properties();
prop.load(UploadImage.class.getResourceAsStream("/fckeditor.properties"));
Logger log = LoggerFactory.getLogger(this.getClass());
//目标目录
String destPath = prop.getProperty("destPath");
//服务器访问静态文件路径根节点
String httpPath = prop.getProperty("httpPath");
String picturePath = prop.getProperty("picturePath");
//文件保存目录路径
//String savePath = pageContext.getServletContext().getRealPath("/") + "attached/";
//文件保存目录URL
//String saveUrl  = request.getContextPath() + "/attached/";
//定义允许上传的文件扩展名
String[] fileTypes = {"image/bmp","image/png","image/gif","image/jpeg",
		"image/jpg","image/pjpeg","image/pjpg","image/x-png","gif", "jpg", "jpeg", "png", "bmp"};
//最大文件大小
long maxSize = 3*1024*1024l;

response.setContentType("text/html; charset=UTF-8");

if(!ServletFileUpload.isMultipartContent(request)){
	out.println(getError("请选择文件。"));
	return;
}
//检查目录
File uploadDir = new File(destPath + "/" + picturePath);
if(!uploadDir.isDirectory()){
	uploadDir.mkdirs();
}
//检查目录写权限
if(!uploadDir.canWrite()){
	out.println(getError("上传目录没有写权限。"));
	return;
}
String hosts = "http://" + request.getHeader("Host");
FileItemFactory factory = new DiskFileItemFactory();
ServletFileUpload upload = new ServletFileUpload(factory);
upload.setHeaderEncoding("UTF-8");
List items = upload.parseRequest(request);
Iterator itr = items.iterator();
String urlpath = "";
while (itr.hasNext()) {
	FileItem item = (FileItem) itr.next();
	String fileName = item.getName();
	long fileSize = item.getSize();
	if (!item.isFormField() && !StringUtils.isBlank(fileName) && fileSize>0) {
		//System.out.println(fileName);
		//检查文件大小
		if(item.getSize() > maxSize){
			out.println(getError("上传文件大小超过限制。"));
			return;
		}
		//检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if(!Arrays.<String>asList(fileTypes).contains(fileExt)){
			out.println(getError("上传文件扩展名是不允许的扩展名。"));
			return;
		}
		String newFileName = "";
		final Lock lock = new ReentrantLock();
		lock.lock();
		try {
			//加锁为防止文件名重复 
			newFileName = System.currentTimeMillis()
					+ fileName.substring(fileName.lastIndexOf("."),
							fileName.length());
		}finally {
			lock.unlock();
		}
		try{
			
			File uploadedFile = new File(uploadDir.getAbsolutePath() + "/" + newFileName);
			item.write(uploadedFile);
			//压缩图片
			int dot = newFileName.lastIndexOf(".");
			//int oblique = newFileName.lastIndexOf("/");
			String prefix = newFileName.substring(0, dot);
			String postfix = newFileName.substring(dot, newFileName.length());
			ImageScale.resizeFix(uploadedFile, new File(destPath + "/" + picturePath + "/" + prefix + "_0" + postfix), 700, 600);
			urlpath += hosts + "/" + httpPath + "/" + picturePath +  "/" + prefix + "_0" + postfix  + ",";
			
		}catch(Exception e){
			
			log.error("upload_json.jsp error:",e);
			out.println(getError("上传文件失败。"));
			return;
		}
	}
}
int index = urlpath.lastIndexOf(",");
urlpath = urlpath.substring(0,index);
log.info("urlpath---------->>>>>>>>>>", urlpath);
JSONObject obj = new JSONObject();
obj.put("error", 0);
obj.put("url", urlpath);
out.println(obj.toJSONString());

%>
<%!
private String getError(String message) {
	JSONObject obj = new JSONObject();
	obj.put("error", 1);
	obj.put("message", message);
	return obj.toJSONString();
}
%>
