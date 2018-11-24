package com.jeecms.cms.dao.assist.impl;

import java.util.Date;

import com.jeecms.cms.dao.assist.FileDownloadLogDao;
import com.jeecms.cms.entity.assist.FileDownloadLog;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.util.DateUtils;
import com.jeecms.common.util.ValidateUtils;

public class FileDownloadLogDaoImpl extends
HibernateBaseDao<FileDownloadLog, Integer> implements FileDownloadLogDao {

	@Override
	protected Class<FileDownloadLog> getEntityClass() {
		// TODO Auto-generated method stub
		return FileDownloadLog.class;
	}
	
	
	public Long add(FileDownloadLog t){
		getSession().save(t);
		return t.getId();
	}
	
	
	public int  getTotalCountByFileIdAndCreaterId(long fileId,Integer createrId){
		Finder f = Finder.create("from FileDownloadLog bean where bean.fileId=:fileId and bean.creater.id=:createrId");
		f.setParam("fileId",fileId);
		f.setParam("createrId",createrId);
		return 	countQueryResult(f);
	}
	
	public int  getTotalCountByFileIdAndCreaterId(long fileId,Integer createrId,String createTimeMin,String createTimeMax){
		Finder f = Finder.create("from FileDownloadLog bean where bean.fileId=:fileId and bean.creater.id=:createrId");
		f.setParam("fileId",fileId);
		f.setParam("createrId",createrId);
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
		return 	countQueryResult(f);
	}

}
