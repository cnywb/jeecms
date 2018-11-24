package com.jeecms.cms.manager.main.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.FordRepairWebDao;
import com.jeecms.cms.entity.main.ClubDictionary;
import com.jeecms.cms.entity.main.FordCar;
import com.jeecms.cms.entity.main.FordClubMember;
import com.jeecms.cms.entity.main.FordRepairWeb;
import com.jeecms.cms.entity.main.TempDeal;
import com.jeecms.cms.manager.main.ClubDictionaryMng;
import com.jeecms.cms.manager.main.FordCarMng;
import com.jeecms.cms.manager.main.FordClubMemberMng;
import com.jeecms.cms.manager.main.FordRepairWebMng;
import com.jeecms.cms.manager.main.TempDealMng;
import com.jeecms.cms.vo.CardScoreInfoVo;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class FordRepairWebMngImpl implements FordRepairWebMng {
	@Autowired
	private FordRepairWebDao dao;
	@Autowired
	private FordClubMemberMng memberMng;
	@Autowired
	private FordCarMng carMng;
	@Autowired
	private TempDealMng dealMng;
	@Autowired
	private ClubDictionaryMng dictionaryMng;

	@Override
	public Pagination getFordRepairWebByUserId(Integer userId, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return this.dao.getFordRepairWebByUserId(userId, pageNo, pageSize);
	}

	@Override
	public List<FordRepairWeb> getFordRepairWebByUserId(Integer userId,
			int count) {
		// TODO Auto-generated method stub
		return this.dao.getFordRepairWebByUserId(userId, count);
	}

	@Override
	public List<FordRepairWeb> getFordRepairWebByCardId(String vvin, int count) {
		// TODO Auto-generated method stub
		return this.dao.getFordRepairWebByCardId(vvin, count);
	}

	@Override
	public List<CardScoreInfoVo> getCardScoreInfoVoBy(Integer userId,
			int repairCount, int dealCount) {
		// 获得俱乐部会员和车辆信息
		List<FordClubMember> members = memberMng.getFordClubMemberByUid(userId);
		if (members != null && members.size() > 0) {
			List<CardScoreInfoVo> list = new ArrayList<CardScoreInfoVo>();
			for (int i = 0; i < members.size(); i++) {
				FordClubMember member = members.get(i);
				CardScoreInfoVo vo = new CardScoreInfoVo();
				vo.setCount(i + 1);
				vo.setCardId(member.getVcardId());
				vo.setVin(member.getVvin());
				// 获得车辆信息
				// FordCar car = carMng.findById(member.getVcarId());
				FordCar car = carMng.findByVinOfOne(member.getVvin());
				if (car != null) {
					if (StringUtils.isNotBlank(car.getVmodel())) {
						ClubDictionary c = dictionaryMng
								.getClubDictionaryByCode(car.getVmodel());
						if (c != null)
							vo.setCarType(c.getCdname());
					}
					vo.setDealerName(car.getVdealerName());
					vo.setLicenseNumber(car.getVlicenseNmb());
					if (StringUtils.isNotBlank(car.getVcolor())) {
						ClubDictionary c = dictionaryMng
								.getClubDictionaryByCode(car.getVcolor());
						if (c != null)
							vo.setCarColor(c.getCdname());
						else
							vo.setCarColor("无颜色");
					} else
						vo.setCarColor("无颜色");
				}

				// 维修工单
				List<FordRepairWeb> repairs = this.dao
						.getFordRepairWebByCardId(member.getVvin(), repairCount);
				vo.setRepairs(repairs);
				// 电子对账单
				if (dealCount > 0) {
					List<TempDeal> deals = dealMng.getTempDealsByVin(
							member.getVvin(), dealCount);
					vo.setTempDeals(deals);
				}
				list.add(vo);
			}
			return list;
		}
		return null;
	}
}
