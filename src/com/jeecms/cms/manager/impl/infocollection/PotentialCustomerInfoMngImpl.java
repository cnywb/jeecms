package com.jeecms.cms.manager.impl.infocollection;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.infocollection.PotentialCustomerInfoDao;
import com.jeecms.cms.entity.infocollection.PotentialCustomerInfo;
import com.jeecms.cms.entity.infocollection.PotentialCustomerInfoRequest;
import com.jeecms.cms.entity.infocollection.PotentialCustomerInfoResponse;
import com.jeecms.cms.manager.infocollection.PotentialCustomerInfoMng;
import com.jeecms.common.util.ValidateUtils;



@Service
@Transactional(rollbackFor=Exception.class) 
public class PotentialCustomerInfoMngImpl implements PotentialCustomerInfoMng {
	
	@Autowired
	private PotentialCustomerInfoDao potentialCustomerInfoDao;
	
	
	public PotentialCustomerInfoResponse add(PotentialCustomerInfoRequest request){
		PotentialCustomerInfoResponse retVal=new PotentialCustomerInfoResponse();
		List<PotentialCustomerInfo> infoList=request.getInfoList();
		for(PotentialCustomerInfo t:infoList){
			if(!ValidateUtils.isMobileNO(t.getCarOwnerMobilePhoneNo())){
				retVal.setMessage("保存失败：carOwnerMobilePhoneNo必须为正确的手机号！");
				retVal.setStatus(0);
				return retVal;
			}
			
			
			if(StringUtils.isEmpty(t.getCarModel())){
				retVal.setMessage("保存失败：carModel不能为空！");
				retVal.setStatus(0);
				return retVal;
			}
			if(StringUtils.isEmpty(t.getCarOwnerName())){
				retVal.setMessage("保存失败：carOwnerName不能为空！");
				retVal.setStatus(0);
				return retVal;
			}
			/*
			  if(!ValidateUtils.isMobileNO(t.getCustomerMobilePhoneNo())){
				retVal.setMessage("保存失败：customerMobilePhoneNo必须为正确的手机号！");
				retVal.setStatus(0);
				return retVal;
			}
			if(StringUtils.isEmpty(t.getCustomerName())){
				retVal.setMessage("保存失败：customerName不能为空！");
				retVal.setStatus(0);
				return retVal;
			}
			if(StringUtils.isEmpty(t.getCustomerProvince())){
				retVal.setMessage("保存失败：customerProvince不能为空！");
				retVal.setStatus(0);
				return retVal;
			}
			if(StringUtils.isEmpty(t.getCustomerCity())){
				retVal.setMessage("保存失败：customerCity不能为空！");
				retVal.setStatus(0);
				return retVal;
			}
			if(StringUtils.isEmpty(t.getIntentionalCarModel())){
				retVal.setMessage("保存失败：tntentionalCarModel不能为空！");
				retVal.setStatus(0);
				return retVal;
			}
			
			if(t.getIntentionalBuyDate()==null){
				retVal.setMessage("保存失败：intentionalBuyDate不能为空！");
				retVal.setStatus(0);
				return retVal;
			}
			*/
			if(StringUtils.isEmpty(t.getChannel())){
				retVal.setMessage("保存失败：channel不能为空！");
				retVal.setStatus(0);
				return retVal;
			}
			
		}
		for(PotentialCustomerInfo t:infoList){
			potentialCustomerInfoDao.add(t);
		}
		retVal.setMessage("保存成功!");
		retVal.setStatus(1);
		return retVal;
	}
	
	public PotentialCustomerInfoResponse addForCheckCustomerInfo(PotentialCustomerInfoRequest request){
		PotentialCustomerInfoResponse retVal=new PotentialCustomerInfoResponse();
		List<PotentialCustomerInfo> infoList=request.getInfoList();
		for(PotentialCustomerInfo t:infoList){
			 t.setCarOwnerName(t.getCustomerName());
			 t.setCarOwnerMobilePhoneNo(t.getCarOwnerMobilePhoneNo());
			if(!ValidateUtils.isMobileNO(t.getCustomerMobilePhoneNo())){
				retVal.setMessage("保存失败：customerMobilePhoneNo必须为正确的手机号！");
				retVal.setStatus(0);
				return retVal;
			}
			if(StringUtils.isEmpty(t.getCustomerName())){
				retVal.setMessage("保存失败：customerName不能为空！");
				retVal.setStatus(0);
				return retVal;
			}
			
			if(StringUtils.isEmpty(t.getChannel())){
				retVal.setMessage("保存失败：channel不能为空！");
				retVal.setStatus(0);
				return retVal;
			}
			
		}
		for(PotentialCustomerInfo t:infoList){
			potentialCustomerInfoDao.add(t);
		}
		retVal.setMessage("保存成功!");
		retVal.setStatus(1);
		return retVal;
	}
	

}
