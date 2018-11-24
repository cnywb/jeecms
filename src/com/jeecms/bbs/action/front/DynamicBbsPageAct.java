package com.jeecms.bbs.action.front;

import static com.jeecms.bbs.Constants.TPLDIR_FORUM;
import static com.jeecms.bbs.Constants.TPLDIR_TOPIC;
import static com.jeecms.common.web.Constants.INDEX;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.jeecms.bbs.cache.TopicCountEhCache;
import com.jeecms.bbs.entity.BbsForum;
import com.jeecms.bbs.entity.BbsPostType;
import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.BbsUserGroup;
import com.jeecms.bbs.manager.BbsConfigMng;
import com.jeecms.bbs.manager.BbsForumMng;
import com.jeecms.bbs.manager.BbsForumModeratorMng;
import com.jeecms.bbs.manager.BbsPostTypeMng;
import com.jeecms.bbs.manager.BbsTopicMng;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.core.web.front.URLHelper;
import com.jeecms.point.manager.point.PointCalculateMng;

@Controller
public class DynamicBbsPageAct {
	private static final Logger log = LoggerFactory
			.getLogger(DynamicBbsPageAct.class);

	public static final String TPL_INDEX = "tpl.index";
	public static final String TPL_FORUM = "tpl.forum";
	public static final String TPL_TOPIC = "tpl.topic";
	public static final String TPL_NO_VIEW = "tpl.noview";
	public static final String TYPE = "type";

	public String dynamic(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		model.put("type", request.getParameter("findType")==null?"":request.getParameter("findType").toString());
		String[] paths = URLHelper.getPaths(request);
		int len = paths.length;
		if (len == 1) {
			return null;
		} else if (len == 2 || (len ==3 && paths[1].equals(TYPE))) {
			model.put("returnUrl", URLHelper.getURI(request));
			if (paths[1].equals(INDEX) ) {
				// 主题列表初始页
				return forum(paths[0], request, response, model,0);
			} else if(len ==3 && paths[1].equals(TYPE)){
				Integer postTypeId = Integer.parseInt(paths[2]);
				// 主题列表按类别分
				
				return forum(paths[0], request, response, model,postTypeId);
			}else {
				// 主题详细页
				try {
					Integer topicId = Integer.parseInt(paths[1]);
					String topicCreaterStr=request.getParameter("topicCreater")==null?"":request.getParameter("topicCreater").toString();
					Integer topicCreater="".equals(topicCreaterStr)?null:Integer.parseInt(topicCreaterStr);
					model.put("topicCreater",topicCreater);//用于只看楼主作为标识
					
					return topic(paths[0], topicId, request, response, model);
				} catch (NumberFormatException e) {
					log.debug("Content id must String: {}", paths[1]);
					return FrontUtils.pageNotFound(request, response, model);
				}
			}
		}else {
			log.debug("Illegal path length: {}, paths: {}", len, paths);
			return FrontUtils.pageNotFound(request, response, model);
		}
	}

	private String forum(String path, HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Integer postTypeId) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getBbsUser(request);
		FrontUtils.frontData(request, model, site);
		BbsForum forum = bbsForumMng.getByPath(site.getId(), path);
		if (forum == null) {
			model.put("msg", "你所访问的页面不存在");
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_FORUM, TPL_NO_VIEW);
		}
		boolean check = checkView(forum, user, site);
		if (!check) {
			model.put("msg", "你所在会员组没有访问该地址的权限");
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_FORUM, TPL_NO_VIEW);
		}
//		if(postTypeId != null && postTypeId.intValue() > 0)
//		{
//			BbsPostType postType = bbsPostTypeMng.findById(postTypeId);
//			if(null != postType && postType.getParent()!=null)
//				model.put("parentTypeId", postType.getParent().getId());
//		}
		List<BbsPostType> typeList=bbsPostTypeMng.getList(site.getId(), forum.getId(), null);
		Collections.sort(typeList);
		model.put("typeList", typeList);
		
		model.put("typeId", postTypeId);
		model.put("manager", checkManager(forum, user, site));
		model.put("uptop", checkUpTop(forum, user, site));
		model.put("sheild", checkShield(forum, user, site));
		model.put("moderators", checkModerators(forum, user, site));
		model.put("forum", forum);
		
		model.put("ty", "");
		model.put("moderatorNames",bbsForumModeratorMng.findAllNamesByByForumId(forum.getId()));//查找当前版块版主
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_FORUM, TPL_FORUM);
	}

	private String topic(String path, Integer topicId,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getBbsUser(request);
		FrontUtils.frontData(request, model, site);
		BbsForum forum = bbsForumMng.getByPath(site.getId(), path);
		if (forum == null) {
			model.put("msg", "你所访问的页面不存在");
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_FORUM, TPL_NO_VIEW);
		}
		boolean check = checkView(forum, user, site);
		if (!check) {
			model.put("msg", "你所在会员组没有访问该地址的权限");
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_FORUM, TPL_NO_VIEW);
		}
		BbsTopic topic = bbsTopicMng.findById(topicId);
		topicCountEhCache.setViewCount(topicId);
		model.put("moderators", checkModerators(forum, user, site));
		model.put("forum", forum);
		model.put("topic", topic);
		if(user!=null){
		 Boolean canAddPostPoint=pointCalculateMng.replyTopicLimit(user.getId()).isResult();
		model.put("canAddPostPoint",canAddPostPoint);//回贴是否加积分
		}
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_TOPIC, TPL_TOPIC);
	}

	private boolean checkView(BbsForum forum, BbsUser user, CmsSite site) {
		if (forum.getGroupViews() == null) {
			return false;
		} else {
			BbsUserGroup group = null;
			if (user == null) {
				group = bbsConfigMng.findById(site.getId()).getDefaultGroup();
			} else {
				group = user.getGroup();
			}
			if (group == null) {
				return false;
			}
			if (forum.getGroupViews().indexOf("," + group.getId() + ",") == -1) {
				return false;
			}
		}
		return true;
	}

	private boolean checkManager(BbsForum forum, BbsUser user, CmsSite site) {
		if (forum.getGroupViews() == null) {
			return false;
		} else {
			BbsUserGroup group = null;
			if (user == null) {
				group = bbsConfigMng.findById(site.getId()).getDefaultGroup();
			} else {
				group = user.getGroup();
			}
			if (group == null) {
				return false;
			}
			if (!group.hasRight(forum, user)) {
				return false;
			}
			if (!group.topicManage()) {
				return false;
			}
		}
		return true;
	}

	private boolean checkUpTop(BbsForum forum, BbsUser user, CmsSite site) {
		if (forum.getGroupViews() == null) {
			return false;
		} else {
			BbsUserGroup group = null;
			if (user == null) {
				group = bbsConfigMng.findById(site.getId()).getDefaultGroup();
			} else {
				group = user.getGroup();
			}
			if (group == null) {
				return false;
			}
			if (!group.hasRight(forum, user)) {
				return false;
			}
			if (group.topicTop() == 0) {
				return false;
			}
		}
		return true;
	}

	private boolean checkShield(BbsForum forum, BbsUser user, CmsSite site) {
		if (forum.getGroupViews() == null) {
			return false;
		} else {
			BbsUserGroup group = null;
			if (user == null) {
				group = bbsConfigMng.findById(site.getId()).getDefaultGroup();
			} else {
				group = user.getGroup();
			}
			if (group == null) {
				return false;
			}
			if (!group.hasRight(forum, user)) {
				return false;
			}
			if (!group.topicShield()) {
				return false;
			}
		}
		return true;
	}

	private boolean checkModerators(BbsForum forum, BbsUser user, CmsSite site) {
		if (forum.getGroupViews() == null) {
			return false;
		} else {
			BbsUserGroup group = null;
			if (user == null) {
				group = bbsConfigMng.findById(site.getId()).getDefaultGroup();
			} else {
				group = user.getGroup();
			}
			if (group == null) {
				return false;
			}
			if (!group.hasRight(forum, user)) {
				return false;
			}
		}
		return true;
	}

	@Autowired
	private BbsTopicMng bbsTopicMng;
	@Autowired
	private BbsForumMng bbsForumMng;
	@Autowired
	private BbsConfigMng bbsConfigMng;
	@Autowired
	private TopicCountEhCache topicCountEhCache;
	@Autowired
	private BbsPostTypeMng bbsPostTypeMng;
	
	@Autowired
	private BbsForumModeratorMng bbsForumModeratorMng;
	
	@Autowired
	private PointCalculateMng pointCalculateMng;

}
