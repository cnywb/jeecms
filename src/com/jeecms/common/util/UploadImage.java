package com.jeecms.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
public class UploadImage {
	//限制文件类型
	private static String[] fileTypes = {"image/bmp","image/png","image/gif","image/jpeg",
		"image/jpg","image/pjpeg","image/pjpg","image/x-png"};
	//限制文件大小
	private static long fileSize = 1024*1024l;
	
	private static Properties prop = new Properties();
	
	private static Logger log= org.slf4j.LoggerFactory.getLogger(UploadImage.class);

	/**
	 * 根据上传文件获取其真实读取路径
	 * @param request
	 * @param file
	 * @param fileName
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String setUploadFile(HttpServletRequest request,File file,String fileName,String path) throws IOException{
		log.info("begin method:setUploadFile()");
		//获取文件存放共享目录
		prop.load(UploadImage.class.getResourceAsStream("/fckeditor.properties"));
		//目标目录
		String destPath = prop.getProperty("destPath");
		//服务器访问静态文件路径根节点
		String httpPath = prop.getProperty("httpPath");
		final Lock lock = new ReentrantLock();
		String newName = null;
		lock.lock();
		try {
			//加锁为防止文件名重复 
			newName = System.currentTimeMillis()
					+ fileName.substring(fileName.lastIndexOf("."),
							fileName.length());
		}finally {
			lock.unlock();
		}
		//检查图片类型
		String uploadfiletypeString = new MimetypesFileTypeMap().getContentType(new File(fileName));
		if(!checkFileImg(uploadfiletypeString)){
			return fileName;
		}
		//------------ 锁结束 -------------
		//获取文件输出流 
		//FileOutputStream fos = new FileOutputStream(request.getSession().getServletContext().getRealPath("/")+ path.replaceAll("/", "\\\\") + "\\" + newName);
		File ff = new File(destPath + "/" + path );
		if(!ff.exists()){
			ff.mkdirs();
		}
		String newFileName = "";
		InputStream in = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(ff.getAbsolutePath() + "/" + newName);
			//设置 KE 中的图片文件地址 
			/**String newFileName = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/"+path+"/" + newName;**/
			newFileName = "/" + httpPath + "/" + path + "/" + newName;
			byte[] buffer = new byte[1024];
			//获取内存中当前文件输入流 
			in = new FileInputStream(file);
			int num = 0;
			while ((num = in.read(buffer)) > 0) {
				fos.write(buffer, 0, num);
			}
		} catch (Exception e) {
			log.error("",e);
			//e.printStackTrace(System.err);
		} finally {
			in.close();
			fos.close();
		}
		log.info("end method:setUploadFile()");
		return newFileName;
	}
	
	/**
	 * 根据上传文件获取其真实读取路径
	 * @param request
	 * @param file
	 * @param fileName
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String setUploadFileWithRealfilename(HttpServletRequest request,File file,String fileName,String path) throws IOException{
		log.info("begin method:setUploadFile()");
		//获取文件存放共享目录
		prop.load(UploadImage.class.getResourceAsStream("/fckeditor.properties"));
		//目标目录
		String destPath = prop.getProperty("destPath");
		//服务器访问静态文件路径根节点
		String httpPath = prop.getProperty("httpPath");
		final Lock lock = new ReentrantLock();
		String newName = null;
		lock.lock();
		try {
			//加锁为防止文件名重复 
			newName = fileName;
//				System.currentTimeMillis()
//					+ fileName.substring(fileName.lastIndexOf("."),
//							fileName.length());
		}finally {
			lock.unlock();
		}
		
		//------------ 锁结束 -------------
		//获取文件输出流 
		//FileOutputStream fos = new FileOutputStream(request.getSession().getServletContext().getRealPath("/")+ path.replaceAll("/", "\\\\") + "\\" + newName);
		File ff = new File(destPath + "/" + path );
		if(!ff.exists()){
			ff.mkdirs();
		}
		String newFileName = "";
		InputStream in = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(ff.getAbsolutePath() + "/" + newName);
			//设置 KE 中的图片文件地址 
			/**String newFileName = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/"+path+"/" + newName;**/
			newFileName = "/" + httpPath + "/" + path + "/" + newName;
			byte[] buffer = new byte[1024];
			//获取内存中当前文件输入流 
			in = new FileInputStream(file);
			int num = 0;
			while ((num = in.read(buffer)) > 0) {
				fos.write(buffer, 0, num);
			}
		} catch (Exception e) {
			log.error("",e);
			//e.printStackTrace(System.err);
		} finally {
			in.close();
			fos.close();
		}
		log.info("end method:setUploadFile()");
		return newFileName;
	}
	
	/**
	 * 根据上传文件获取其真实读取路径
	 * @param request
	 * @param file
	 * @param fileName
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String setUploadFileNoModifyFileName(File file,String fileName,String path) throws IOException{
		log.info("begin method:setUploadFileNoModifyFileName()");
		//获取文件存放共享目录
		prop.load(UploadImage.class.getResourceAsStream("/fckeditor.properties"));
		//目标目录
		String destPath = prop.getProperty("destPath");
		//服务器访问静态文件路径根节点
		String httpPath = prop.getProperty("httpPath");
		//获取文件输出流 
		//FileOutputStream fos = new FileOutputStream(request.getSession().getServletContext().getRealPath("/")+ path.replaceAll("/", "\\\\") + "\\" + newName);
		File ff = new File(destPath + "/" + path );
		if(!ff.exists()){
			ff.mkdirs();
		}
		String newFileName = "";
		InputStream in = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(ff.getAbsolutePath() + "/" + fileName);
			//设置 KE 中的图片文件地址 
			/**String newFileName = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/"+path+"/" + newName;**/
			newFileName = "/" + httpPath + "/" + path + "/" + fileName;
			byte[] buffer = new byte[1024];
			//获取内存中当前文件输入流 
			in = new FileInputStream(file);
			int num = 0;
			while ((num = in.read(buffer)) > 0) {
				fos.write(buffer, 0, num);
			}
		} catch (Exception e) {
			log.error("",e);
			//e.printStackTrace(System.err);
			return "";
		} finally {
			in.close();
			fos.close();
		}
		log.info("end method:setUploadFileNoModifyFileName()");
		return newFileName;
	}
	
	/**
	 * 验证文件类型是否为图片
	 * @param fileType
	 * @return
	 */
	public static boolean checkFileImg(String fileType){	
		boolean flag = false;
		for(String type:fileTypes){
			if(type.equals(fileType))
				flag = true;
		}
		return flag;
	}
	
	/**
	 * 判断文件是否超长
	 * @param file
	 * @return
	 */
	public static boolean checkFileSize(File file){
		boolean flag = false;
		long size = file.length();
		if(size<=fileSize)
			flag = true;
		return flag;
	}
//	public static void main(String[] args) {
//		System.out.println("aa/bb/cc".replaceAll("/", "\\\\"));
//	}
}
