/**
 * 
 */
package com.jeecms.point.manager.point.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.point.manager.point.PointUserMng;

/**
 * 积分实现类
 * @author wanglijun
 *
 */
@Service
@Transactional
public class PointUserMngImpl  implements PointUserMng{
	
	@Autowired
	private UnifiedUserMng  unifiedUserMng;
	

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointUserMng#getUser(java.lang.Integer)
	 */
	@Override
	public UnifiedUser getUser(Integer id){
		return unifiedUserMng.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointUserMng#getUserPointById(java.lang.Integer)
	 */
	@Override
	public long getUserPointById(Integer id) {
		UnifiedUser unifiedUser=this.getUser(id);
		if(unifiedUser!=null){
			unifiedUser.getCurrentPoint();
		}		
		return 0L;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointUserMng#updateUserPoint(java.lang.Integer, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public boolean updateUserPoint(Integer userId,Long productPointNum) {
		UnifiedUser unifiedUser=this.unifiedUserMng.lock(userId);
		if(unifiedUser==null){
			return false;
		}
		if(productPointNum>0&&unifiedUser.getCurrentPoint()>=productPointNum){
			long currentPoint=unifiedUser.getCurrentPoint();
			unifiedUser=this.unifiedUserMng.subtractionPoint(unifiedUser,productPointNum);
			if(unifiedUser!=null&&currentPoint>unifiedUser.getCurrentPoint()){
				this.unifiedUserMng.unLock(userId);
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointUserMng#findByOpenId(java.lang.String)
	 */
	@Override
	public UnifiedUser findByOpenId(String openId) {
		return this.unifiedUserMng.findByOpenId(openId);
	}
	
	
	
}
