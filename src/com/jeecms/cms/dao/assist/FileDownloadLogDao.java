package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.FileDownloadLog;


public interface FileDownloadLogDao {
	public Long add(FileDownloadLog t);
	public int  getTotalCountByFileIdAndCreaterId(long fileId,Integer createrId);
	public int  getTotalCountByFileIdAndCreaterId(long fileId,Integer createrId,String createTimeMin,String createTimeMax);

}
