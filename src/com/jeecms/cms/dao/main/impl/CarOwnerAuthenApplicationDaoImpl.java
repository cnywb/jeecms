package com.jeecms.cms.dao.main.impl;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.CarOwnerAuthenApplicationDao;
import com.jeecms.cms.entity.main.CarOwnerAuthenApplication;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.common.util.ValidateUtils;
@Repository
public class CarOwnerAuthenApplicationDaoImpl extends HibernateBaseDao<CarOwnerAuthenApplication, Long>implements
		CarOwnerAuthenApplicationDao {

	@Override
	protected Class<CarOwnerAuthenApplication> getEntityClass() {
		return CarOwnerAuthenApplication.class;
	}
	
	public void save(CarOwnerAuthenApplication t){
		getSession().save(t);
	}
	
	public void update(CarOwnerAuthenApplication t){
		getSession().update(t);
	}
	
	//查找当前正在申请的vin
	public List<CarOwnerAuthenApplication>  findCarOwnerAuthenApplicationInProcess(String vin){
		String hql = "select bean from CarOwnerAuthenApplication bean where bean.vvin=? and bean.status=?";
		return find(hql,vin,0);
	}

	//查找当前用户下暂存的认证申请
	public List<CarOwnerAuthenApplication>  findCarOwnerAuthenApplicationInSave(int createrId){
		String hql = "select bean from CarOwnerAuthenApplication bean where bean.status=? and bean.creater.id=?";
		return find(hql,3,createrId);
	}
	public CarOwnerAuthenApplication findByEntityId(long id){
		return super.get(id);
	}
	
	public Pagination getPage(String status,String name,String mobile,String vin,String createTimeMin,String createTimeMax,String updateTimeMin,String updateTimeMax, int pageNo,
			int pageSize){
		Finder f = Finder.create("from CarOwnerAuthenApplication bean where 1=1");
		if(!ValidateUtils.isEmpty(name)){
			f.append(" and bean.vname=:vname");
			f.setParam("vname",name);
		}
		if(!ValidateUtils.isEmpty(mobile)){
			f.append(" and bean.vmobile=:vmobile");
			f.setParam("vmobile",mobile);
		}
		if(!ValidateUtils.isEmpty(vin)){
			f.append(" and bean.vvin=:vvin");
			f.setParam("vvin",vin);
		}
		
		if(!ValidateUtils.isEmpty(status)){
			f.append(" and bean.status=:status");
			f.setParam("status",Integer.parseInt(status));
		}else{
			f.append(" and bean.status!=:status");
			f.setParam("status",4);
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
		if(!ValidateUtils.isEmpty(updateTimeMin)){
			updateTimeMin=updateTimeMin+" 00:00:00";
			Date minTime=DateUtils.parseDate(updateTimeMin, DateUtils.FORMAT_DATE_TIME_DEFAULT);
			f.append(" and bean.updateTime>=:minTime");
			f.setParam("minTime",minTime);
		}
		if(!ValidateUtils.isEmpty(updateTimeMax)){
			updateTimeMax=updateTimeMax+" 23:59:59";
			Date maxTime=DateUtils.parseDate(updateTimeMax, DateUtils.FORMAT_DATE_TIME_DEFAULT);
			f.append(" and bean.updateTime<=:maxTime");
			f.setParam("maxTime",maxTime);
		}
		f.append(" order by bean.createTime desc");
		return find(f, pageNo, pageSize);
	}
	public CarOwnerAuthenApplication  findCarOwnerAuthenApplicationTempInfo(int createId){
		String hql = "select bean from CarOwnerAuthenApplication bean where bean.creater.id=? and bean.status=?";
		List<CarOwnerAuthenApplication> list=find(hql,createId,3);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public int updateCarOwnerAuthenApplicationTempInfo(int createId,long id){
		String sql="update CarOwnerAuthenApplication bean set bean.status=:newStatus where bean.id=:id and bean.creater.id=:createId and bean.status=:oldStatus";
		return getSession().createQuery(sql).setParameter("newStatus",4).setParameter("id",id).setParameter("createId",createId).setParameter("oldStatus",3).executeUpdate();
	}
	
}
