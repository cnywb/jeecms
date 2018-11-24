package com.jeecms.cms.manager.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.CmsUserExtDao;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.CmsUserExt;
import com.jeecms.cms.manager.main.CmsUserExtMng;
import com.jeecms.common.hibernate3.Updater;

@Service
@Transactional
public class CmsUserExtMngImpl implements CmsUserExtMng {
	public CmsUserExt save(CmsUserExt ext, CmsUser user) {
		ext.blankToNull();
		ext.setUser(user);
		dao.save(ext);
		return ext;
	}

	public CmsUserExt update(CmsUserExt ext, CmsUser user) {
		CmsUserExt entity = dao.findById(user.getId());
		if (entity == null) {
			entity = save(ext, user);
			user.getUserExtSet().add(entity);
			return entity;
		} else {
			Updater<CmsUserExt> updater = new Updater<CmsUserExt>(ext);
			updater.include("gender");
			updater.include("birthday");
			
			ext = dao.updateByUpdater(updater);
			ext.blankToNull();
			return entity;
		}
	}

	public CmsUserExt updateCarInfo(CmsUserExt ext, CmsUser user) {
		CmsUserExt entity = dao.findById(user.getId());
		if (entity == null) {
			entity = save(ext, user);
			user.getUserExtSet().add(entity);
			return entity;
		} else {
			entity.setBuyingDealerProvince(ext.getBuyingDealerProvince());
			entity.setBuyingDealerCity(ext.getBuyingDealerCity());
			entity.setCarBuyingDealer(ext.getCarBuyingDealer());
			entity.setCarBuyingDate(ext.getCarBuyingDate());
			entity.setRepairDealerProvince(ext.getRepairDealerProvince());
			entity.setRepairDealerCity(ext.getRepairDealerCity());
			entity.setCarRepairDealer(ext.getCarRepairDealer());
			entity.setCarStyle(ext.getCarStyle());
			entity.setCarColor(ext.getCarColor());
			entity.setCarModel(ext.getCarModel());
			dao.update(entity);
			return entity;
		}
	}
	
	private CmsUserExtDao dao;

	@Autowired
	public void setDao(CmsUserExtDao dao) {
		this.dao = dao;
	}
}