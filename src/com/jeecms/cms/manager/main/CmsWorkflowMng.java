package com.jeecms.cms.manager.main;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.CmsWorkflow;

public interface CmsWorkflowMng {
	public int check(CmsWorkflow workflow, CmsUser owner, CmsUser operator,
			Integer dateTypeId, Integer dataId, String opinion);

	public int reject(CmsWorkflow workflow, CmsUser owner, CmsUser operator,
			Integer dateTypeId, Integer dataId, String opinion);
	
	public Pagination getPage(Integer siteId, int pageNo, int pageSize);

	public List<CmsWorkflow> getList(Integer siteId, Boolean disabled);

	public CmsWorkflow findById(Integer id);

	public CmsWorkflow save(CmsWorkflow bean,Integer[] roleIds, Integer[] countersigns);

	public CmsWorkflow update(CmsWorkflow bean,Integer[] roleIds, Integer[] countersigns);

	public CmsWorkflow deleteById(Integer id);

	public CmsWorkflow[] deleteByIds(Integer[] ids);

	public void updatePriority(Integer[] ids, Integer[] priorities);
}