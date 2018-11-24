package com.jeecms.cms.dao.assist;

import java.util.Date;
import java.util.List;

import com.jeecms.cms.entity.assist.CmsGuestbook;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface CmsGuestbookDao {
	public Pagination getPage(Integer siteId, Integer ctgId,Integer userId, Boolean recommend,
			Boolean checked, boolean desc, boolean cacheable, int pageNo,
			int pageSize);
	
	public Pagination getPage(Integer siteId, Integer ctgIds[],Integer userId, Boolean recommend,
			Boolean checked, boolean desc, boolean cacheable, int pageNo,
			int pageSize);
	
	public List<CmsGuestbook> getList(Integer siteId, Integer ctgId,
			Boolean recommend, Boolean checked, boolean desc,
			boolean cacheable, int first, int max);

	public CmsGuestbook findById(Integer id);

	public CmsGuestbook save(CmsGuestbook bean);

	public CmsGuestbook updateByUpdater(Updater<CmsGuestbook> updater);

	public CmsGuestbook deleteById(Integer id);
	public Pagination getPageByKeyword(Integer siteId, Integer ctgId,Integer userId,Boolean recommend,
			Boolean checked, boolean asc, boolean cacheable, int pageNo,
			int pageSize,String keyword);
	/**
	 * ping.qi 2014-07-03 modify 新增查询条件
	 * */
	public Pagination getPageByKeywordForCms(String title,String createTimeMin,String createTimeMax,String member,Integer siteId, Integer ctgId,Integer ctgIds[],Integer userId, String recommend,
			String checked, boolean desc, boolean cacheable, int pageNo,
			int pageSize);
	
	public int  getTotalCountByUserId(Integer userId);
}