package com.jeecms.cms.dao.assist.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.assist.CmsGuestbookDao;
import com.jeecms.cms.entity.assist.CmsGuestbook;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.common.util.ValidateUtils;

@Repository
public class CmsGuestbookDaoImpl extends
		HibernateBaseDao<CmsGuestbook, Integer> implements CmsGuestbookDao {
	public Pagination getPage(Integer siteId, Integer ctgId,Integer userId,Boolean recommend,
			Boolean checked, boolean asc, boolean cacheable, int pageNo,
			int pageSize) {
		Finder f = createFinder(siteId, ctgId, null,userId,recommend, checked, asc,
				cacheable);
		return find(f, pageNo, pageSize);
	}
	
	public Pagination getPage(Integer siteId, Integer ctgIds[],Integer userId,Boolean recommend,
			Boolean checked, boolean asc, boolean cacheable, int pageNo,
			int pageSize) {
		Finder f = createFinder(siteId, null, ctgIds, userId, recommend, checked, asc, cacheable);
		return find(f, pageNo, pageSize);
	}
	/**
	 * ping.qi 2014-07-03 modify add search条件
	 */
	public Pagination getPageByKeywordForCms(String title,String createTimeMin,String createTimeMax,String member,Integer siteId, Integer ctgId,Integer ctgIds[],Integer userId, String recommend,
			String checked, boolean desc, boolean cacheable, int pageNo,
			int pageSize){
		Finder f=createFinderByKeyWordForCms(title,createTimeMin,createTimeMax,member,siteId,ctgId, ctgIds, userId, recommend, checked, desc, cacheable);
		return find(f, pageNo, pageSize);
	}
	public Pagination getPageByKeyword(Integer siteId, Integer ctgId,Integer userId,Boolean recommend,
			Boolean checked, boolean asc, boolean cacheable, int pageNo,
			int pageSize,String keyword) {
		Finder f = createFinder(siteId, ctgId, null,userId,recommend, checked, asc,
				cacheable,keyword);
		return find(f, pageNo, pageSize);
	}
	

	@SuppressWarnings("unchecked")
	public List<CmsGuestbook> getList(Integer siteId, Integer ctgId,
			Boolean recommend, Boolean checked, boolean desc,
			boolean cacheable, int first, int max) {
		Finder f = createFinder(siteId, ctgId, null,null,recommend, checked, desc,
				cacheable);
		f.setFirstResult(first);
		f.setMaxResults(max);
		return find(f);
	}
	

    private Finder createFinderByKeyWordForCms(String title,String createTimeMin,String createTimeMax,String member,Integer siteId,Integer ctgId, Integer ctgIds[],Integer userId,
    		String recommend, String checked, boolean desc, boolean cacheable){
    	Finder f = Finder.create("from CmsGuestbook bean where 1=1");
		if (siteId != null && !"".equals(siteId)) {
			f.append(" and bean.site.id=:siteId");
			f.setParam("siteId", siteId);
		}
		if (ctgId != null && !"".equals(ctgId)) {//留言分类
			f.append(" and bean.ctg.id =:ctgId");
			f.setParam("ctgId", ctgId);
		}
		if(ctgIds!=null&&ctgIds.length>0){
			f.append(" and bean.ctg.id in(:ctgIds)");
			f.setParamList("ctgIds", ctgIds);
		}
		if (userId != null && !"".equals(userId)) {
			f.append(" and bean.member.id=:userId");
			f.setParam("userId", userId);
		}
		if (!StringUtils.isEmpty(member)) {
			f.append(" and bean.member.username=:member");
			f.setParam("member", member);
		}
		if (!StringUtils.isEmpty(recommend)) {//推荐
			Boolean recommendB="1".equals(recommend)?true:false;
			f.append(" and bean.recommend=:recommendB");
			f.setParam("recommendB", recommendB);
		}
		if (!StringUtils.isEmpty(checked)) {//审核
			Boolean checkedB="1".equals(checked)?true:false;
			f.append(" and bean.checked=:checkedB");
			f.setParam("checkedB", checkedB);
		}
		if(!StringUtils.isEmpty(title)){//标题
			f.append(" and bean.ext.title like:keyword");
			f.setParam("keyword", "%"+title+"%");
		}
		if(!ValidateUtils.isEmpty(createTimeMin)){
			createTimeMin=createTimeMin+" 00:00:00";
			Date minTime=DateUtils.parseDate(createTimeMin, DateUtils.FORMAT_DATE_TIME_DEFAULT);
			f.append(" and bean.createTime>=:minTime");
			f.setParam("minTime",minTime);
		}
		if(!ValidateUtils.isEmpty(createTimeMax)){
			createTimeMax=createTimeMax+" 23:59:59";
			Date maxTime=DateUtils.parseDate(createTimeMax, DateUtils.FORMAT_DATE_TIME_DEFAULT);
			f.append(" and bean.createTime<=:maxTime");
			f.setParam("maxTime",maxTime);
		}
		if (desc) {
			f.append(" order by bean.id desc");
		} else {
			f.append(" order by bean.id asc");
		}
		f.setCacheable(cacheable);
		return f;
    }
	private Finder createFinder(Integer siteId, Integer ctgId,Integer ctgIds[],Integer userId,
			Boolean recommend, Boolean checked, boolean desc, boolean cacheable) {
		Finder f = Finder.create("from CmsGuestbook bean where 1=1");
		if (siteId != null) {
			f.append(" and bean.site.id=:siteId");
			f.setParam("siteId", siteId);
		}
		if (ctgId != null) {
			f.append(" and bean.ctg.id =:ctgId");
			f.setParam("ctgId", ctgId);
		}
		if(ctgIds!=null&&ctgIds.length>0){
			f.append(" and bean.ctg.id in(:ctgIds)");
			f.setParamList("ctgIds", ctgIds);
		}
		if (userId != null) {
			f.append(" and bean.member.id=:userId");
			f.setParam("userId", userId);
		}
		if (recommend != null) {
			f.append(" and bean.recommend=:recommend");
			f.setParam("recommend", recommend);
		}
		if (checked != null) {
			f.append(" and bean.checked=:checked");
			f.setParam("checked", checked);
		}
		if (desc) {
			f.append(" order by bean.id desc");
		} else {
			f.append(" order by bean.id asc");
		}
		f.setCacheable(cacheable);
		return f;
	}

	
	private Finder createFinder(Integer siteId, Integer ctgId,Integer ctgIds[],Integer userId,
			Boolean recommend, Boolean checked, boolean desc, boolean cacheable,String keyword){
		Finder f = Finder.create("from CmsGuestbook bean where 1=1");
		if (siteId != null) {
			f.append(" and bean.site.id=:siteId");
			f.setParam("siteId", siteId);
		}
		if (ctgId != null) {
			f.append(" and bean.ctg.id =:ctgId");
			f.setParam("ctgId", ctgId);
		}
		if(ctgIds!=null&&ctgIds.length>0){
			f.append(" and bean.ctg.id in(:ctgIds)");
			f.setParamList("ctgIds", ctgIds);
		}
		if (userId != null) {
			f.append(" and bean.member.id=:userId");
			f.setParam("userId", userId);
		}
		if (recommend != null) {
			f.append(" and bean.recommend=:recommend");
			f.setParam("recommend", recommend);
		}
		if (checked != null) {
			f.append(" and bean.checked=:checked");
			f.setParam("checked", checked);
		}
		if(!StringUtils.isEmpty(keyword)){
			f.append(" and LOWER(bean.ext.title) like:keyword");
			f.setParam("keyword", "%"+keyword.toLowerCase()+"%");
		}
		if (desc) {
			f.append(" order by bean.id desc");
		} else {
			f.append(" order by bean.id asc");
		}
		f.setCacheable(cacheable);
		return f;
		
	}
	
	public int  getTotalCountByUserId(Integer userId){
		Finder f = Finder.create("from CmsGuestbook bean where bean.member.id=:userId");
		f.setParam("userId",userId);
		return 	countQueryResult(f);
	}
	
	public CmsGuestbook findById(Integer id) {
		CmsGuestbook entity = get(id);
		return entity;
	}

	public CmsGuestbook save(CmsGuestbook bean) {
		getSession().save(bean);
		return bean;
	}

	public CmsGuestbook deleteById(Integer id) {
		CmsGuestbook entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<CmsGuestbook> getEntityClass() {
		return CmsGuestbook.class;
	}
}