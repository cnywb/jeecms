package com.jeecms.point.dao.activity.impl;

import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.point.dao.activity.ActivityDao;
import com.jeecms.point.entity.PointActivity;
import com.jeecms.point.web.query.QueryVo;

import java.util.Date;
import java.util.List;

/**
 * Created by kui.yang on 15/5/26.
 */
public class ActivityDaoImpl extends HibernateBaseDao<PointActivity,Long> implements ActivityDao {
    /**
     * 分页查询活动内容
     *
     * @param queryVo 查询条件
     * @return 活动记录
     */
    @Override
    public Pagination queryPagination(QueryVo queryVo) {
        String hql = "FROM PointActivity p";
        Finder finder = Finder.create(hql);
        Pagination pagination= this.find(finder, queryVo.getPage(), queryVo.getRows());
        return pagination;
    }

    @Override
    public void save(PointActivity activity) {
        this.getSession().saveOrUpdate(activity);
        if(activity.getCode()==null){
            String code = activity.getId()+"00";
            activity.setCode(code);
            this.getSession().saveOrUpdate(activity);
        }
    }

    @Override
    public void deleteById(Long id) {
        Object o = super.get(id);
        if(o!=null){
            this.getSession().delete(o);
        }
    }

    @Override
    public PointActivity getById(Long id) {
        return super.get(id);
    }

    @Override
    public PointActivity getByCode(String code) {
        String hql = "FROM PointActivity p where (1=1) ";
        Finder finder = Finder.create(hql);
        finder.append("and p.code=:code");
        finder.setParam("code", code);
        List<PointActivity> retval = this.find(finder);
        if(retval==null ||retval.isEmpty()){
            return null;
        }
        return retval.get(0);
    }

    /**
     * 获得Dao对于的实体类
     *
     * @return
     */
    @Override
    protected Class<PointActivity> getEntityClass() {
        return PointActivity.class;
    }
}
