package com.jeecms.cms.manager.main;

import java.util.Set;

import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.CmsWorkflowEvent;
import com.jeecms.cms.entity.main.CmsWorkflowEventUser;

public interface CmsWorkflowEventUserMng {
	
	public Set<CmsWorkflowEventUser> save(CmsWorkflowEvent event,Set<CmsUser>users);

	public Set<CmsWorkflowEventUser> update(CmsWorkflowEvent event,Set<CmsUser>users);
	
	public void deleteByEvent(Integer eventId);

}