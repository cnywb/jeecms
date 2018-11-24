package com.jeecms.cms.manager.main.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jeecms.cms.dao.main.CarOwnerAuthenApplicationDao;
import com.jeecms.cms.entity.main.CarOwnerAuthenApplication;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.FordClubMember;
import com.jeecms.cms.manager.main.CarOwnerAuthenApplicationMng;
import com.jeecms.cms.manager.main.FordClubMemberMng;
import com.jeecms.cms.manager.main.FordMemberFormMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.ValidateUtils;
import com.jeecms.point.manager.point.PointCalculateMng;


@Service
@Transactional(rollbackFor=Exception.class) 
public class CarOwnerAuthenApplicationMngImpl implements
		CarOwnerAuthenApplicationMng {
	@Autowired
	private CarOwnerAuthenApplicationDao carOwnerAuthenApplicationDao;
	
	@Autowired
	private FordClubMemberMng clubMemberMng;
	@Autowired
	private FordMemberFormMng fordMemberFormMng;
	
	@Autowired
	private PointCalculateMng pointCalculateMng;
	
    public int add(CmsUser user,CarOwnerAuthenApplication carOwnerAuthenApplication){
       	FordClubMember clubMember = clubMemberMng.getFordClubMemberByVin(carOwnerAuthenApplication.getVvin());
		if(clubMember!= null){	
		   return 0;//该车已经被认证过
		}
		int validateResult=validateSubmitData(carOwnerAuthenApplication);
		if(validateResult!=10){
			return validateResult;
		}
		List<CarOwnerAuthenApplication> list=carOwnerAuthenApplicationDao.findCarOwnerAuthenApplicationInProcess(carOwnerAuthenApplication.getVvin());
		if(list.size()>0){
			return 1;//已经存在申请中的vin
		}
   		carOwnerAuthenApplication.setCreater(user);
   		carOwnerAuthenApplication.setStatus(0);
   		if(carOwnerAuthenApplication.getVname().length()>30){
   			carOwnerAuthenApplication.setVname(carOwnerAuthenApplication.getVname().substring(0,30));
   		}
	    carOwnerAuthenApplicationDao.save(carOwnerAuthenApplication);
       	return 2;//提交申请成功
    }
    
  
    
    public int add(CmsUser user,String vvin, String vname,String vmobile, String certImageUrl){
    	CarOwnerAuthenApplication t=new CarOwnerAuthenApplication(vvin,  vname,vmobile, certImageUrl);
    	return add(user, t);
    }
    
  
    
    /**
     * 校验参数
     * @param t
     * @return
     */
    private int validateSubmitData(CarOwnerAuthenApplication t){
    	if(ValidateUtils.isEmpty(t.getVname())){
    		return 4;
    	}
    	if(ValidateUtils.isEmpty(t.getVmobile())){
    		return 5;
    	}
    	if(ValidateUtils.isEmpty(t.getCertImageUrl())){
    		return 6;
    	}
    	if(ValidateUtils.isEmpty(t.getVvin())){
    		return 7;
    	}
    	if(!ValidateUtils.isMobileNO(t.getVmobile())){
    		return 8;
    	}
       	return 10;
    }
    
    private int validateSubmitTempInfo(CarOwnerAuthenApplication t){
    	if(ValidateUtils.isEmpty(t.getVname())){
    		return 4;
    	}
    	if(ValidateUtils.isEmpty(t.getVmobile())){
    		return 5;
    	}
      	if(ValidateUtils.isEmpty(t.getVvin())){
    		return 7;
    	}
    	if(!ValidateUtils.isMobileNO(t.getVmobile())){
    		return 8;
    	}
       	return 10;
    }
    
     public Pagination getPage(String status,String name,String mobile,String vin,String createTimeMin,String createTimeMax,String updateTimeMin,String updateTimeMax, int pageNo,
			int pageSize){
    	return carOwnerAuthenApplicationDao.getPage(status,name, mobile, vin, createTimeMin, createTimeMax, updateTimeMin, updateTimeMax, pageNo, pageSize);
    }
     
     public CarOwnerAuthenApplication findById(long id){
       	 return carOwnerAuthenApplicationDao.findByEntityId(id);
     }
     
     public int authenPass(long id){
    	 CarOwnerAuthenApplication t=carOwnerAuthenApplicationDao.findByEntityId(id);
    	 if(t==null){
    		 return 0;
    	 }
    	 if(t.getStatus()!=0){
    		 return 1;
    	 }
       	 int result=fordMemberFormMng.authenManualy(t.getCreater(), t.getVname(),t.getVmobile(),t.getVvin());
    	 if(result==4){
    		t.setStatus(1);
    		pointCalculateMng.authUserPoint(t.getCreater().getId());
       	 }else{
    		t.setStatus(2);
    	 }
    	 t.setUpdateTime(new Date());
    	 carOwnerAuthenApplicationDao.update(t);
    	 return result;
     }
     
     public int authenUnPass(long id,String memo){
    	 CarOwnerAuthenApplication t=carOwnerAuthenApplicationDao.findByEntityId(id);
    	 if(t==null){
    		 return 0;
    	 }
    	 if(t.getStatus()!=0){
    		 return 1;
    	 }
    	 t.setStatus(2);
    	 t.setUpdateTime(new Date());
    	 carOwnerAuthenApplicationDao.update(t);
       	 return 3;
     }


     public int saveTempInfo(CmsUser user,CarOwnerAuthenApplication carOwnerAuthenApplication){
 		int validateResult=validateSubmitTempInfo(carOwnerAuthenApplication);
 		if(validateResult!=10){
 			return validateResult;
 		}
 		List<CarOwnerAuthenApplication> list=carOwnerAuthenApplicationDao.findCarOwnerAuthenApplicationInProcess(carOwnerAuthenApplication.getVvin());
 		if(list.size()>0){
 			return 1;//已经存在申请中的vin
 		}
 		list=carOwnerAuthenApplicationDao.findCarOwnerAuthenApplicationInSave(user.getId());
 		if(list.size()>0){
 			return 2;//已经存在暂存中的申请
 		}
 		
    		carOwnerAuthenApplication.setCreater(user);
    		carOwnerAuthenApplication.setStatus(0);
    		if(carOwnerAuthenApplication.getVname().length()>30){
    			carOwnerAuthenApplication.setVname(carOwnerAuthenApplication.getVname().substring(0,30));
    		}
    		carOwnerAuthenApplication.setCertImageUrl("no image");
    		carOwnerAuthenApplication.setStatus(3);//暂存状态
 	        carOwnerAuthenApplicationDao.save(carOwnerAuthenApplication);
        	return 3;//保存成功
     }
     
     public int saveTempInfo(CmsUser user,String vvin, String vname,String vmobile){
     	CarOwnerAuthenApplication t=new CarOwnerAuthenApplication(vvin,  vname,vmobile);
     	return saveTempInfo(user, t);
     }



	@Override
	public CarOwnerAuthenApplication findCarOwnerAuthenApplicationTempInfo(
			int createId) {
		return carOwnerAuthenApplicationDao.findCarOwnerAuthenApplicationTempInfo(createId);
	}
	@Override
	public int updateCarOwnerAuthenApplicationTempInfo(int createId, long id) {
		// TODO Auto-generated method stub
		return carOwnerAuthenApplicationDao.updateCarOwnerAuthenApplicationTempInfo(createId, id);
	}
	
}
