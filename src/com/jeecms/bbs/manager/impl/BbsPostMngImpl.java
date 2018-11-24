package com.jeecms.bbs.manager.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jeecms.bbs.cache.BbsConfigEhCache;
import com.jeecms.bbs.dao.BbsPostDao;
import com.jeecms.bbs.entity.Attachment;
import com.jeecms.bbs.entity.BbsForum;
import com.jeecms.bbs.entity.BbsPost;
import com.jeecms.bbs.entity.BbsPostText;
import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.BbsUserGroup;
import com.jeecms.bbs.manager.AttachmentMng;
import com.jeecms.bbs.manager.BbsConfigMng;
import com.jeecms.bbs.manager.BbsOperationMng;
import com.jeecms.bbs.manager.BbsPostMng;
import com.jeecms.bbs.manager.BbsPostTypeMng;
import com.jeecms.bbs.manager.BbsTopicMng;
import com.jeecms.bbs.manager.BbsUserGroupMng;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.bbs.web.StrUtils;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.manager.main.CmsSiteMng;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.upload.FileRepository;
import com.jeecms.common.util.DateUtils;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.UnifiedUserMng;

@Service
@Transactional
public class BbsPostMngImpl implements BbsPostMng {

	public BbsPost shield(Integer id, String reason, BbsUser operator,Short status) {
		BbsPost post = dao.findById(id);
		post.setStatus(status);
		if(status==-1){
			bbsOperationMng.saveOpt(post.getSite(), operator, "屏蔽", reason, post);
		}else if(status==0){
			bbsOperationMng.saveOpt(post.getSite(), operator, "取消屏蔽", reason, post);
		}
		
		return post;
	}

	public BbsPost updatePost(Integer id, String title, String content,
			BbsUser editor, String ip) {
		// 修改BbsPost
		BbsPost post = dao.findById(id);
		post.setEditCount(post.getEditCount() + 1);
		post.setEditerIp(ip);
		post.setEditTime(new Timestamp(System.currentTimeMillis()));
		post.setEditer(editor);
		
		if (findHidden(content)) {
			post.setHidden(true);
		} else {
			post.setHidden(false);
		}
		if (post.isFirst()) {
			post.getTopic().setTitle(title);
		}
		// 修改BbsPostText
		BbsPostText text = post.getPostText();
		text.setTitle(title);
		text.setContent(content);
		
		// 写入操作日志
		bbsOperationMng.saveOpt(post.getSite(), editor, "编辑", null, post);
		return post;
	}
	
	public BbsPost updatePost(Integer id, String title,Boolean visibleAfterReply, String content,
			BbsUser editor, String ip, List<MultipartFile> file,
			List<String> code) {
		// 修改BbsPost
		BbsPost post = dao.findById(id);
		post.setEditCount(post.getEditCount() + 1);
		post.setEditerIp(ip);
		post.setEditTime(new Timestamp(System.currentTimeMillis()));
		post.setEditer(editor);
		if (file != null && file.size() > 0) {
			post.setAffix(true);
		}
		if (findHidden(content)) {
			post.setHidden(true);
		} else {
			post.setHidden(false);
		}
		BbsPostText text = post.getPostText();
		if (post.isFirst()) {
			post.getTopic().setTitle(title);
			post.getTopic().setVisibleAfterReply(visibleAfterReply);
			text.setTitle(title);
		}
		if (file != null && file.size() > 0) {
			content = uploadImg(content, file, code, 1, post);
		}
		text.setContent(content);
		// 写入操作日志
		bbsOperationMng.saveOpt(post.getSite(), editor, "编辑", null, post);
		return post;
	}

	
	public BbsPost post(Integer userId, Integer siteId, Integer topicId,Integer postTypeId,
			String title, String content, String ip, List<MultipartFile> file,
			List<String> code){
		
		return post(null, userId, siteId, topicId, postTypeId, title, content, ip, file, code);
	}
	public BbsPost post(Integer replyToPostId,Integer userId, Integer siteId, Integer topicId,Integer postTypeId,
			String title, String content, String ip, List<MultipartFile> file,
			List<String> code) {
		BbsPostText text = new BbsPostText();
		BbsPost post = new BbsPost();
		BbsTopic topic = bbsTopicMng.findById(topicId);
		post.setSite(siteMng.findById(siteId));
		post.setConfig(bbsConfigMng.findById(siteId));
		post.setTopic(topic);
		BbsPost replyToPost =null;
		
		if(replyToPostId!=null&&replyToPostId.intValue()!=0){
			replyToPost = dao.findById(replyToPostId);;
			post.setReplyToBbsPost(replyToPost);
		}
		
		if(postTypeId!=null){
			post.setPostType(bbsPostTypeMng.findById(postTypeId));
		}
		post.setCreater(bbsUserMng.findById(userId));
		if (file != null && file.size() > 0) {
			post.setAffix(true);
		} else {
			post.setAffix(false);
		}
		if (findHidden(content)) {
			post.setHidden(true);
		} else {
			post.setHidden(false);
		}
		initBbsPost(post, ip);
		post = dao.save(post);
		if (file != null && file.size() > 0) {
			content = uploadImg(content, file, code, siteId, post);
		}
		text.setTitle(title);
		if(replyToPost!=null&&replyToPost.getIndexCount()!=1){//如果不是回复第一楼
			StringBuffer sb=new StringBuffer("<div class=\"box\">");
			sb.append("<span class=\"boxTitle\">");
			sb.append(replyToPost.getCreater().getUsername()+" 发表于："+DateUtils.format(replyToPost.getCreateTime(), DateUtils.FORMAT_DATE_TIME_DEFAULT));
			sb.append("</span>");
			sb.append("<br/>");
			sb.append(replyToPost.getContent());
			sb.append("</div>");
			sb.append(content);
			text.setContent(sb.toString());
		}else{
			text.setContent(content);
		}
		
		post.setPostText(text);
		post.setIndexCount(getIndexCount(topicId));
		if (post.getIndexCount() == 1) {
			topic.setHaveReply(",");
		} else {
			if (topic.getHaveReply() == null) {
				topic.setHaveReply("," + userId + ",");
			} else {
				if (topic.getHaveReply().indexOf("," + userId + ",") == -1) {
					topic.setHaveReply(topic.getHaveReply() + userId + ",");
				}
			}
		}
		return post;
	}

	public List<BbsPost> getPostByTopic(Integer topicId) {
		return dao.getPostByTopic(topicId);
	}

	public BbsPost reply(Integer userId, Integer siteId, Integer topicId,Integer postTypeId,
			String title, String content, String ip, List<MultipartFile> file,
			List<String> code){
		return reply(null, userId, siteId, topicId, postTypeId, title, content, ip, file, code);
	}
	public BbsPost reply(Integer replyToPostId,Integer userId, Integer siteId, Integer topicId,Integer postTypeId,
			String title, String content, String ip, List<MultipartFile> file,
			List<String> code) {
		BbsPost post = post(replyToPostId,userId, siteId, topicId,postTypeId, title, content, ip, file,code);
		if (bbsUserMng.findById(userId).getModerator()) {
			post.getTopic().setModeratorReply(true);
		}
		updatePostCount(post, bbsUserMng.findById(userId));
		bbsConfigEhCache.setBbsConfigCache(1, 0, 1, 0, null, siteId);
		return post;
	}

	public String uploadImg(String content, List<MultipartFile> file,
			List<String> code, Integer siteId, BbsPost post) {
		List<String> list = findImgUrl(content);
		for (int i = 0; i < code.size(); i++) {
			if (list.contains(code.get(i))) {
				String origName = file.get(i).getOriginalFilename();
				String ext = FilenameUtils.getExtension(origName).toLowerCase(
						Locale.ENGLISH);
				String filepath;
				try {
					filepath = fileRepository.storeByExt(siteMng.findById(
							siteId).getUploadPath(), ext, file.get(i));
					Attachment att = addAttachment(origName, filepath, post,
							file.get(i).getSize());
					int j = i + 1;
					content = StrUtils.replace(content, "[localimg]" + j
							+ "[/localimg]", "[attachment]" + att.getId()
							+ "[/attachment]");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content;
	}

	private List<String> findImgUrl(String content) {
		String ems = "\\[localimg]([0-9]+)\\[/localimg]";
		Matcher matcher = Pattern.compile(ems).matcher(content);
		List<String> list = new ArrayList<String>();
		while (matcher.find()) {
			String url = matcher.group(1);
			list.add(url);
		}
		return list;
	}

	private boolean findHidden(String content) {
		String ems = "\\[hide\\]([\\s\\S]*)\\[/hide\\]";
		Matcher matcher = Pattern.compile(ems).matcher(content);
		while (matcher.find()) {
			return true;
		}
		return false;
	}

	@Transactional(readOnly = true)
	public Pagination getForTag(Integer siteId, Integer topicId,
			Integer userId, int pageNo, int pageSize) {
		
		short groupType=1;
		List<BbsUserGroup> groupList=bbsUserGroupMng.getList(2,groupType);
		
		Pagination page=dao.getForTag(siteId, topicId, userId, pageNo, pageSize);
		List<BbsPost> list=(List<BbsPost>)page.getList();
    	 for(BbsPost t:list){
				CmsUser u=cmsUserMng.findById(t.getCreater().getId());
				UnifiedUser uu=unifiedUserMng.findById(t.getCreater().getId());
				t.getCreater().setCmsUserComefrom(u.getUserExt()==null?"":u.getUserExt().getCity());
				t.getCreater().setCmsUserCarStyle(u.getUserExt()==null?"":u.getUserExt().getCarStyle());
				if(uu!=null){
					t.getCreater().setUserCurrentPoint(uu.getCurrentPoint()==null?0L:uu.getCurrentPoint());
				}
				BbsUserGroup nextGroup=getNextUserGoup(groupList,t.getCreater().getGroup().getGradeNum()+1);
				if(nextGroup!=null){
					t.getCreater().setNextGroupName(nextGroup.getName());
					long percentToNextGroup=(t.getCreater().getPoint()*100L/nextGroup.getPoint());
					t.getCreater().setPercentToNextGroup(percentToNextGroup);
				}else{
					t.getCreater().setNextGroupName(t.getCreater().getGroup().getName());
					t.getCreater().setPercentToNextGroup(100L);
				}
				
		}
		page.setList(list);
		return page;
	}

	public BbsPost getLastPost(Integer forumId, Integer topicId) {
		return dao.getLastPost(forumId, topicId);
	}

	@Transactional(readOnly = true)
	public Pagination getMemberReply(Integer webId, Integer memberId,
			int pageNo, int pageSize) {
		int count = dao.getMemberReplyCount(webId, memberId);
		if (pageSize == 0) {
			pageSize = 16;
		}
		if ((count / pageSize + 1) < pageNo) {
			pageNo = count / pageSize + 1;
		}
		if (pageNo < 1) {
			pageNo = 1;
		}
		List<Number> list = dao.getMemberReply(webId, memberId, (pageNo - 1)
				* pageSize, pageSize);
		List<BbsPost> l = new ArrayList<BbsPost>();
		Pagination p = new Pagination();
		for (Number b : list) {
			BbsPost bbspost = dao.findById(b.intValue());
			l.add(bbspost);
		}
		p.setPageNo(pageNo);
		p.setPageSize(pageSize);
		p.setTotalCount(count);
		p.setList(l);
		return p;
	}

	@Transactional(readOnly = true)
	public int getMemberReplyCount(Integer webId, Integer memberId) {
		return dao.getMemberReplyCount(webId, memberId);
	}

	@Transactional(readOnly = true)
	public int getIndexCount(Integer topicId) {
		return dao.getIndexCount(topicId);
	}

	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public BbsPost findById(Integer id) {
		BbsPost entity = dao.findById(id);
		return entity;
	}

	public BbsPost save(BbsPost bean) {
		dao.save(bean);
		return bean;
	}

	public BbsPost update(BbsPost bean) {
		Updater<BbsPost> updater = new Updater<BbsPost>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public BbsPost deleteById(Integer id) {
		BbsPost bean = dao.deleteById(id);
		return bean;
	}

	public BbsPost[] deleteByIds(Integer[] ids) {
		BbsPost[] beans = new BbsPost[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	
	public List<BbsPost> getList(int count, int orderby) {
		List<Integer>postIds= dao.getList(count,orderby);
		List<BbsPost>results=new ArrayList<BbsPost>();
		if(postIds!=null&&postIds.size()>0){
			for(Integer postId:postIds){
				results.add(findById(postId));
			}
		}
		return results;
	}

	private Attachment addAttachment(String filename, String filepath,
			BbsPost post, Long size) {
		Attachment attach = new Attachment();
		attach.setName(filename);
		attach.setFileName(filename);
		attach.setFilePath(filepath);
		attach.setFileSize(size.intValue());
		attach.setPost(post);
		/*
		 * 判断图片还是附件
		 */
		// boolean tag = false;
		
		int indx = filename.indexOf('.');
		String type = "";
		if(indx!=-1){
			type = filename.substring(indx+1);
		}

		if("zip".equals(type.toLowerCase())||"rar".equals(type.toLowerCase())){
			attach.setPicture(false);
		}else{
			attach.setPicture(true);
		}
		attach.setCreateTime(new Timestamp(System.currentTimeMillis()));
		attachmentMng.save(attach);
		return attach;
	}

	private void initBbsPost(BbsPost post, String ip) {
		Date now = new Timestamp(System.currentTimeMillis());
		post.setCreateTime(now);
		post.setPosterIp(ip);
		post.setEditCount(0);
		post.setStatus(BbsPost.NORMAL);
		post.setAnonymous(false);
		if (post.getIndexCount() == null) {
			post.setIndexCount(0);
		}
	}

	public void updatePostCount(BbsPost post, BbsUser user) {
		BbsTopic topic = post.getTopic();
		BbsForum forum = topic.getForum();
		forum.setLastPost(post);
		forum.setLastReply(post.getCreater());
		forum.setLastTime(post.getCreateTime());
		forum.setPostToday(forum.getPostToday() + 1);
		forum.setPostTotal(forum.getPostTotal() + 1);
		topic.setLastPost(post);
		topic.setLastReply(post.getCreater());
		topic.setLastTime(post.getCreateTime());
		topic.setSortTime(post.getCreateTime());
		topic.setReplyCount(topic.getReplyCount() + 1);
		topic.setReplyCountDay(topic.getReplyCountDay()+1);
		//是否启用积分
		if(forum.getPointAvailable()){
			user.setPoint(user.getPoint() + forum.getPointReply());
		}
		//是否启用威望
		if(forum.getPrestigeAvailable()){
			user.setPrestige(user.getPrestige()+forum.getPrestigeReply());
		}
		user.setPostToday(user.getPostToday() + 1);
		user.setReplyCount(user.getReplyCount() + 1);
	}

	public int getCountByForumId(Integer forumId){
		return dao.getCountByForumId(forumId);
	}
	@Transactional(readOnly = true)
	public Pagination getReplyToMe(Integer siteId, Integer userId,
			int pageNo, int pageSize) {
		return dao.getReplyToMe(siteId,userId, pageNo, pageSize);
	}
	
	public void setToHeadline(Integer[] ids) {
		
		for (int i = 0, len = ids.length; i < len; i++) {
			
		}
	
	}
	@Transactional(readOnly = true)
	public int  getTotalCountByTopicIdAndCreaterId(Integer topicId,Integer createrId){
		return dao.getTotalCountByTopicIdAndCreaterId(topicId, createrId);
	}
	
	public List<String>getFirstPostImageSrcList(Integer topicId){
		return dao.getFirstPostImageSrcList(topicId);
	}
	
	public int  getReplyToMeTotalCountByUserId(Integer userId){
		return dao.getReplyToMeTotalCountByUserId(userId);
	}
	
	
	private BbsOperationMng bbsOperationMng;
	private BbsConfigMng bbsConfigMng;
	private CmsSiteMng siteMng;
	private BbsTopicMng bbsTopicMng;
	private BbsUserMng bbsUserMng;
	private BbsConfigEhCache bbsConfigEhCache;
	private AttachmentMng attachmentMng;
	private FileRepository fileRepository;
	private BbsPostTypeMng bbsPostTypeMng;
	private BbsPostDao dao;


	@Autowired
	public void setDao(BbsPostDao dao) {
		this.dao = dao;
	}

	@Autowired
	public void setBbsConfigMng(BbsConfigMng bbsConfigMng) {
		this.bbsConfigMng = bbsConfigMng;
	}

	@Autowired
	public void setSiteMng(CmsSiteMng siteMng) {
		this.siteMng = siteMng;
	}

	@Autowired
	public void setBbsUserMng(BbsUserMng bbsUserMng) {
		this.bbsUserMng = bbsUserMng;
	}

	@Autowired
	public void setBbsOperationMng(BbsOperationMng bbsOperationMng) {
		this.bbsOperationMng = bbsOperationMng;
	}

	@Autowired
	public void setBbsTopicMng(BbsTopicMng bbsTopicMng) {
		this.bbsTopicMng = bbsTopicMng;
	}

	@Autowired
	public void setBbsConfigEhCache(BbsConfigEhCache bbsConfigEhCache) {
		this.bbsConfigEhCache = bbsConfigEhCache;
	}

	@Autowired
	public void setAttachmentMng(AttachmentMng attachmentMng) {
		this.attachmentMng = attachmentMng;
	}

	@Autowired
	public void setFileRepository(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}
	@Autowired
	public void setBbsPostTypeMng(BbsPostTypeMng bbsPostTypeMng) {
		this.bbsPostTypeMng = bbsPostTypeMng;
	}

	@Override
	public BbsPost readById(Integer id) {
		return dao.readById(id);
	}
	
	
	private BbsUserGroup getNextUserGoup(List<BbsUserGroup> groupList,Integer gradeNum){
		if(gradeNum==1){
			return null;
		}
		for(BbsUserGroup g:groupList){
			if(g.getGradeNum().intValue()==gradeNum.intValue()){
				return g;
			}
		}
		return null;
	}
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private UnifiedUserMng unifiedUserMng ;
	
	@Autowired
	private BbsUserGroupMng bbsUserGroupMng;

	/* (non-Javadoc)
	 * @see com.jeecms.bbs.manager.BbsPostMng#clearS(com.jeecms.bbs.entity.BbsPost)
	 */
}
