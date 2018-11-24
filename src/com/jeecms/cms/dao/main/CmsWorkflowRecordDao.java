package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.cms.entity.main.CmsWorkflowRecord;

public interface CmsWorkflowRecordDao {
	public List<CmsWorkflowRecord> getList(Integer eventId,Integer userId);
	
	public Pagination getPage(int pageNo, int pageSize);

	public CmsWorkflowRecord findById(Integer id);

	public CmsWorkflowRecord save(CmsWorkflowRecord bean);

	public CmsWorkflowRecord updateByUpdater(Updater<CmsWorkflowRecord> updater);

	public CmsWorkflowRecord deleteById(Integer id);
}