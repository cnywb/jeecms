package com.jeecms.point.action.admin;

import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.point.entity.PointActivity;
import com.jeecms.point.manager.activity.ActivityMng;
import com.jeecms.point.vo.activity.ActivityVO;
import com.jeecms.point.web.json.JSONParser;
import com.jeecms.point.web.query.QueryVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by kui.yang on 15/5/24.
 * 活动管理，查询、添加、修改、删除活动
 */
@Controller
@RequestMapping("/admin/points/activity")
public class PointActivityAdminAct {
    /*首页*/
    private static final String INDEX_RETURN = "points/activity/index";
    /*详情*/
    private static final String INFO_RETURN = "points/activity/info";

    @Autowired
    private ActivityMng activityMng;

    @RequestMapping("/index.do")
    public String index(){
        return INDEX_RETURN;
    }
    /*** 查询列表信息 */
    @RequestMapping("/list.do")
    @ResponseBody
    public String list(QueryVo queryVo) {
        Pagination pagination =activityMng.queryPagination(queryVo);
        return JSONParser.toDataGridString(pagination, DateUtils.FORMAT_DATE_TIME_DEFAULT);
    }

    @RequestMapping("/info.do")
    public String info(Long id,ModelMap model){
        if(id!=null){
            PointActivity pointActivity=activityMng.getById(id);
            model.put("activity", pointActivity);
        }else{
            model.put("activity", new PointActivity());
        }
        return INFO_RETURN;
    }

    @RequestMapping("/delete.do")
    public String delete(Long id,ModelMap model,HttpServletRequest request){
        CmsSite site = CmsUtils.getSite(request);
        activityMng.deleteById(id);
        return "redirect:/jeeadmin/jeecms/admin/points/activity/index.do";
    }

    @RequestMapping("/save.do")
    public String save(Long id,ActivityVO activityVO,HttpServletRequest request){
        CmsSite site = CmsUtils.getSite(request);
        CmsUser currUser = com.jeecms.cms.web.CmsUtils.getUser(request);
        PointActivity activity = new PointActivity();
        if(activityVO!=null){
            if (activityVO.getId() != null) {
                activity.setId(activityVO.getId());
                activity.setUpdatedDate(new Date());
                if(currUser!=null){
                    activity.setUpdatedId(currUser.getId().longValue());
                }
            }else{
                activity.setCreatedDate(new Date());
                if(currUser!=null){
                    activity.setCreatedId(currUser.getId().longValue());
                }
            }
            if(activityVO.getName()!=null){
                activity.setName(activityVO.getName());
            }
            if(activityVO.getType()!=null){
                activity.setType(activityVO.getType());
            }
            if (activityVO.getStartTime() != null) {
                activity.setStartTime(DateUtils.parseDate(activityVO.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
            }
            if (activityVO.getEndTime() != null) {
                activity.setEndTime(DateUtils.parseDate(activityVO.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
            }
            if (StringUtils.isNotBlank(activityVO.getProbability1())) {
                activity.setProbability1(Integer.parseInt(activityVO.getProbability1()));
            }
            if (StringUtils.isNotBlank(activityVO.getProbability2())) {
                activity.setProbability2(Integer.parseInt(activityVO.getProbability2()));
            }
            if (activityVO.getCostPoints()!=null) {
                activity.setCostPoints(activityVO.getCostPoints());
            }
            if (activityVO.getLotteryLimitForDay() != null) {
                activity.setLotteryLimitForDay(activityVO.getLotteryLimitForDay());
            }
            if (activityVO.getKillStartTime() != null) {
                activity.setKillStartTime(activityVO.getKillStartTime());
            }
            if (activityVO.getKillEndTime() != null) {
                activity.setKillEndTime(activityVO.getKillEndTime());
            }
            if(activityVO.getKillOfWeek()!=null){
                activity.setKillOfWeek(activityVO.getKillOfWeek());
            }
            activity.setStatus(activityVO.isStatus());
            this.activityMng.save(activity);
        }
        return "redirect:/jeeadmin/jeecms/admin/points/activity/index.do";
    }

}
