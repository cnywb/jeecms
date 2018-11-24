package com.jeecms.bbs.action.member;

import static com.jeecms.bbs.Constants.TPLDIR_TOPIC;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeecms.bbs.action.member.vo.ForumVo;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.manager.member.BbsMemberFavoriteMng;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.common.web.RequestUtils;

@Controller
public class MemberFavoriteAct {
	
	private  final Logger  logger= LoggerFactory.getLogger(super.getClass());
	
	private  final String  FAVORITE_TOPIC_RESULT="/WEB-INF/t/cms/www/blue/member/favorite_topic.html";
	
	private  final String  FAVORITE_BORAD_RESULT="/WEB-INF/t/cms/www/blue/member/favorite_borad.html";
	
	private  final String  FAVORITE_ADD_TEST_RESULT="/WEB-INF/t/cms/www/blue/member/favorite_add.html";
	
	
	private  final String  FAVORITE_BORAD_FOCUS_RESULT="redirect:board.jhtml";
	
	private  final String  FAVORITE_ADD_RESULT=FAVORITE_ADD_TEST_RESULT;

	public static final String TPL_NO_LOGIN = "tpl.nologin";
	
	@Autowired
	private BbsMemberFavoriteMng bbsMemberFavoriteMng;
	
	
	@RequestMapping("/member/favorite/topic*.jhtml") 
	public String showTopic(@RequestParam(defaultValue="1",required=false) int  pageNo,HttpServletRequest request,ModelMap model){
		
		logger.info("showTopic in ...");
		
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		model.put("type","");
		model.put("ty","");
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		logger.info("showTopic out ...");
		return FAVORITE_TOPIC_RESULT;
	}
	
	
	@RequestMapping("/member/favorite/board.jhtml") 
	public String showBoard(@RequestParam(defaultValue="1",required=false) int  pageNo,HttpServletRequest request,ModelMap model){
		
		logger.info("showBoard in ...");
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		site.getId();
		model.addAttribute("kw", RequestUtils.getQueryParam(request, "kw"));
		model.addAttribute("user", user);
		//List<ForumVo>  userForums= bbsMemberFavoriteMng.queryForumByUserId(user.getId(), site.getId());
		List<ForumVo>   list=bbsMemberFavoriteMng.queryAllForumByUserId(user.getId(), site.getId());
		model.put("allForums",list);
		 
		FrontUtils.frontData(request, model, site);
		logger.info("showBoard out ...");
		return FAVORITE_BORAD_RESULT;
	}
	
	@RequestMapping("/member/favorite/board_focus.jhtml") 
	public String focusBoard(String[] chkCarTypes ,HttpServletRequest request,ModelMap model){
		logger.info(" focusBoard in ...");
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		site.getId();
		model.addAttribute("kw", RequestUtils.getQueryParam(request, "kw"));
		model.addAttribute("user", user);
		FrontUtils.frontData(request, model, site);
		bbsMemberFavoriteMng.saveForums(chkCarTypes,user.getId());
		logger.info(" focusBoard out ...");
		return FAVORITE_BORAD_FOCUS_RESULT;
	}
	
	@RequestMapping("/member/favorite/board_focus_delete.jhtml") 
	public String focusBoardDelete(Long id,HttpServletRequest request,ModelMap model){
		logger.info(" focusBoardDelete in ...");
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		site.getId();
		model.addAttribute("kw", RequestUtils.getQueryParam(request, "kw"));
		model.addAttribute("user", user);
		FrontUtils.frontData(request, model, site); 
		bbsMemberFavoriteMng.deleteById(id);
		logger.info(" focusBoardDelete out ...");
		
		return FAVORITE_BORAD_FOCUS_RESULT;
	}
	
	/***
	 * '收藏类别

	 * @param id  版块ID或2主题ID
	 * @param type 1 版块 2 主题   3 帖子（回复？）
	 * @return
	 */
	@RequestMapping("/member/favorite/add.jhtml") 
	public  String addFavorite(Integer topicId,@RequestParam(defaultValue="2",required=false)Integer type,HttpServletRequest request,ModelMap model){
		logger.info(" addFavorite in ...");
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		//if (user == null) {
		//	return FrontUtils.getTplPath(request, site.getSolutionPath(),TPLDIR_TOPIC, TPL_NO_LOGIN);
		//}
		model.addAttribute("kw", RequestUtils.getQueryParam(request, "kw"));
		model.addAttribute("user", user);
		FrontUtils.frontData(request, model, site);
		this.bbsMemberFavoriteMng.saveTopic(topicId,user.getId(),type);
		logger.info(" addFavorite out ...");
		return FAVORITE_ADD_RESULT;
	}
	
	
	/***
	 * '收藏类别

	 * @param id  版块ID或2主题ID
	 * @param type 1 版块 2 主题   3 帖子（回复？）
	 * @return
	 */
	@RequestMapping("/member/favorite/addtest.jhtml") 
	public  String addFavoriteTest(HttpServletRequest request,ModelMap model){
		logger.info(" addFavorite in ...");
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		model.addAttribute("kw", RequestUtils.getQueryParam(request, "kw"));
		model.addAttribute("user", user);
		FrontUtils.frontData(request, model, site);
		 
		logger.info(" addFavorite out ...");
		return FAVORITE_ADD_TEST_RESULT;
	}
}
