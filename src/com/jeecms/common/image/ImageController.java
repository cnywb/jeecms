package com.jeecms.common.image;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.common.util.RemoteImageScaleUtil;


@Controller@Scope("prototype")
@RequestMapping("/common/image/*")
public class ImageController {

	
	@RequestMapping("scaling.htm")
	public void scaling(HttpServletResponse response,HttpServletRequest request){
		try{
			ServletOutputStream out = response.getOutputStream();
			byte[] image=RemoteImageScaleUtil.imageScaling(response,request);
			response.setContentLength(image.length);
	        out.write(image);
	        out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
