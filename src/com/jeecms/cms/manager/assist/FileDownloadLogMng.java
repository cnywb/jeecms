package com.jeecms.cms.manager.assist;

import javax.servlet.http.HttpServletRequest;

import com.jeecms.cms.entity.assist.FileDownloadLog;

public interface FileDownloadLogMng {
	
	public int add(FileDownloadLog t,HttpServletRequest request);

}
