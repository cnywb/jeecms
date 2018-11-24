package com.jeecms.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;

public class Base64Utils {

	
	 public static void main(String[] args)  
	    {  
	      
	        FileInputStream f;
			try {
				f = new FileInputStream("D:\\image.txt");//必须去除头文件
				 BufferedReader dr=new BufferedReader(new InputStreamReader(f,"utf-8"));
				 String line;
				 String imageStr="";
				 while((line=dr.readLine())!=null){ 
				       imageStr=imageStr+line;
			     } 
			    // base64toImage(imageStr,"D:\\xxx.jpg"); 
			 //    System.out.println(imageStr);
			     System.out.println(imageTobase64("D:\\20150917150050_994.jpg"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	  
	    
	      
	    }  
	    //图片转化成base64字符串  
	    public static String imageTobase64(String imgFile)  
	    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
	      
	        InputStream in = null;  
	        byte[] data = null;  
	        //读取图片字节数组  
	        try   
	        {  
	            in = new FileInputStream(imgFile);          
	            data = new byte[in.available()];  
	            in.read(data);  
	            in.close();  
	        }   
	        catch (IOException e)   
	        {  
	            e.printStackTrace();  
	        }  
	        //对字节数组Base64编码  
	        BASE64Encoder encoder = new BASE64Encoder();  
	        return encoder.encode(data);//返回Base64编码过的字节数组字符串  
	    }  
	      
	    //base64字符串转化成图片  
	    public static boolean base64toImage(String imgStr,String imgFilePath)  
	    {   //对字节数组字符串进行Base64解码并生成图片  
	        if (imgStr == null) //图像数据为空  
	            return false;  
	        BASE64Decoder decoder = new BASE64Decoder();  
	        try   
	        {  
	            //Base64解码  
	            byte[] b = decoder.decodeBuffer(imgStr);  
	            for(int i=0;i<b.length;++i)  
	            {  
	                if(b[i]<0)  
	                {//调整异常数据  
	                    b[i]+=256;  
	                }  
	            }  
	            OutputStream out = new FileOutputStream(imgFilePath);      
	            out.write(b);  
	            out.flush();  
	            out.close();  
	            return true;  
	        }   
	        catch (Exception e)   
	        {  
	        	e.printStackTrace();
	            return false;  
	        }  
	    }  
}
