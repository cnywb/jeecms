package com.jeecms.bbs.manager;

import java.util.List;

import com.jeecms.bbs.entity.BbsTopicDraft;
import com.jeecms.common.page.Pagination;

public interface BbsTopicDraftMng {
	public BbsTopicDraft save(BbsTopicDraft t);
    public List<BbsTopicDraft>  findAllByUserId(int userId);
    public int deleteByIdAndUserId(long id,int userId);
    public int deleteByUserId(int userId);
    public int  getTotalCountByUserId(Integer userId);
    public Pagination getPage( int pageNo,int pageSize);
}
