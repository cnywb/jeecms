<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="java.io.*"%>
<%@page import="org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper"%>
<%@page import="org.slf4j.Logger"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="com.jeecms.common.util.ImageScale"%>
<%@page import="java.util.Properties"%>
<%@page import="com.jeecms.common.util.UploadImage"%>
<%
	//Struts2  请求 包装过滤器
	MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
	// 获得上传的文件名 
	String[] fileNames = wrapper.getFileNames("imgFile");
	
	Properties prop = new Properties();
	prop.load(UploadImage.class.getResourceAsStream("/fckeditor.properties"));
	//目标目录
	String destPath = prop.getProperty("destPath");
	//服务器访问静态文件路径根节点
	String httpPath = prop.getProperty("httpPath");
	String picturePath = prop.getProperty("picturePath");
	//获得未见过滤器 
	File[] files = wrapper.getFiles("imgFile");
	//----------- 重新构建上传文件名----------------------
	
	String urlpath = "";
	for(int i=0;i<files.length;i++)
	{
		File file = files[i];
		if(null != file && file.length()>0)
		{
			try{
				String newFileName = UploadImage.setUploadFile(request,file,fileNames[i],picturePath);
				//压缩图片
				int dot = newFileName.lastIndexOf(".");
				int oblique = newFileName.lastIndexOf("/");
				String prefix = newFileName.substring(oblique + 1, dot);
				String postfix = newFileName.substring(dot, newFileName.length());
				ImageScale.resizeFix(file, new File(destPath + "/" + picturePath + "/" + prefix + "_0" + postfix), 700, 600);
				urlpath += "/" + httpPath + "/" + picturePath +  "/" + prefix + "_0" + postfix + ",";
			}catch(Exception ex){
				Logger log = LoggerFactory.getLogger(this.getClass());
				log.error("upload_json.jsp error:",ex);
			}
		}
	}
	int index = urlpath.lastIndexOf(",");
	urlpath = urlpath.substring(0,index);
	//发送给KE 
	out.println("<html><head><title>Insert Image</title><meta http-equiv='content-type' content='text/html; charset=gbk'/></head><body>");
	out.println("<script type='text/javascript'>");
	out.println("parent.parent.KE.plugin['image'].insert('"
			+ wrapper.getParameter("id") + "','" + urlpath + "','"
			+ wrapper.getParameter("imgTitle") + "','"
			+ wrapper.getParameter("imgWidth") + "','"
			+ wrapper.getParameter("imgHeight") + "','"
			+ wrapper.getParameter("imgBorder") + "','"
			+ wrapper.getParameter("align") + "')</script>");
	out.println("</body></html>");
%>