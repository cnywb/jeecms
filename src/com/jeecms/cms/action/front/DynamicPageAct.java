package com.jeecms.cms.action.front;

import static com.jeecms.cms.Constants.TPLDIR_INDEX;
import static com.jeecms.common.web.Constants.INDEX;

import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.bbs.action.front.DynamicBbsPageAct;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.CmsGroup;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.manager.assist.CmsKeywordMng;
import com.jeecms.cms.manager.main.CarOwnerAuthenApplicationMng;
import com.jeecms.cms.manager.main.ChannelMng;
import com.jeecms.cms.manager.main.ContentMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.page.Paginable;
import com.jeecms.common.page.SimplePage;
import com.jeecms.core.web.front.URLHelper;
import com.jeecms.core.web.front.URLHelper.PageInfo;

@Controller
public class DynamicPageAct {
	private static final Logger log = LoggerFactory
			.getLogger(DynamicPageAct.class);
	/**
	 * 首页模板名称
	 */
	public static final String TPL_INDEX = "tpl.index";
	public static final String GROUP_FORBIDDEN = "login.groupAccessForbidden";
	@Autowired
	private CarOwnerAuthenApplicationMng carOwnerAuthenApplicationMng;
	/**
	 * TOMCAT的默认路径
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_INDEX, TPL_INDEX);
	}

	/**
	 * WEBLOGIC的默认路径
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index.jhtml", method = RequestMethod.GET)
	public String indexForWeblogic(HttpServletRequest request,HttpServletResponse response,ModelMap model) {
		Cookie[]  cookies=request.getCookies();
		if(cookies!=null && cookies.length>0){
			int number=0;
			for (int i = 0; i < cookies.length; i++) {
				if("FLASH_SHOW".equals(cookies[i].getName())){
					model.put("FLASH_SHOW","CLOSE");
					number++;
					break;
				}
			}
			if(number==0){
				createCookie(response);
				model.put("FLASH_SHOW","SHOW");
			}
		}else{
			createCookie(response);
			model.put("FLASH_SHOW","SHOW");
		}
		return index(request, model);
	}
	/**
	 * 创建Cookie
	 * @param lq
	 */
	private void createCookie(HttpServletResponse response){
		Cookie cookie = new Cookie("FLASH_SHOW" ,"SHOW");
		cookie.setMaxAge(2*24*60*60*1000);
		response.addCookie(cookie);
	}
	
	
	
	/**
	 * 动态页入口
	 */
	@RequestMapping(value = "/**/*.*")
	public String dynamic(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		if (site == null) {
			return FrontUtils.pageNotFound(request, response, model);
		}
		if (site.getId().equals(2) ) {
			return dynamicBbsPageAct.dynamic(request, response, model);
		} else {
			return dynamicCms(request, response, model);
		}
	}
	
	public String dynamicCms(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		// 尽量不要携带太多参数，多使用标签获取数据。
		// 目前已知的需要携带翻页信息。
		// 获得页号和翻页信息吧。
		int pageNo = URLHelper.getPageNo(request);
		String[] params = URLHelper.getParams(request);
		PageInfo info = URLHelper.getPageInfo(request);
		String[] paths = URLHelper.getPaths(request);
		int len = paths.length;
		
	
		if (len == 1) {
			// 单页
			return channel(paths[0], pageNo, params, info, request, response,
					model);
		} else if (len == 2) {
			if (paths[1].equals(INDEX)) {
				// 栏目页
				
				if(paths[0].equals("czrz")){
					CmsUser user = CmsUtils.getUser(request);
					if(user!=null){
						model.put("tempInfo", carOwnerAuthenApplicationMng.findCarOwnerAuthenApplicationTempInfo(user.getId()));
					}
				}
				
				if(paths[0].equals("fybk")){
					model.put("q",request.getParameter("q"));
				}
				return channel(paths[0], pageNo, params, info, request,
						response, model);
			} else {
				// 内容页
				try {
					Integer id = Integer.parseInt(paths[1]);
					return content(id, pageNo, params, info, request, response,
							model);
				} catch (NumberFormatException e) {
					log.debug("Content id must String: {}", paths[1]);
					return FrontUtils.pageNotFound(request, response, model);
				}
			}
		} else {
			log.debug("Illegal path length: {}, paths: {}", len, paths);
			return FrontUtils.pageNotFound(request, response, model);
		}
	}

	public String channel(String path, int pageNo, String[] params,
			PageInfo info, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		Channel channel = channelMng.findByPathForTag(path, site.getId());
		if (channel == null) {
			log.debug("Channel path not found: {}", path);
			return FrontUtils.pageNotFound(request, response, model);
		}

		model.addAttribute("channel", channel);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		return channel.getTplChannelOrDef();
	}

	public String content(Integer id, int pageNo, String[] params,
			PageInfo info, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Content content = contentMng.findById(id);
		if (content == null) {
			log.debug("Content id not found: {}", id);
			return FrontUtils.pageNotFound(request, response, model);
		}
		CmsUser user = CmsUtils.getUser(request);
		CmsSite site = content.getSite();
		Set<CmsGroup> groups = content.getViewGroupsExt();
		int len = groups.size();
		// 需要浏览权限
		if (len != 0) {
			// 没有登录
			if (user == null) {
				return FrontUtils.showLogin(request, model, site);
			}
			// 已经登录但没有权限
			Integer gid = user.getGroup().getId();
			boolean right = false;
			for (CmsGroup group : groups) {
				if (group.getId().equals(gid)) {
					right = true;
					break;
				}
			}
			if (!right) {
				String gname = user.getGroup().getName();
				return FrontUtils.showMessage(request, model, GROUP_FORBIDDEN,
						gname);
			}
		}
		String txt = content.getTxtByNo(pageNo);
		// 内容加上关键字
		txt = cmsKeywordMng.attachKeyword(site.getId(), txt);
		Paginable pagination = new SimplePage(pageNo, 1, content.getPageCount());
		model.addAttribute("pagination", pagination);
		FrontUtils.frontPageData(request, model);
		model.addAttribute("content", content);
		model.addAttribute("channel", content.getChannel());
		model.addAttribute("title", content.getTitleByNo(pageNo));
		model.addAttribute("txt", txt);
		model.addAttribute("pic", content.getPictureByNo(pageNo));
		FrontUtils.frontData(request, model, site);
		return content.getTplContentOrDef();
	}

	@Autowired
	private ChannelMng channelMng;
	@Autowired
	private ContentMng contentMng;
	@Autowired
	private CmsKeywordMng cmsKeywordMng;
	@Autowired
	private DynamicBbsPageAct dynamicBbsPageAct;
	
}
