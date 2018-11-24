package com.jeecms.cms.manager.assist.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.assist.FileDownloadLogDao;
import com.jeecms.cms.entity.assist.FileDownloadLog;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.FordCar;
import com.jeecms.cms.entity.main.FordClubMember;
import com.jeecms.cms.manager.assist.FileDownloadLogMng;
import com.jeecms.cms.manager.main.FordCarMng;
import com.jeecms.cms.manager.main.FordClubMemberMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.common.util.DateUtils;




@Service
@Transactional(rollbackFor=Exception.class) 
public class FileDownloadLogMngImpl implements FileDownloadLogMng {
	
	@Autowired
	private FileDownloadLogDao fileDownloadLogDao;
	
	@Autowired
	private FordClubMemberMng fordClubMemberMng;
	
	@Autowired
	private FordCarMng  fordCarMng;
	
	public int add(FileDownloadLog t,HttpServletRequest request){
  		    CmsUser user = CmsUtils.getUser(request);
  			if(t.getFileId()==null){
				return 8;
			}
			if(user==null){
				return 0;
			}
			if(user.getGroup().getId()!=2){
				return 1;
			}
			List<FordClubMember> list=fordClubMemberMng.getFordClubMemberByUid(user.getId());
			if(list.size()==0){
				return 2;
			}
			
			boolean canDownload=false;
			int temRetVal=0;
			for(FordClubMember fordClubMember:list){//一个用户可能认证多辆车
				int retVal=checkCarAndLimitTime(fordClubMember);
				if(retVal==9){
					canDownload=true;
				}
				if(retVal!=9){
					temRetVal=retVal;
				}
			}
			if(canDownload==false){
				 return temRetVal;
			}
		
			/*int downloadTotalCount=fileDownloadLogDao.getTotalCountByFileIdAndCreaterId(t.getFileId(), user.getId());
			
			if(downloadTotalCount>=2){
				return 6;
			}*/
		    t.setCreater(user);
			fileDownloadLogDao.add(t);
			return 7;
	}
	
	private int checkCarAndLimitTime(FordClubMember fordClubMember){
		List<FordCar> fordCarList=fordCarMng.findByVin(fordClubMember.getVvin());
		if(fordCarList.size()==0){
			return 3;
		}
		FordCar fordCar=fordCarList.get(0);
		if(fordCar.getVpurchasedDate()==null||"".equals(fordCar.getVpurchasedDate())){
			return 4;
		}
		Long offset=15L*30L*24L*60L*60L*1000L;//15个月
		Date currentDate=new Date();
		Date purchasedDate=DateUtils.parseDate(fordCar.getVpurchasedDate(), DateUtils.FORMAT_DATE_DEFAULT);
		if((currentDate.getTime()-purchasedDate.getTime())>=offset){
			return 5;
		}
		return 9;
	}

}
