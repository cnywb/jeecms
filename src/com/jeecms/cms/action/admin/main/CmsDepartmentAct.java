package com.jeecms.cms.action.admin.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.entity.assist.CmsGuestbookCtg;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.CmsDepartment;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.manager.assist.CmsGuestbookCtgMng;
import com.jeecms.cms.manager.main.ChannelMng;
import com.jeecms.cms.manager.main.CmsDepartmentMng;
import com.jeecms.cms.manager.main.CmsLogMng;
import com.jeecms.cms.manager.main.CmsSiteMng;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.WebErrors;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.common.web.ResponseUtils;
import static com.jeecms.common.page.SimplePage.cpn;

@Controller
public class CmsDepartmentAct {
	private static final Logger log = LoggerFactory
			.getLogger(CmsDepartmentAct.class);

	@RequestMapping("/department/v_list.do")
	public String list(Integer parentId, HttpServletRequest request,ModelMap model) {
		List<CmsDepartment>list=manager.getList(parentId, false);
		model.addAttribute("list", list);
		appendModelAttribute(model, parentId);
		return "department/list";
	}
	

	@RequestMapping("/department/v_add.do")
	public String add(ModelMap model, HttpServletRequest request) {
		model.addAttribute("site",CmsUtils.getSite(request));
		List<CmsSite> siteList = cmsSiteMng.getList();
		List<CmsGuestbookCtg>ctgList=guestBookCtgMng.getList(CmsUtils.getSiteId(request));
		model.addAttribute("siteList", siteList);
		model.addAttribute("ctgList", ctgList);
		return "department/add";
	}

	@RequestMapping("/department/o_save.do")
	public String save(CmsDepartment bean, Integer pid,Integer[] channelIds,Integer[] ctgIds,
			HttpServletRequest request,ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		if(pid!=null&&!pid.equals(0)){
			CmsDepartment parent=manager.findById(pid);
			bean.setParent(parent);
		}
		bean = manager.save(bean,channelIds,ctgIds);
		log.info("save CmsDepartment id={}", bean.getId());
		cmsLogMng.operating(request, "cmsdepartment.log.save", "id="
				+ bean.getId() + ";name=" + bean.getName());
		return "redirect:v_list.do";
	}

	@RequestMapping("/department/v_check_name.do")
	public String checkName(String name, HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtils.isBlank(name) || manager.nameExist(name)) {
			ResponseUtils.renderJson(response, "false");
		} else {
			ResponseUtils.renderJson(response, "true");
		}
		return null;
	}

	@RequestMapping("/department/v_edit.do")
	public String edit(Integer id,Integer parentId, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsDepartment department= manager.findById(id);
		List<CmsSite> siteList = cmsSiteMng.getList();
		List<CmsGuestbookCtg>ctgList=guestBookCtgMng.getList(CmsUtils.getSiteId(request));
		Map<String, Set<Integer>>departChannelIds=new HashMap<String, Set<Integer>>();
		for(CmsSite site:siteList){
			departChannelIds.put(site.getId().toString(), department.getChannelIds(site.getId()));
		}
		model.addAttribute("department", department);
		model.addAttribute("siteList", siteList);
		model.addAttribute("ctgList", ctgList);
		model.addAttribute("site",CmsUtils.getSite(request));
		model.addAttribute("departChannelIds", departChannelIds);
		model.addAttribute("ctgIds", department.getGuestBookCtgIds());
		appendModelAttribute(model, parentId);
		return "department/edit";
	}

	@RequestMapping("/department/o_update.do")
	public String update(CmsDepartment bean, Integer pid,Integer[] channelIds,Integer[] ctgIds, Integer parentId,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		if(pid!=null&&!pid.equals(0)){
			CmsDepartment parent=manager.findById(pid);
			bean.setParent(parent);
		}
		bean = manager.update(bean,channelIds,ctgIds);
		log.info("update CmsDepartment id={}.", bean.getId());
		cmsLogMng.operating(request, "cmsdepartment.log.update", "id="
				+ bean.getId() + ";name=" + bean.getName());
		return list(parentId,request, model);
	}

	@RequestMapping("/department/o_priority.do")
	public String priority(Integer[] wids, Integer[] priority, Integer parentId, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validatePriority(wids, priority, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		manager.updatePriority(wids, priority);
		log.info("update department priority");
		return list(parentId, request, model);
	}

	@RequestMapping("/department/o_delete.do")
	public String delete(Integer[] ids, Integer parentId,HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsDepartment[] beans = manager.deleteByIds(ids);
		for (CmsDepartment bean : beans) {
			log.info("delete department id={}", bean.getId());
			cmsLogMng.operating(request, "cmsdepartment.log.delete", "id="
					+ bean.getId() + ";name=" + bean.getName());
		}
		return list(parentId, request, model);
	}
	
	@RequestMapping(value = "/department/v_tree.do")
	public String selectParent(String root, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		log.debug("tree path={}", root);
		boolean isRoot;
		// jquery treeview的根请求为root=source
		if (StringUtils.isBlank(root) || "source".equals(root)) {
			isRoot = true;
		} else {
			isRoot = false;
		}
		model.addAttribute("isRoot", isRoot);
		List<CmsDepartment> departList;
		if(isRoot){
			departList= manager.getList(null,false);
		}else{
			departList = manager.getList(Integer.parseInt(root),false);
		}
		model.addAttribute("list", departList);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "department/tree";
	}

	@RequestMapping(value = "/department/v_channels_add.do")
	public String channelsAdd(Integer siteId, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return channelsAddJson(siteId, request, response, model);
	}

	@RequestMapping(value = "/department/v_channels_edit.do")
	public String channelsEdit(Integer departId, Integer siteId,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		return channelsEditJson(departId, siteId, request, response, model);
	}
	

	@RequestMapping("/department/v_list_members.do")
	public String list_members(Integer departId, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Pagination pagination = userMng.getAdminsByDepartId(departId,cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("departId", departId);
		return "department/members";
	}
	

	private String channelsAddJson(Integer siteId, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		List<Channel> channelList = channelMng.getTopList(siteId, false);
		model.addAttribute("channelList", channelList);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "department/channels_add";
	}

	private String channelsEditJson(Integer departId, Integer siteId,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		List<Channel> channelList = channelMng.getTopList(siteId, false);
		CmsDepartment department = manager.findById(departId);
		model.addAttribute("channelList", channelList);
		model.addAttribute("channelIds", department.getChannelIds());
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "department/channels_edit";
	}


	private WebErrors validatePriority(Integer[] ids, Integer[] priorities,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		return errors;
	}

	private WebErrors validateSave(CmsDepartment bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		return errors;
	}

	private WebErrors validateEdit(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldExist(id, errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldExist(id, errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifEmpty(ids, "ids")) {
			return errors;
		}
		for (Integer id : ids) {
			vldExist(id, errors);
		}
		return errors;
	}

	private boolean vldExist(Integer id, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		CmsDepartment entity = manager.findById(id);
		if (errors.ifNotExist(entity, CmsDepartment.class, id)) {
			return true;
		}
		return false;
	}

	private void appendModelAttribute(ModelMap model, Integer parentId) {
		Integer pid=null;
		if(parentId!=null){
			CmsDepartment parent=manager.findById(parentId);
			if(parent!=null&&parent.getParent()!=null){
				pid=parent.getParent().getId();
			}
		}
		model.addAttribute("pid", pid);
		model.addAttribute("parentId", parentId);
	}

	@Autowired
	private CmsLogMng cmsLogMng;
	@Autowired
	private CmsDepartmentMng manager;
	@Autowired
	private ChannelMng channelMng;
	@Autowired
	private CmsSiteMng cmsSiteMng;
	@Autowired
	private CmsGuestbookCtgMng guestBookCtgMng;
	@Autowired
	private CmsUserMng userMng;
}