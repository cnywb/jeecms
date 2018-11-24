package com.jeecms.common.kindeditor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.common.util.DateUtils;
import com.jeecms.common.util.PropUtils;
import com.jeecms.common.util.ImageMarkUtil;



@Controller@Scope("prototype")
@RequestMapping("/common/kindeditor/*")
public class EditorController {
	
	
	
	@RequestMapping("upload.jhtml")
	@SuppressWarnings("unchecked")
	public void upload(@RequestParam MultipartFile[] imgFile,String dir,String imgWidth,String imgHeight,String align,String imgTitle,String localUrl,Boolean needWaterMark,HttpServletResponse response,HttpServletRequest request){
		CmsUser user = CmsUtils.getUser(request);
		if(user==null){
			try {
				response.getWriter().println(getError("请先登录。"));
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long userId=user.getId();
        try{
		String savePath = request.getSession().getServletContext().getRealPath("/") + PropUtils.getPropertyValue("editor.properties","attached_save_path")+"/";

		//文件保存目录URL
		String contextPath=request.getContextPath();
		if(!"".equals(contextPath)){
			contextPath=contextPath+"/";
		}
		contextPath=contextPath.replaceAll(" ", "");
		String saveUrl=contextPath+PropUtils.getPropertyValue("editor.properties","attached_save_path")+"/";

		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image","gif,jpg,jpeg,png,bmp");
		//extMap.put("flash", "swf,flv");
		//extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		//extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		//最大文件大小2M
		long maxSize =2097152; //1024*1024*2 

		response.setContentType("text/html; charset=UTF-8");

		if(!ServletFileUpload.isMultipartContent(request)){
			try {
				response.getWriter().println(getError("请选择文件。"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return;
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
				response.getWriter().println(getError("上传目录不存在。"));
				return;
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			response.getWriter().println(getError("上传目录没有写权限。"));
			return;
		}
		if (dir == null) {
			dir = "image";
		}
		if(!extMap.containsKey(dir)){
			response.getWriter().println(getError("目录名不正确。"));
			return;
		}
		//创建文件夹
		savePath += dir +"/"+userId+"/";
		saveUrl += dir +"/"+userId+"/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		String ymd = DateUtils.format(new Date(),DateUtils.FORMAT_DATE_YYYYMMDD);
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
				BufferedInputStream bis = null;      
				BufferedOutputStream bos = null; 
				for(int i = 0,size = imgFile.length;i<size;i++){      
					MultipartFile file = imgFile[i];      
				    try { 
				    	if(file.getSize()>maxSize){
				    		try {
								response.getWriter().println(getError("上传文件大小超过限制。"));
							} catch (IOException e) {
								e.printStackTrace();
							}
				    	}
				    	String uploadFileName=file.getOriginalFilename();
				    	String extendName=uploadFileName.substring(uploadFileName.lastIndexOf("."),uploadFileName.length());
				    	if(!checkFileType(uploadFileName, extMap.get(dir))){
				    		response.getWriter().print(getError("不允许上传该文件类型！"));
				    		return;
				    	}
				    	String fileFullName =DateUtils.format(new Date(), DateUtils.FORMAT_DATE_TIME_YYYYMMDDHHMMSS)+"_"+ new Random().nextInt(1000);
				        bis = new BufferedInputStream(file.getInputStream());  
				        String newFileName=fileFullName+extendName;
				        bos = new BufferedOutputStream(new FileOutputStream(new File(savePath,newFileName)));      
				        Streams.copy(bis, bos, true);
				        
				        if(needWaterMark!=null&&needWaterMark==true){
				        String markImageSrc=request.getSession().getServletContext().getRealPath("/")+"/r/cms/www/blue/bbs_forum/images/water_mark_logo.png" ;
				        ImageMarkUtil.markImage(markImageSrc, savePath+newFileName, 0);
				        }
				        JSONObject obj = new JSONObject();
						obj.put("error",0);
						obj.put("url",saveUrl+newFileName);
						response.getWriter().println(obj.toJSONString());
					    } catch (Exception e) {      
				    	response.getWriter().print(getError("上传出错！"));
				        e.printStackTrace();      
				    }          
				}
        } catch (Exception e) {      
	    	
	        e.printStackTrace(); 
        }
	}
	
	@RequestMapping("manage.jhtml")
	@SuppressWarnings("unchecked")
	public void manage(HttpServletResponse response,HttpServletRequest request){
		CmsUser user = CmsUtils.getUser(request);
		if(user==null){
			try {
				response.getWriter().println(getError("请先登录。"));
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long userId=user.getId();
		  
		try{
			
		String rootPath = request.getSession().getServletContext().getRealPath("/") + PropUtils.getPropertyValue("editor.properties","attached_save_path")+"/";
		//文件保存目录URL
		String rootUrl= request.getContextPath()+PropUtils.getPropertyValue("editor.properties","attached_save_path")+"/";
		//图片扩展名
		String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};
		String dirName = request.getParameter("dir")==null?"image": request.getParameter("dir");
		if (dirName != null) {
			if(!Arrays.<String>asList(new String[]{"image", "flash", "media", "file"}).contains(dirName)){
				try {
					response.getWriter().println("Invalid Directory name.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
			rootPath += dirName+"/"+userId+"/";
			rootUrl += dirName +"/"+userId+"/";
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		//根据path参数，设置各路径和URL
		String path = request.getParameter("path") != null ? request.getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}

		//排序形式，name or size or type
		String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

		//不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			try {
				response.getWriter().println("Access is not allowed.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		//最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			try {
				response.getWriter().println("Parameter is not valid.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		//目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if(!currentPathFile.isDirectory()){
			try {
				response.getWriter().println("Directory does not exist.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}

		//遍历目录取的文件信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if(currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if(file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if(file.isFile()){
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		JSONObject result = new JSONObject();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().println(result.toJSONString());
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	    }
	}
	
	public class NameComparator implements Comparator {
		@SuppressWarnings("rawtypes")
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String)hashA.get("filename")).compareTo((String)hashB.get("filename"));
			}
		}
	}
	public class SizeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long)hashA.get("filesize")) > ((Long)hashB.get("filesize"))) {
					return 1;
				} else if (((Long)hashA.get("filesize")) < ((Long)hashB.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}
	public class TypeComparator implements Comparator {
		@SuppressWarnings("rawtypes")
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String)hashA.get("filetype")).compareTo((String)hashB.get("filetype"));
			}
		}
	}


	
	@SuppressWarnings("unchecked")
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
	
	private boolean checkFileType(String fileName,String authFileType){
		String fileType=fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length()).toLowerCase();
		if(authFileType.indexOf(fileType)==-1){
			return false;
		}
		return true;
	}
}
