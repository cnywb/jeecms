package com.jeecms.cms.action.media;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jeecms.cms.entity.assist.FileDownloadLog;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.manager.assist.FileDownloadLogMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.util.PropUtils;



@Controller@Scope("prototype")
@RequestMapping("/media/*")
public class FileDownloadAct {
	
	
	@Autowired
	private FileDownloadLogMng fileDownloadLogMng;
	
	@RequestMapping(value = "mapFileDownload.jspx")
	public String mapFileDownload(ModelMap model,HttpServletRequest request){
		CmsSite site = CmsUtils.getSite(request);
		//
		FrontUtils.frontData(request, model, site);
		/*CmsUser user = CmsUtils.getUser(request);
		  if(user==null){
			return FrontUtils.showLogin(request, model, site);
		}*/
		return "/WEB-INF/t/cms/www/red/media/map_file_download.html";
	}
	
	@RequestMapping(value = "downloadMapFile.jspx")
	public String downloadMapFile(Long  fileId,ModelMap model,HttpServletRequest request){
		CmsSite site = CmsUtils.getSite(request);
		FileDownloadLog t=new FileDownloadLog();
		t.setFileId(fileId);
		int retVal=fileDownloadLogMng.add(t, request);
		if(retVal==0){
			return FrontUtils.showLogin(request, model, site);
		}
		if(retVal==7){
			if(fileId.longValue()==1L){//地图升级包
				String url=PropUtils.getPropertyValue("dvd_file.properties","dvdfile_1");
				return "redirect:".concat(url);	
			}else if(fileId.longValue()==2L){//升级操作指南
				String url=PropUtils.getPropertyValue("dvd_file.properties","dvdfile_2");
				return "redirect:".concat(url);	
			}else if(fileId.longValue()==3L){//导航系统升级包
				String url=PropUtils.getPropertyValue("dvd_file.properties","dvdfile_3");
				return "redirect:".concat(url);	
	        }
		}
		FrontUtils.frontData(request, model, site);
		model.addAttribute("status",retVal);
		return "/WEB-INF/t/cms/www/red/media/map_file_download.html";
	}

	
	
	
}
