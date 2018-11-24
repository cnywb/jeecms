package com.jeecms.bbs.manager.member.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.jeecms.bbs.action.member.vo.CarFocusType;
import com.jeecms.bbs.dao.member.MemberCarFocusDao;
import com.jeecms.bbs.entity.BbsMemberCarFocus;
import com.jeecms.bbs.manager.member.BbsMemberCarFocusMng;

@Service
@Transactional
public class BbsMemberCarFocusMngImpl implements BbsMemberCarFocusMng {

	private final Logger logger = LoggerFactory.getLogger(super.getClass());

	@Autowired
	private MemberCarFocusDao memberCarFocusDao;

	private static Map<String, String> focusCarTypeParams = new HashMap<String, String>();

	static {
		focusCarTypeParams.put("A", "福克斯");
		focusCarTypeParams.put("B", "蒙迪欧");
		focusCarTypeParams.put("C", "嘉年华");
		focusCarTypeParams.put("D", "麦柯斯");
		focusCarTypeParams.put("E", "翼搏");
		focusCarTypeParams.put("F", "翼虎");
	}

	@Autowired
	public void setMemberCarFocusDao(MemberCarFocusDao memberCarFocusDao) {
		this.memberCarFocusDao = memberCarFocusDao;
	}

	@Override
	public BbsMemberCarFocus save(BbsMemberCarFocus memberCarFocus) {
		return this.memberCarFocusDao.save(memberCarFocus);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BbsMemberCarFocus> queryById(Integer uuid) {
		return this.memberCarFocusDao.queryById(uuid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.bbs.manager.member.BbsMemberCarFocusMng#queryByUserId(java
	 * .lang.Integer)
	 */
	@Override
	public List<CarFocusType> queryByUserId(Integer uuid) {
		List<BbsMemberCarFocus> bbsMemberCarFocus = this.queryById(uuid);
		List<CarFocusType> carFocusTypes = new ArrayList<CarFocusType>();
		for (BbsMemberCarFocus carFocus : bbsMemberCarFocus) {
			CarFocusType carFocusType = new CarFocusType();
			carFocusType.setId(carFocus.getId());
			carFocusType.setKey(carFocus.getCarType());
			carFocusType.setName(this.focusCarTypeParams.get(carFocus.getCarType()));
			carFocusTypes.add(carFocusType);
		}
		return carFocusTypes;
	}

	public List<BbsMemberCarFocus> save(String[] carTypes, Integer uuid) {
		if (carTypes == null) {
			return null;
		}
		List<BbsMemberCarFocus> list = new ArrayList<BbsMemberCarFocus>();
		for (String str : carTypes) {
			BbsMemberCarFocus carFocus = new BbsMemberCarFocus();
			carFocus.setCarType(str);
			carFocus.setUuid(uuid);
			carFocus.setUpdateUser(uuid);
			carFocus.setUpdateDate(new Date());
			carFocus.setCreateDate(new Date());
			carFocus.setCreateUser(uuid);
			list.add(carFocus);

		}
		if (org.springframework.util.CollectionUtils.isEmpty(list)) {
			return null;
		}
		list = this.saveObjects(list);
		return list;
	}

	public List<BbsMemberCarFocus> saveObjects(List<BbsMemberCarFocus> list) {
		List<BbsMemberCarFocus> listCarFocus = new ArrayList<BbsMemberCarFocus>();
		for (BbsMemberCarFocus carFocus : list) {
			carFocus = this.save(carFocus);
			listCarFocus.add(carFocus);
		}
		return listCarFocus;
	}

	@Override
	public List<CarFocusType> queryFocusFullCarType() {
		List<CarFocusType> list = new ArrayList<CarFocusType>();

		for (String key : focusCarTypeParams.keySet()) {
			CarFocusType focusType = new CarFocusType();
			focusType.setKey(key);
			focusType.setName(focusCarTypeParams.get(key));
			list.add(focusType);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.bbs.manager.member.BbsMemberCarFocusMng#queryFocusFullCarType
	 * (java.util.List)
	 */
	@Override
	public List<CarFocusType> queryFocusFullCarType(List<CarFocusType> userCarFocusTypes) {
		List<CarFocusType> list = new ArrayList<CarFocusType>();
		for (String key : focusCarTypeParams.keySet()) {
			CarFocusType focusType = new CarFocusType();
			focusType.setKey(key);
			focusType.setName(focusCarTypeParams.get(key));
			if(!CollectionUtils.isEmpty(userCarFocusTypes)){
				for(CarFocusType carFocusType:userCarFocusTypes){
					if(key.equals(carFocusType.getKey())){
						focusType.setDisabled(Boolean.TRUE);
						break;
					}
				}
			}
			list.add(focusType);
		}
		return list;
	}

	@Override
	public void deleteById(Long id) {
		this.memberCarFocusDao.deleteById(id);
	}

}
