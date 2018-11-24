package com.jeecms.cms.action.member;

import static com.jeecms.cms.Constants.TPLDIR_MEMBER;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.bbs.manager.BbsFriendShipMng;
import com.jeecms.bbs.manager.BbsMessageMng;
import com.jeecms.bbs.manager.BbsPostMng;
import com.jeecms.bbs.manager.BbsTopicDraftMng;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.CmsUserExt;
import com.jeecms.cms.entity.main.MemberConfig;
import com.jeecms.cms.manager.assist.CmsGuestbookMng;
import com.jeecms.cms.manager.main.CmsUserExtMng;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.cms.manager.main.FordRepairWebMng;
import com.jeecms.cms.vo.CardScoreInfoVo;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.cms.web.WebErrors;
import com.jeecms.common.util.ValidateUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.point.manager.point.PointCalculateMng;

/**
 * 会员中心Action
 */
@Controller
public class MemberAct {
	private static final Logger log = LoggerFactory.getLogger(MemberAct.class);

	public static final String MEMBER_CENTER = "tpl.memberCenter";
	public static final String MEMBER_PROFILE = "tpl.memberProfile";
	public static final String MEMBER_PORTRAIT = "tpl.memberPortrait";
	public static final String MEMBER_PASSWORD = "tpl.memberPassword";

	/**
	 * 会员中心页
	 * 
	 * 如果没有登录则跳转到登陆页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/index.jspx", method = RequestMethod.GET)
	public String index(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		//车主获取初始数据
		if(user.getGroup().getId().intValue()==2)
		{
			List<CardScoreInfoVo> list = repairMng.getCardScoreInfoVoBy(user.getId(),2,2);
			model.put("carInfo", list);
			if(list!=null)
				model.put("size", list.size());
		}
		model.put("user", user);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MEMBER_CENTER);
	}

	/**
	 * 个人资料输入页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/profile.jspx", method = RequestMethod.GET)
	public String profileInput(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MEMBER_PROFILE);
	}
	
	
	@RequestMapping(value = "/member/carInfoUpdate.jspx", method = RequestMethod.GET)
	public String carInfoUpdate(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		return "/WEB-INF/t/cms/www/red/member/car_info_update.html";
	}
	
	
	/**
	 * 更换头像
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/portrait.jspx", method = RequestMethod.GET)
	public String portrait(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MEMBER_PORTRAIT);
	}

	/**
	 * 个人资料提交页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/member/profile.jspx", method = RequestMethod.POST)
	public String profileSubmit(CmsUserExt ext, String nextUrl,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		ext.setId(user.getId());
		user.setEmail(ext.getEmail());
		cmsUserMng.updateUser(user);
		ext=cmsUserExtMng.update(ext, user);
		ext.setEmail(user.getEmail());
		log.info("update CmsUserExt success. id={}", user.getId());
		pointCalculateMng.perfactUserInfo(user.getId(), getProfileInfoCount(ext));
		return FrontUtils.showSuccess(request, model, nextUrl);
	}
	
	private int getProfileInfoCount(CmsUserExt ext){
		//车主基本信息+车辆基本信息 共计13项
		//车主基本信息
		int retVal=0;
		//车主基本信息－姓名 1 
		if(!StringUtils.isEmpty(ext.getRealname())){
			retVal=retVal+1;
		}
		//车主基本信息－性别2
		if(ext.getGender()!=null){
			retVal=retVal+1;
		}
		//车主基本信息－出生日期3
		if(ext.getBirthday()!=null){
			retVal=retVal+1;
		}
		//车主基本信息-驾驶证4
		if(!StringUtils.isEmpty(ext.getDrivingLicense())){
			retVal=retVal+1;
		}
		//车主基本信息-地址 5
		if(StringUtils.isNotEmpty(ext.getProvince())&&StringUtils.isNotEmpty(ext.getCity())&&StringUtils.isNotEmpty(ext.getCounty())&&StringUtils.isNotEmpty(ext.getComefrom())){
			retVal=retVal+1;
		}
		//车主基本信息-手机 6
		if(!StringUtils.isEmpty(ext.getMobile())){
			retVal=retVal+1;
		}
		//车主基本信息-地址 7
		if(!StringUtils.isEmpty(ext.getEmail())){
			retVal=retVal+1;
		}
		//车主基本信息-头像 8
		//if(!StringUtils.isEmpty(ext.getUserImg())){
		//	retVal=retVal+1;
		//}
		 
		//车辆基本信息 购买日期1
		if(ext.getCarBuyingDate()!=null){
			retVal=retVal+1;
		}
		//车辆基本信息 经销商地址 2
		if(StringUtils.isNotEmpty(ext.getBuyingDealerProvince())&&StringUtils.isNotEmpty(ext.getBuyingDealerCity())){
			retVal=retVal+1;
		}
		//车辆基本信息 维修经销商所在省 3
		if(StringUtils.isNotEmpty(ext.getRepairDealerProvince())&&StringUtils.isNotEmpty(ext.getRepairDealerCity())){
			retVal=retVal+1;
		}
		//车辆基本信息  车型 4
		if(StringUtils.isNotEmpty(ext.getCarModel())){
			retVal=retVal+1;
		}
		//车辆基本信息  车款 5
		if(StringUtils.isNotEmpty(ext.getCarStyle())){
			retVal=retVal+1;
		}
		//车辆基本信息  车色 6
		if(StringUtils.isNotEmpty(ext.getCarColor())){
			retVal=retVal+1;
		}
		return retVal;
	}

	@RequestMapping(value = "/member/updateCarInfo.jspx", method = RequestMethod.POST)
	public String updateCarInfo(CmsUserExt ext, String nextUrl,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		ext=cmsUserExtMng.updateCarInfo(ext, user);
		ext.setEmail(user.getEmail());
		pointCalculateMng.perfactUserInfo(user.getId(), getProfileInfoCount(ext));
		log.info("update CmsUserExt success. id={}", user.getId());
		return FrontUtils.showSuccess(request, model, nextUrl);
	}
	
	@RequestMapping(value = "/member/updateProfile.jspx", method = RequestMethod.POST)
	public void  updateProfile(CmsUserExt ext, String nextUrl,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		JSONObject object = new JSONObject();
		try {
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			object.put("status",0);
			ResponseUtils.renderJson(response, object.toString());
			return;
		}
		if (user == null) {
			object.put("status",1);
			ResponseUtils.renderJson(response, object.toString());
			return;
		}
		if(StringUtils.isEmpty(ext.getRealname())){
			object.put("status",2);
			ResponseUtils.renderJson(response, object.toString());
			return;
		}
		if(StringUtils.isEmpty(ext.getComefrom())){
			object.put("status",3);
			ResponseUtils.renderJson(response, object.toString());
			return;
		}
		if(!ValidateUtils.isMobileNO(ext.getMobile())){
			object.put("status",4);
			ResponseUtils.renderJson(response, object.toString());
			return;
		}
		ext.setId(user.getId());
		cmsUserExtMng.update(ext, user);
		object.put("status",5);
		ResponseUtils.renderJson(response, object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 密码修改输入页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/pwd.jspx", method = RequestMethod.GET)
	public String passwordInput(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MEMBER_PASSWORD);
	}

	/**
	 * 密码修改提交页
	 * 
	 * @param origPwd
	 *            原始密码
	 * @param newPwd
	 *            新密码
	 * @param email
	 *            邮箱
	 * @param nextUrl
	 *            下一个页面地址
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/member/pwd.jspx", method = RequestMethod.POST)
	public String passwordSubmit( String newPwd, String email,
			String nextUrl, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		WebErrors errors = validatePasswordSubmit(user.getId(), 
				newPwd, request);
		if (errors.hasErrors()) {
			return FrontUtils.showError(request, response, model, errors);
		}
		cmsUserMng.updatePwdEmail(user.getId(), newPwd, email);
		return FrontUtils.showSuccess(request, model, nextUrl);
	}

	/**
	 * 验证密码是否正确
	 * 
	 * @param origPwd
	 *            原密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/member/checkPwd.jspx")
	public void checkPwd(String origPwd, HttpServletRequest request,
			HttpServletResponse response) {
		CmsUser user = CmsUtils.getUser(request);
		boolean pass = cmsUserMng.isPasswordValid(user.getId(), origPwd);
		ResponseUtils.renderJson(response, pass ? "true" : "false");
	}

	private WebErrors validatePasswordSubmit(Integer id, 
			String newPwd, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);

		if (errors.ifMaxLength(newPwd, "newPwd", 100)) {
			return errors;
		}

		return errors;
	}

	
	@RequestMapping(value = "/member/messageCenter.jhtml")
	public String messageCenter(CmsUserExt ext, String nextUrl,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {
		CmsSite site = CmsUtils.getSite(request);
		
		FrontUtils.frontData(request, model, site);
		
		CmsUser user = CmsUtils.getUser(request);
		int userMessageCount=bbsMessageMng.getTotalCountByReceiverAndTypeIdAndStatus(user.getId(),1, false);
		int friendshipCount=bbsFriendShipMng.getTotalCountByUserIdAndStatus(user.getId(),0);
		int sysMessageCount=bbsMessageMng.getTotalCountByReceiverAndTypeIdAndStatus(user.getId(), 4, false);
		int guestbookCount=cmsGuestbookMng.getTotalCountByUserId(user.getId());
		int replyToMeCount=bbsPostMng.getReplyToMeTotalCountByUserId(user.getId());
		int topicDraftCount=bbsTopicDraftMng.getTotalCountByUserId(user.getId());
		model.addAttribute("topicDraftCount",topicDraftCount);
		model.addAttribute("replyToMeCount",replyToMeCount);
		model.addAttribute("guestbookCount",guestbookCount);
		model.addAttribute("sysMessageCount",sysMessageCount);
		model.addAttribute("friendshipCount",friendshipCount );
		model.addAttribute("userMessageCount",userMessageCount );
		return "/WEB-INF/t/cms/www/blue/member/message_center.html";
	}
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private CmsUserExtMng cmsUserExtMng;
	@Autowired
	private FordRepairWebMng repairMng;
	@Autowired
	private BbsFriendShipMng bbsFriendShipMng;
	@Autowired
	private BbsMessageMng bbsMessageMng;
	@Autowired
	private CmsGuestbookMng  cmsGuestbookMng;
	@Autowired
	private BbsPostMng bbsPostMng;
	@Autowired
	private BbsTopicDraftMng bbsTopicDraftMng;
	@Autowired
	private PointCalculateMng pointCalculateMng;
}
