package com.jeecms.bbs.action.member;

import static com.jeecms.bbs.Constants.TPLDIR_TOPIC;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeecms.bbs.action.member.vo.CarFocusType;
import com.jeecms.bbs.action.member.vo.UserCarVo;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.manager.member.BbsMemberCarFocusMng;
import com.jeecms.bbs.manager.member.BbsMemberCarMng;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.common.web.RequestUtils;

@Controller
public class MemberCarAct {
	private  final Logger  logger= LoggerFactory.getLogger(super.getClass());
	
	
	public static final String TPL_NO_LOGIN = "tpl.nologin";
	
	private static final String  CAR_FOCUS_RESULT="/WEB-INF/t/cms/www/blue/member/car_focus.html";
	
	private static final String  CAR_MY_RESULT="/WEB-INF/t/cms/www/blue/member/car_my.html";
	
	private static final String  CAR_FRIEND_RESULT="/WEB-INF/t/cms/www/blue/member/car_friend.html";
	
	private static final String  CAR_FOCUS_INIT="redirect:car_focus.jhtml";
	
	private static final String  CAR_MY_REDIRECT="redirect:car_my.jhtml";
	
	private static final String  CAR_FRIEND_REDIRECT="redirect:car_friend.jhtml";
	
	private static final String  CAR_UPLOAD_PATH="/user/car/";
	
	private static final Integer  IMAGE_WIDTH=385;
	
	private static final Integer  IMAGE_HEIGHT=264;
	
	private static final Integer PAGE_SIZE=15;
	
	@Autowired
	private BbsMemberCarMng bbsMemberCarMng;
	
	@Autowired
	private BbsMemberCarFocusMng bbsMemberCarFocusMng;
	
	
	 @RequestMapping("/member/car/car_focus.jhtml") 
	 public String carFocus(HttpServletRequest request,ModelMap model) {
		logger.info("carFocus  in ..."); 
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		List<CarFocusType> listUser=bbsMemberCarFocusMng.queryByUserId(user.getId());
		
		model.put("userfocusCarsType",listUser);
		
		model.put("focusCarTypes", bbsMemberCarFocusMng.queryFocusFullCarType(listUser));
		
		model.addAttribute("kw", RequestUtils.getQueryParam(request, "kw"));
		model.addAttribute("user", user);
		FrontUtils.frontData(request, model, site);
		logger.info("carFocus out ...");
		return  CAR_FOCUS_RESULT;
	}
	 
	 
	 @RequestMapping("/member/car/carfriend*.jhtml")
	 public String myFriend(@RequestParam(defaultValue="1",required=false) int  pageNo,HttpServletRequest request,ModelMap model){
			logger.info("myFriend  in ..."); 
			CmsSite site = CmsUtils.getSite(request);
			BbsUser user = CmsUtils.getUser(request);
			if (user == null) {
				return FrontUtils.getTplPath(request, site.getSolutionPath(),TPLDIR_TOPIC, TPL_NO_LOGIN);
			}
			
			model.addAttribute("kw", RequestUtils.getQueryParam(request, "kw"));
			model.addAttribute("user", user);
			
			if(pageNo==0){
				pageNo=1;
			}
			//Pagination  pagination=this.bbsMemberCarMng.queryFriendCarByUserId(user.getId(), pageNo, PAGE_SIZE);
			
			//model.put("carPagination", pagination);
			
			model.put("type","");
			model.put("ty","");
			FrontUtils.frontData(request, model, site);
			FrontUtils.frontPageData(request, model);
			logger.info("myFriend out ...");
			return  CAR_FRIEND_RESULT;
	 }
	 
	@RequestMapping("/member/car/car_my.jhtml")
	public String myCar(HttpServletRequest request,ModelMap model){
		logger.info("myCar  in ..."); 
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		
		List<UserCarVo>   userCarVos=bbsMemberCarMng.queryUserCarBy(user.getId());
		model.put("userCarVos",userCarVos);
		model.put("userCarVosSize",userCarVos.size());
		model.put("image_width",IMAGE_WIDTH);
		model.put("image_heigth",IMAGE_HEIGHT);
		
		//logger.info("filePath:{}",this.getUploadPath(request));
		model.addAttribute("kw", RequestUtils.getQueryParam(request, "kw"));
		model.addAttribute("user", user);
		FrontUtils.frontData(request, model, site);
		logger.info("myCar out ...");
		return  CAR_MY_RESULT;
	}
	
	
	@RequestMapping("/member/car/car_my_uploadimage.jhtml")
	public String  upploadMyCarImage(Long imageId,String carId,String productCode,String uploadImgPath,String purchasedDate,HttpServletRequest request,ModelMap model){
		logger.info("upploadMyCarImage  in ..."); 
		//logger.info("imageId:{},carId:{}",imageId,carId);
		//logger.info("productCode:{},uploadImgPath:{}",productCode,uploadImgPath);
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),TPLDIR_TOPIC, TPL_NO_LOGIN);
		} 
		model.addAttribute("kw", RequestUtils.getQueryParam(request, "kw"));
		model.addAttribute("user", user);
		FrontUtils.frontData(request, model, site);
		
		
		bbsMemberCarMng.saveImage(imageId, user.getId(), carId, productCode, uploadImgPath,purchasedDate);
		logger.info("upploadMyCarImage  out ..."); 
		 return CAR_MY_REDIRECT;
	}
	
	@RequestMapping("/member/car/car_my_deleteimage.jhtml")
	public String  deleteMyCarImage(Long imageId,HttpServletRequest request,ModelMap model){
		logger.info("deleteMyCarImage  in ..."); 
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),TPLDIR_TOPIC, TPL_NO_LOGIN);
		} 
  
		model.addAttribute("kw", RequestUtils.getQueryParam(request, "kw"));
		model.addAttribute("user", user);
		FrontUtils.frontData(request, model, site);
		
		String imagePath=bbsMemberCarMng.deleteImageById(imageId, user.getId());
		StringBuffer  filePath=new StringBuffer(request.getSession().getServletContext().getRealPath("/"));
		//删除文件
		this.deleteImageFile(filePath+imagePath);
		
		logger.info("deleteMyCarImage  out ..."); 
		 return CAR_MY_REDIRECT;
	}
	
	private void deleteImageFile(String imagePath){
		if(StringUtils.isNotEmpty(imagePath)){
			File  imageFile=new File(imagePath);
			if(imageFile.exists()){
				imageFile.delete();
			}
		}
	}
	
	
	@SuppressWarnings("unused")
	private String getUploadPath(HttpServletRequest request){
		StringBuffer  filePath=new StringBuffer(request.getSession().getServletContext().getRealPath("/"));
		filePath.append(CAR_UPLOAD_PATH);
		String uploadDate=DateUtils.parseDate(new Date(), "yyyy/MM/dd");
		filePath.append(uploadDate);
		filePath.append("/");
		File uploadFile=new File(filePath.toString());
		if(!uploadFile.exists()){
			uploadFile.mkdirs();
		}
		return filePath.toString();
	}
	 
	 @RequestMapping("/member/car/car_focus_delete.jhtml")
	 public String carFocusDelete(Long id,HttpServletRequest request,ModelMap model){
		 logger.info("carFocusDelete  in ..."); 
		 
		 CmsSite site = CmsUtils.getSite(request);
		 
		 BbsUser user = CmsUtils.getUser(request);
		 
		 if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		 }
		 
		 if(id>0){
			 	this.bbsMemberCarFocusMng.deleteById(id);
		 }
		 
		 
		 model.addAttribute("kw", RequestUtils.getQueryParam(request, "kw"));
		 model.addAttribute("user", user);
		 FrontUtils.frontData(request, model, site);
		  logger.info("carFocusDelete  out...");
		 return  CAR_FOCUS_INIT;
	 }
	 
	
	 @RequestMapping("/member/car/car_focus_submit.jhtml") 
	 public String carFocusSubmit(String[] chkCarTypes,HttpServletRequest request,ModelMap model) {
		 
		 logger.info("carFocusSubmit  in ..."); 		  
		 CmsSite site = CmsUtils.getSite(request);		 
		 BbsUser user = CmsUtils.getUser(request);
		 if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		 }
		 bbsMemberCarFocusMng.save(chkCarTypes, user.getId());
		 model.addAttribute("kw", RequestUtils.getQueryParam(request, "kw"));
		 model.addAttribute("user", user);
		 FrontUtils.frontData(request, model, site);
		 logger.info("carFocusSubmit  out...");
		 return  CAR_FOCUS_INIT;
	 }
}
