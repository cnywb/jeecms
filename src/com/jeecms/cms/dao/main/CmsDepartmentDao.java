package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.CmsDepartment;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface CmsDepartmentDao {

	public List<CmsDepartment> getList(Integer parentId,boolean all);

	public Pagination getPage(String name, int pageNo,int pageSize);

	public CmsDepartment findById(Integer id);

	public CmsDepartment findByName(String name);

	public CmsDepartment save(CmsDepartment bean);

	public CmsDepartment deleteById(Integer id);

	public CmsDepartment updateByUpdater(Updater<CmsDepartment> updater);

}