package com.jeecms.point.dao.activity;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.PointActivity;
import com.jeecms.point.web.query.QueryVo;

/**
 * Created by kui.yang on 15/5/26.
 * 活动管理
 */
public interface ActivityDao {

    /**
     * 分页查询活动内容
     *
     * @param queryVo 查询条件
     * @return 活动记录
     */
    Pagination queryPagination(QueryVo queryVo);

    void save(PointActivity activity);

    void deleteById(Long id);

    PointActivity getById(Long id);

    PointActivity getByCode(String code);
}
