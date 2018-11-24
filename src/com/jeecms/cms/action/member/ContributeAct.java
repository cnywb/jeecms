package com.jeecms.cms.action.member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 会员投稿Action
 * 
 */
@Controller
public class ContributeAct extends AbstractContentMemberAct {

	public static final String CONTRIBUTE_LIST = "tpl.contributeList";
	public static final String CONTRIBUTE_ADD = "tpl.contributeAdd";
	public static final String CONTRIBUTE_EDIT = "tpl.contributeEdit";

	/**
	 * 会员投稿列表
	 * 
	 * @param title
	 *            文章标题
	 * @param channelId
	 *            栏目ID
	 * @param pageNo
	 *            页码
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/contribute_list.jspx")
	public String list(String queryTitle, Integer modelId,
			Integer queryChannelId, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		return super.list(queryTitle, modelId, queryChannelId, CONTRIBUTE_LIST,
				pageNo, request, model);
	}

	/**
	 * 会员投稿添加
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/contribute_add.jspx")
	public String add(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		return super.add(true, CONTRIBUTE_ADD, request, response, model);
	}

	/**
	 * 会员投稿保存
	 * 
	 * @param id
	 *            文章ID
	 * @param title
	 *            标题
	 * @param author
	 *            作者
	 * @param description
	 *            描述
	 * @param txt
	 *            内容
	 * @param tagStr
	 *            TAG字符串
	 * @param channelId
	 *            栏目ID
	 * @param nextUrl
	 *            下一个页面地址
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/contribute_save.jspx")
	public String save(String title, String author, String description,
			String txt, String tagStr, Integer channelId,Integer modelId, String captcha,
			String nextUrl, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return super.save(title, author, description, txt, tagStr, channelId,modelId,
				null, captcha, nextUrl, request, response, model);
	}

	/**
	 * 会员投稿修改
	 * 
	 * @param id
	 *            文章ID
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/contribute_edit.jspx")
	public String edit(Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return super.edit(id, CONTRIBUTE_EDIT, request, response, model);
	}

	/**
	 * 会有投稿更新
	 * 
	 * @param id
	 *            文章ID
	 * @param title
	 *            标题
	 * @param author
	 *            作者
	 * @param description
	 *            描述
	 * @param txt
	 *            内容
	 * @param tagStr
	 *            TAG字符串
	 * @param channelId
	 *            栏目ID
	 * @param nextUrl
	 *            下一个页面地址
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/contribute_update.jspx")
	public String update(Integer id, String title, String author,
			String description, String txt, String tagStr, Integer channelId,
			String nextUrl, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return super.update(id, title, author, description, txt, tagStr,
				channelId, null, nextUrl, request, response, model);
	}

	/**
	 * 会员投稿删除
	 * 
	 * @param ids
	 *            待删除的文章ID数组
	 * @param nextUrl
	 *            下一个页面地址
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/contribute_delete.jspx")
	public String delete(Integer[] ids, HttpServletRequest request,
			String nextUrl, HttpServletResponse response, ModelMap model) {
		return super.delete(ids, request, nextUrl, response, model);
	}
}
