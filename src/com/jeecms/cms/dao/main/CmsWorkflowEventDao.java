package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.cms.entity.main.CmsWorkflowEvent;

public interface CmsWorkflowEventDao {
	
	public List<CmsWorkflowEvent> getListByWorkFlowId(Integer workflowId);
	
	public List<CmsWorkflowEvent> getListByUserId(Integer userId);
	
	public Pagination getPage(int pageNo, int pageSize);

	public CmsWorkflowEvent findById(Integer id);
	
	public List<CmsWorkflowEvent> find(Integer dataTypeId, Integer dataId);

	public CmsWorkflowEvent save(CmsWorkflowEvent bean);

	public CmsWorkflowEvent updateByUpdater(Updater<CmsWorkflowEvent> updater);

	public CmsWorkflowEvent deleteById(Integer id);

}