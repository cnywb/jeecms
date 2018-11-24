package com.jeecms.cms.dao.main.impl.campaign.sfdj;

import java.util.Date;
import java.util.List;

import com.jeecms.cms.dao.campaign.sfdj.MemberStoryDao;
import com.jeecms.cms.entity.campaign.sfdj.MemberStory;

import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.common.util.ValidateUtils;

public class MemberStoryDaoImpl extends HibernateBaseDao<MemberStory, Long>
		implements MemberStoryDao {

	@Override
	protected Class<MemberStory> getEntityClass() {
		return MemberStory.class;
	}

	
	public long save(MemberStory t){
		getSession().save(t);
		return t.getId();
	}
	
	public int  update(MemberStory t){
		String sql="update MemberStory bean set bean.imageUrl=:imageUrl,bean.content=:content where bean.id=:id and bean.creater.id=:createrId and bean.status=:oldStatus";
		return getSession().createQuery(sql).setParameter("updateTime", new Date()).setParameter("imageUrl",t.getImageUrl()).setParameter("content", t.getContent()).setParameter("id",t.getId()).setParameter("createrId",t.getCreater().getId()).setParameter("oldStatus",0).executeUpdate();
	}
	
	
	public int  updatePraiseCount(Integer praiseCount,Long memberStoryId){
		String sql="update MemberStory bean set bean.praiseCount=:praiseCount where bean.id=:id";
		return getSession().createQuery(sql).setParameter("praiseCount",praiseCount).setParameter("id",memberStoryId).executeUpdate();
	}
	
	public int  updateStatus(MemberStory t){
		if(t.getCreater()==null){
			return 2;
		}else if(!t.getCreater().getUsername().equals("admin")){
			return 3;
		}
		String sql="update MemberStory bean set bean.status=:newStatus,bean.updateTime=:updateTime where bean.id=:id and bean.status=:oldStatus";
		return getSession().createQuery(sql).setParameter("updateTime", new Date()).setParameter("newStatus",t.getStatus()).setParameter("id",t.getId()).setParameter("oldStatus",0).executeUpdate();
	}
	
	/**
	 * 添加会员小故事
	 * @param t
	 * @return
	 */
	public int add(MemberStory t){
		Date limitDate=DateUtils.parseDate("2015-01-26 22:00:00", DateUtils.FORMAT_DATE_TIME_DEFAULT);
		Date currentDate=new Date();
		if(currentDate.getTime()>=limitDate.getTime()){
			return 777;
		}
		if(t.getCreater()==null){
			return 0;//用户为空
		}
		if(t.getCreater().getGroup().getId()!=2){
			return 1;//不是认证用户
		}
		List<MemberStory> list=findAllByCreaterId(t.getCreater().getId());
		if(list.size()>0){
			return 2;//已经存在记录
		}
		
		save(t);
		return 3;
	}
	
	public List<MemberStory>  findAllByCreaterId(int createrId){
		String hql = "select bean from MemberStory bean where bean.creater.id=? and (bean.status=0 or bean.status=1)";
		return find(hql,createrId);
	}
	
	public MemberStory findById(long id){
		String hql = "select bean from MemberStory bean where bean.id=?";
		List<MemberStory> list=find(hql,id);
		return list.size()>0?list.get(0):null;
	}
	
	public Pagination getPage(String status,String userName,String createTimeMin,String createTimeMax, int pageNo,int pageSize){
		Finder f = Finder.create("from MemberStory bean where 1=1");
		if(!ValidateUtils.isEmpty(userName)){
			f.append(" and bean.creater.username=:username");
			f.setParam("username",userName);
		}
		if(!ValidateUtils.isEmpty(status)){
			f.append(" and bean.status=:status");
			f.setParam("status",Integer.parseInt(status));
		}
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
		f.append(" order by bean.createTime desc,bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	public Pagination getPageForPass(int pageNo,int pageSize){
		Finder f = Finder.create("from MemberStory bean where 1=1");
	    f.append(" and bean.status=:status");
		f.setParam("status",1);
		f.append(" order by bean.praiseCount desc,bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	public int getTotalCountByCreaterId(Integer createrId){
		Finder f = Finder.create("from MemberStory bean where bean.creater.id=:createrId and bean.status=:status");
		f.setParam("createrId",createrId);
		f.setParam("status",1);
		return 	countQueryResult(f);
	}
}
