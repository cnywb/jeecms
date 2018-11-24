package com.jeecms.cms.action.infocollection;

import java.io.File;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jeecms.cms.entity.infocollection.PotentialCustomerInfoRequest;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.manager.infocollection.PotentialCustomerInfoMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.util.Base64Utils;
import com.jeecms.common.util.DateUtils;
import com.jeecms.common.util.PropUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.wechat.util.AudioUtil;
import com.jeecms.wechat.util.MediaUtil;



@Controller@Scope("prototype")
@RequestMapping("/infocollection/potentialcustomer/*")
public class PotentialCustomerInfoAct {
	
	
	@Autowired
	private PotentialCustomerInfoMng potentialCustomerInfoMng;
	

	@RequestMapping(value="index.jspx")
	public String index(HttpServletRequest request,String channel,ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		model.put("channel", channel);
		return "/WEB-INF/t/cms/www/mobile/infocollection/indext.html";
	}
	
	@RequestMapping(value="{path}/index.jspx")
	public String index(HttpServletRequest request,@PathVariable String path,String channel,ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		model.put("channel", channel);
		
		return "/WEB-INF/t/cms/www/mobile/"+path+"/indext.html";
	}
	@RequestMapping(value="potentialCustomerInfo.jspx")
    public String  potentialCustomerInfo(HttpServletRequest request,String channel,ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		model.put("channel", channel);
		return "/WEB-INF/t/cms/www/mobile/infocollection/potential_customer_info.html";
	}
	
	@RequestMapping(value="{path}/potentialCustomerInfo.jspx")
    public String  potentialCustomerInfo(HttpServletRequest request,@PathVariable String path,String channel,ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		model.put("channel", channel);
		return "/WEB-INF/t/cms/www/mobile/"+path+"/potential_customer_info.html";
	}

	@RequestMapping(value="add.jspx")
	public void add(HttpServletResponse response,PotentialCustomerInfoRequest potentialCustomerInfoRequest,HttpServletRequest request,ModelMap model){
		String json=potentialCustomerInfoMng.add(potentialCustomerInfoRequest).toString();
		ResponseUtils.renderJson(response,json);
	}
	
	@RequestMapping(value="{path}/add.jspx")
	public void add(HttpServletResponse response,@PathVariable String path,PotentialCustomerInfoRequest potentialCustomerInfoRequest,HttpServletRequest request,ModelMap model){
		String json=potentialCustomerInfoMng.addForCheckCustomerInfo(potentialCustomerInfoRequest).toString();
		ResponseUtils.renderJson(response,json);
	}
	
	@RequestMapping(value="{path}/saveBase64Image.jspx")
	public void saveBase64Image(HttpServletResponse response,@PathVariable String path,String base64data,HttpServletRequest request,ModelMap model){
		String savePath = request.getSession().getServletContext().getRealPath("/") + PropUtils.getPropertyValue("editor.properties","attached_save_path")+"/image/wechat/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		String fileFullName =DateUtils.format(new Date(), DateUtils.FORMAT_DATE_TIME_YYYYMMDDHHMMSS)+"_"+ new Random().nextInt(1000)+".jpg";
		base64data=base64data.replaceAll(" ","+");//由于系统自动将+号替换成了空格，所以得还原。
		Base64Utils.base64toImage(base64data,savePath+fileFullName);
		ResponseUtils.renderJson(response,fileFullName);
	}
	
	@RequestMapping(value="{path}/getMp3AudioUrl.jspx")
	public void getMp3AudioUrl(HttpServletResponse response,@PathVariable String path,String mediaId,HttpServletRequest request,ModelMap model){
		String savePath = request.getSession().getServletContext().getRealPath("/") + PropUtils.getPropertyValue("editor.properties","attached_save_path")+"/media/wechat/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		String fileName=MediaUtil.downloadMedia(savePath, mediaId);
		String mp3FileName=fileName.replaceAll("amr","mp3");
		AudioUtil.changeToMp3(fileName,mp3FileName);
		mp3FileName="/media/wechat/"+mp3FileName.substring(mp3FileName.lastIndexOf("/")+1,mp3FileName.length());
		ResponseUtils.renderJson(response,mp3FileName);
	}
	@RequestMapping(value="{path}/convertAudioToMp3.jspx")
	public void convertAudioToMp3(HttpServletResponse response,@PathVariable String path,String mediaId,HttpServletRequest request,ModelMap model){
		String savePath = request.getSession().getServletContext().getRealPath("/") + PropUtils.getPropertyValue("editor.properties","attached_save_path")+"/media/wechat/";
		String fileName=savePath+mediaId+".amr";
		String mp3FileName=fileName.replaceAll("amr","mp3");
		AudioUtil.changeToMp3(fileName,mp3FileName);
		mp3FileName="/media/wechat/"+mp3FileName.substring(mp3FileName.lastIndexOf("/")+1,mp3FileName.length());
		ResponseUtils.renderJson(response,mp3FileName);
	}
	
	@RequestMapping(value="{path}/getImageUrl.jspx")
	public void getImageUrl(HttpServletResponse response,@PathVariable String path,String mediaId,HttpServletRequest request,ModelMap model){
		String saveRelativePath="/image/wechat/"+(path==null?"":path+"/");
		String saveRealPath = request.getSession().getServletContext().getRealPath("/") + PropUtils.getPropertyValue("editor.properties","attached_save_path")+saveRelativePath;
		File saveDirFile = new File(saveRealPath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		String fileName=MediaUtil.downloadMedia(saveRealPath, mediaId);
		fileName=saveRelativePath+fileName.substring(fileName.lastIndexOf("/")+1,fileName.length());
		ResponseUtils.renderJson(response,fileName);
	}

}
