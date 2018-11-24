package com.jeecms.bbs.action.front;

import static com.jeecms.bbs.Constants.TPLDIR_POST;
import static com.jeecms.bbs.Constants.TPLDIR_TOPIC;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jeecms.bbs.cache.TopicCountEhCache;
import com.jeecms.bbs.entity.BbsForum;
import com.jeecms.bbs.entity.BbsGrade;
import com.jeecms.bbs.entity.BbsPost;
import com.jeecms.bbs.entity.BbsPostType;
import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.BbsUserGroup;
import com.jeecms.bbs.manager.BbsConfigMng;
import com.jeecms.bbs.manager.BbsGradeMng;
import com.jeecms.bbs.manager.BbsPostMng;
import com.jeecms.bbs.manager.BbsPostTypeMng;
import com.jeecms.bbs.manager.BbsTopicMng;
import com.jeecms.bbs.manager.BbsUserGroupMng;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.bbs.web.WebErrors;
import com.jeecms.common.util.SensitiveWordUtil;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.springmvc.MessageResolver;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.core.web.MagicMessage;
import com.jeecms.point.entity.PointCalculateResult;
import com.jeecms.point.manager.point.PointCalculateMng;

@Controller
public class BbsPostAct {
	private static final Logger log = LoggerFactory.getLogger(BbsPostAct.class);

	public static final String TPL_POSTADD = "tpl.postadd";
	public static final String TPL_POSTEDIT = "tpl.postedit";
	public static final String TPL_NO_LOGIN = "tpl.nologin";
	public static final String TPL_NO_URL = "tpl.nourl";
	public static final String TPL_POST_QUOTE = "tpl.postquote";
	public static final String TPL_GUANSHUI = "tpl.guanshui";
	public static final String TPL_POST_GRADE = "tpl.postgrade";
	public static final String TPL_NO_VIEW = "tpl.noview";
	public static final String TPL_NO_POSTTYPE = "tpl.noposttype";

	@RequestMapping("/post/v_add{topicId}.jspx")
	public String add(@PathVariable Integer topicId, Integer tid,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		if (topicId == null && tid == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_POST, TPL_NO_URL);
		}
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		BbsTopic topic = null;
		if (topicId != null) {
			model.put("topicId", topicId);
			topic = bbsTopicMng.findById(topicId);
		} else if (tid != null) {
			model.put("topicId", tid);
			topic = bbsTopicMng.findById(tid);
		}
		String msg=null;
		//主题是否关闭
		if(!topic.getAllayReply()){
			MagicMessage magicMessage=MagicMessage.create(request);
			 msg=magicMessage.getMessage("magic.open.error");
		}else{
			//检查用户权限
			 msg = checkReply(request,topic.getForum(), user, site);
		}
		if (msg != null) {
			model.put("msg", msg);
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_VIEW);
		}
		if(topic!=null&&topic.getPostType()!=null){
			model.put("postTypeId", topic.getPostType().getId());
		}
		Boolean canAddPostPoint=pointCalculateMng.replyTopicLimit(user.getId()).isResult();
		model.put("canAddPostPoint",canAddPostPoint);
		
		model.put("canAddPostPoint",false);//回贴是否加积分
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_POST, TPL_POSTADD);
	}

	@RequestMapping("/post/v_edit{id}.jspx")
	public String edit(@PathVariable Integer id, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		if (id != null) {
			String msg = checkEdit(request,manager.findById(id).getTopic().getForum(),
					manager.findById(id), user, site);
			if (msg != null) {
				model.put("msg", msg);
				return FrontUtils.getTplPath(request, site.getSolutionPath(),
						TPLDIR_TOPIC, TPL_NO_VIEW);
			}
			model.addAttribute("post", manager.findById(id));
		} else {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_POST, TPL_NO_URL);
		}
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_POST, TPL_POSTEDIT);
	}

	@RequestMapping("/post/o_save.jspx")
	public String save(BbsPost bean,Integer replyToPostId, Integer topicId, Integer postTypeId,String title,
			String content,
			@RequestParam(value = "code", required = false) List<String> code,
			HttpServletRequest request, HttpServletResponse response, ModelMap model,Integer channel) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		
		BbsTopic topic=bbsTopicMng.findById(topicId);
		if(topic.getIsForbidReply()==true){
			model.put("msg", "该主题已禁止回贴");
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_VIEW);
		}
		
		String msg = checkReply(request,topic.getForum(), user,
				site);
		if (msg != null) {
			model.put("msg", msg);
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_VIEW);
		}
		boolean flag = topicCountEhCache.getLastReply(user.getId(), user
				.getGroup().getPostInterval());
		if (!flag) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_GUANSHUI);
		}
		if(postTypeId==null){
			//postTypeId= bean.getTopic().getPostType().getId();
		}
		if(postTypeId==null){
			postTypeId= ((BbsPostType)postTypeMng.getList(site.getId(), null, null).get(0)).getId();
		}
		if(postTypeId==null){
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_POSTTYPE);
		}
		title=filterUserInputContent(title);
		//content=filterUserInputContent(content);
		if(StringUtils.isBlank(title)){
//			WebErrors errors=WebErrors.create(request);
//			errors.addErrorCode("operate.faile");
//			return FrontUtils.showError(request, response, model, errors);
		}
		title=SensitiveWordUtil.replaceSensitiveWord(title);
		content=SensitiveWordUtil.replaceSensitiveWord(content);
		
		String ip = RequestUtils.getIpAddr(request);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		bean = manager.reply(replyToPostId,user.getId(), site.getId(), topicId,postTypeId, title,
				content, ip, multipartRequest.getFiles("attachment"), code);
		log.info("save BbsPost id={}", bean.getId());
		bbsUserGroupMng.findNearByPoint(user.getPoint(), user);
		PointCalculateResult result=null;
		
		if(channel==null){
			
			result=pointCalculateMng.replyTopic(user.getId());
		}else{
			result=pointCalculateMng.replyTopic(user.getId(),channel);
		}
		Long point=result.getNum();
		if(result.isResult()==false){
			point=0L;
		}
		model.put("point",point==null?0L:point);//发贴是否加积分
		model.put("pointType","p");
		return "redirect:" + bean.getRedirectUrl();
	}

	@RequestMapping("/post/o_update.jspx")
	public String update(BbsPost bean, Integer postId, String title,
			Boolean visibleAfterReply,String content, Integer pageNo, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "code", required = false) List<String> code,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		BbsTopic topic=manager.findById(postId).getTopic();
		if(topic.getIsForbidReply()==true){
			model.put("msg", "该主题已禁止回贴");
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_VIEW);
		}
		
		String msg = checkEdit(request,topic.getForum(),
				manager.findById(postId), user, site);
		if (msg != null) {
			model.put("msg", msg);
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_VIEW);
		}
		String ip = RequestUtils.getIpAddr(request);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		title=filterUserInputContent(title);
		//content=filterUserInputContent(content);
		if(StringUtils.isBlank(title)){
			WebErrors errors=WebErrors.create(request);
			errors.addErrorCode("operate.faile");
			return FrontUtils.showError(request, response, model, errors);
		}
		title=SensitiveWordUtil.replaceSensitiveWord(title);
		content=SensitiveWordUtil.replaceSensitiveWord(content);
		bean = manager.updatePost(postId, title,visibleAfterReply, content, user, ip,multipartRequest.getFiles("attachment"), code);
		log.info("update BbsPost id={}.", bean.getId());
		return "redirect:" + bean.getRedirectUrl();
	}

	@RequestMapping("/post/v_quote{postId}.jspx")
	public String quote(@PathVariable Integer postId, Integer pid,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		BbsPost post = null;
		if (postId != null) {
			post = manager.findById(postId);
		} else if (pid != null) {
			post = manager.findById(pid);
		}
		model.put("topicId", post.getTopic().getId());
		if (post != null) {
			String msg = checkReply(request,post.getTopic().getForum(), user, site);
			if (msg != null) {
				model.put("msg", msg);
				return FrontUtils.getTplPath(request, site.getSolutionPath(),
						TPLDIR_TOPIC, TPL_NO_VIEW);
			}
			model.put("post", post);
			model.put("topicId", post.getTopic().getId());
			model.put("postTypeId", post.getPostType().getId());
		}
		model.put("otype", 1);
		Boolean canAddPostPoint=pointCalculateMng.replyTopicLimit(user.getId()).isResult();
		model.put("canAddPostPoint",canAddPostPoint);//回贴是否加积分*/
		
		model.put("canAddPostPoint",false);//回贴是否加积分
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_POST, TPL_POSTADD);
	}

	@RequestMapping("/post/v_reply{postId}.jspx")
	public String reply(@PathVariable Integer postId, Integer pid,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		BbsPost post = null;
		if (postId != null) {
			post = manager.findById(postId);
		} else if (pid != null) {
			post = manager.findById(pid);
			model.put("postTypeId", post.getPostType().getId());
		}
		if (post != null) {
			String msg = checkReply(request,post.getTopic().getForum(), user, site);
			if (msg != null) {
				model.put("msg", msg);
				return FrontUtils.getTplPath(request, site.getSolutionPath(),
						TPLDIR_TOPIC, TPL_NO_VIEW);
			}
			model.put("post", post);
			model.put("topicId", post.getTopic().getId());
			model.put("postTypeId", post.getPostType().getId());
		}
		model.put("otype", 2);
		Boolean canAddPostPoint=pointCalculateMng.replyTopicLimit(user.getId()).isResult();
		model.put("canAddPostPoint",canAddPostPoint);//回贴是否加积分*/
		
		model.put("canAddPostPoint",false);//回贴是否加积分
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_POST, TPL_POSTADD);
	}

	@RequestMapping("/post/v_grade{postId}.jspx")
	public String grade(@PathVariable Integer postId, Integer pid,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		BbsPost post = null;
		if (postId != null) {
			post = manager.findById(postId);
		} else if (pid != null) {
			post = manager.findById(pid);
		}
		if (post != null) {
			String msg = checkGrade(request,post.getTopic().getForum(), user, site);
			if (msg != null) {
				model.put("msg", msg);
				return FrontUtils.getTplPath(request, site.getSolutionPath(),
						TPLDIR_TOPIC, TPL_NO_VIEW);
			}
			model.put("post", post);
		}
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_POST, TPL_POST_GRADE);
	}

	@RequestMapping("/post/o_grade.jspx")
	public String gradeSubmit(BbsGrade bean, Integer postId,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		if (postId != null) {
			BbsPost post = manager.findById(postId);
			String msg = checkGrade(request,post.getTopic().getForum(), user, site);
			if (msg != null) {
				model.put("msg", msg);
				return FrontUtils.getTplPath(request, site.getSolutionPath(),
						TPLDIR_TOPIC, TPL_NO_VIEW);
			}
			bbsGradeMng.saveGrade(bean, user, post);
			return "redirect:" + post.getRedirectUrl();
		}
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_POST, TPL_POSTADD);
	}

	@RequestMapping("/post/v_shield{postId}_{status}.jspx")
	public String shield(@PathVariable Integer postId, Integer pid,@PathVariable Short status,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		BbsPost post = null;
		if (postId != null) {
			String msg = checkShield(request,manager.findById(postId).getTopic()
					.getForum(), user, site);
			if (msg != null) {
				model.put("msg", msg);
				return FrontUtils.getTplPath(request, site.getSolutionPath(),
						TPLDIR_TOPIC, TPL_NO_VIEW);
			}
			post = manager.shield(postId, null, user,status);
		} else if (pid != null) {
			String msg = checkShield(request,manager.findById(pid).getTopic()
					.getForum(), user, site);
			if (msg != null) {
				model.put("msg", msg);
				return FrontUtils.getTplPath(request, site.getSolutionPath(),
						TPLDIR_TOPIC, TPL_NO_VIEW);
			}
			post = manager.shield(pid, null, user,status);
		}
		return "redirect:" + post.getRedirectUrl();
	}

	@RequestMapping("/post/o_shield.jspx")
	public String shieldSubmit(Integer postId, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		BbsPost post = null;
		if (postId != null) {
			post = manager.findById(postId);
		}
		if (post != null) {
			String msg = checkShield(request,post.getTopic().getForum(), user, site);
			if (msg != null) {
				model.put("msg", msg);
				return FrontUtils.getTplPath(request, site.getSolutionPath(),
						TPLDIR_TOPIC, TPL_NO_VIEW);
			}
			model.put("post", post);
			model.put("topicId", post.getTopic().getId());
			model.put("postTypeId", post.getPostType().getId());
		}
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_POST, TPL_POSTADD);
	}

	@RequestMapping("/member/o_prohibit.jspx")
	public String prohibit(Integer postId, Integer userId,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		if (!user.getModerator()) {
			String msg=MessageResolver.getMessage(request, "login.groupAccessForbidden",user.getGroup().getName());
			model.put("msg", msg);
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_VIEW);
		}
		BbsUser bbsuser = bbsUserMng.findById(userId);
		BbsPost post = manager.findById(postId);
		bbsuser.setProhibitPost(BbsUser.PROHIBIT_FOREVER);
		return "redirect:" + post.getRedirectUrl();
	}

	@RequestMapping("/post/o_delete.jspx")
	public String delete(Integer[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		BbsPost[] beans = manager.deleteByIds(ids);
		for (BbsPost bean : beans) {
			log.info("delete BbsPost id={}", bean.getId());
		}
		return null;
	}

	// public String checkIp(String ip){
	//		
	// return "";
	// }

	private String checkReply(HttpServletRequest request,BbsForum forum, BbsUser user, CmsSite site) {
		String msg="";
		if (forum.getGroupReplies() == null) {
			msg=MessageResolver.getMessage(request, "login.groupAccessForbidden",user.getGroup().getName());
			return msg;
		} else {
			BbsUserGroup group = null;
			if (user == null) {
				group = bbsConfigMng.findById(site.getId()).getDefaultGroup();
			} else {
				if (user.getProhibit()) {
					msg=MessageResolver.getMessage(request, "member.gag");
					return msg;
				}
				group = user.getGroup();
			}
			if (group == null) {
				msg=MessageResolver.getMessage(request, "member.hasnopermission");
				return msg;
			}
			if (!group.allowReply()) {
				msg=MessageResolver.getMessage(request, "member.hasnopermission");
				return msg;
			}
			if (forum.getGroupReplies().indexOf("," + group.getId() + ",") == -1) {
				msg=MessageResolver.getMessage(request, "member.hasnopermission");
				return msg;
			}
			if (!group.checkPostToday(user.getPostToday())) {
				msg=MessageResolver.getMessage(request, "member.posttomany");
				return msg;
			}
		}
		return null;
	}

	private String checkEdit(HttpServletRequest request,BbsForum forum, BbsPost post, BbsUser user,
			CmsSite site) {
		String msg="";
		if (forum.getGroupReplies() == null) {
			msg=MessageResolver.getMessage(request, "member.hasnopermission");
			return msg;
		} else {
			if (user == null) {
				msg=MessageResolver.getMessage(request, "member.hasnopermission");
				return msg;
			}
			BbsUserGroup group = user.getGroup();
			//if (!post.getCreater().equals(user)) {
			//	return "不能编辑别人的帖子";
			//}
			if (forum.getGroupReplies().indexOf("," + group.getId() + ",") == -1) {
				msg=MessageResolver.getMessage(request, "member.hasnopermission");
				return msg;
			}
		}
		return null;
	}

	private String checkGrade(HttpServletRequest request,BbsForum forum, BbsUser user, CmsSite site) {
		String msg="";
		if (forum.getGroupReplies() == null) {
			msg=MessageResolver.getMessage(request, "member.hasnopermission");
			return msg;
		} else {
			BbsUserGroup group = null;
			if (user == null) {
				group = bbsConfigMng.findById(site.getId()).getDefaultGroup();
			} else {
				group = user.getGroup();
			}
			if (group == null) {
				msg=MessageResolver.getMessage(request, "member.hasnopermission");
				return msg;
			}
			if (forum.getGroupReplies().indexOf("," + group.getId() + ",") == -1) {
				msg=MessageResolver.getMessage(request, "member.hasnopermission");
				return msg;
			}
			if (user.getGradeToday() != null
					&& user.getGradeToday() >= group.getGradeNum()) {
				msg=MessageResolver.getMessage(request, "member.doesnomark");
				return msg;
			}
		}
		return null;
	}

	private String checkShield(HttpServletRequest request, BbsForum forum, BbsUser user, CmsSite site) {
		String msg;
		if (forum.getGroupTopics() == null) {
			msg=MessageResolver.getMessage(request, "member.hasnopermission");
			return msg;
		} else {
			BbsUserGroup group = null;
			if (user == null) {
				group = bbsConfigMng.findById(site.getId()).getDefaultGroup();
			} else {
				group = user.getGroup();
			}
			if (group == null) {
				msg=MessageResolver.getMessage(request, "member.hasnopermission");
				return msg;
			}
			if (!group.allowTopic()) {
				msg=MessageResolver.getMessage(request, "member.hasnopermission");
				return msg;
			}
			if (forum.getGroupTopics().indexOf("," + group.getId() + ",") == -1) {
				msg=MessageResolver.getMessage(request, "member.hasnopermission");
				return msg;
			}
			if (!group.hasRight(forum, user)) {
				msg=MessageResolver.getMessage(request, "member.hasnopermission");
				return msg;
			}
			if (!group.topicShield()) {
				msg=MessageResolver.getMessage(request, "member.hasnopermission");
				return msg;
			}
		}
		return null;
	}
	
	private final static Whitelist user_content_filter = Whitelist.relaxed();
	static {
		user_content_filter.addTags("embed","object","param","span","div");
		user_content_filter.addAttributes(":all", "style", "class", "id", "name");
		user_content_filter.addAttributes("object", "width", "height","classid","codebase");	
		user_content_filter.addAttributes("param", "name", "value");
		user_content_filter.addAttributes("embed", "src","quality","width","height","allowFullScreen","allowScriptAccess","flashvars","name","type","pluginspage");
	}

	/**
	 * 对用户输入内容进行过滤
	 * @param html
	 * @return
	 */
	public static String filterUserInputContent(String html) {
		if(StringUtils.isBlank(html)) return "";
		return Jsoup.clean(html, user_content_filter);
	}
	
	
	@RequestMapping("/post/share.jspx")
	public String share(HttpServletRequest request, ModelMap model,String bdText,String bdDesc,String bdUrl) {
		CmsSite site = CmsUtils.getSite(request);
		bdUrl=bdUrl.replaceAll("：",":"); // 符号":"作为参数值提交时会被转成 "：" 来防止与http:中的:搞乱，所以我们在此处还原回来,此处不能用request.getHeader("Referer")来得到bdUrl ，因为当前会从https跳转到http协议
		model.put("bdText", bdText);
		model.put("bdDesc", bdDesc);
     	model.put("bdUrl",bdUrl);
		FrontUtils.frontData(request, model, site);
		return "/WEB-INF/t/cms/www/blue/post/post_share.html";
	}

	@Autowired
	private BbsPostMng manager;
	@Autowired
	private BbsTopicMng bbsTopicMng;
	@Autowired
	private BbsGradeMng bbsGradeMng;
	@Autowired
	private BbsUserMng bbsUserMng;
	@Autowired
	private BbsConfigMng bbsConfigMng;
	@Autowired
	private TopicCountEhCache topicCountEhCache;
	@Autowired
	private BbsUserGroupMng bbsUserGroupMng;
	@Autowired
	private BbsPostTypeMng postTypeMng;
	
	@Autowired
	private PointCalculateMng pointCalculateMng;
}
