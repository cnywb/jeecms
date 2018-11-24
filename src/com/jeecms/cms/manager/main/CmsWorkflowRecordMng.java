package com.jeecms.cms.manager.main;

import java.util.Date;
import java.util.List;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.CmsWorkflowEvent;
import com.jeecms.cms.entity.main.CmsWorkflowRecord;

public interface CmsWorkflowRecordMng {
	
	public List<CmsWorkflowRecord> getList(Integer eventId,Integer userId);

	public CmsWorkflowRecord save(CmsSite site, CmsWorkflowEvent event,
			CmsUser user, String opinion,Date recordTime, Integer type);

}