package com.yaozhitech.baobei.child.web;

import com.alibaba.fastjson.JSONObject;
import com.yaozhitech.baobei.child.constants.Constants;
import com.yaozhitech.baobei.child.domain.LeaderFeedbackComplaint;
import com.yaozhitech.baobei.child.domain.UserArea;
import com.yaozhitech.baobei.child.dto.LeaderFeedbackDTO;
import com.yaozhitech.baobei.child.service.first.LeaderFeedbackService;
import com.yaozhitech.baobei.child.utils.DateUtil;
import com.yaozhitech.baobei.child.utils.ExcelMergeUtil;
import com.yaozhitech.baobei.child.utils.Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 领队负责人反馈表-Controller类
 * @author jiangjialiang on 2018/11/22
 * @version 1.0.0
 */
@Controller
@RequestMapping("/leader/feedback/")
public class LeaderFeedbackController {

    Logger logger = Logger.getLogger(LeaderFeedbackController.class);


    @Autowired
    private LeaderFeedbackService leaderFeedbackService;

    /**
     * 跳转"负责人反馈表"页面
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="to.do")
    public String leader(HttpServletRequest request){
        return "manage/list/leader_feedback_list";
    }

    /**
     * ajax获取分页总记录数
     * @param name
     * @param response
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="ajax_leader_feedback_total.do")
    public void getLeaderFeedbackTotal(String city, String name, String nickname, String remark, String startTime, String endTime, Integer parms, HttpServletResponse response, HttpServletRequest request){
        Integer totalCount = 0 ;
        try {
            Map map = new HashMap();
            List<UserArea> userAreas = (List<UserArea>) request.getSession().getAttribute("managerUserAreas");
            map.put("userAreas", userAreas);
            map.put("city", city);
            map.put("title", name);
            map.put("nickname", nickname);
            map.put("remark", remark);
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            map.put("parms", parms);
            totalCount = leaderFeedbackService.getLeaderFeedbackTotal(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        jsonAjax(response, totalCount+"");
    }

    /**
     * ajax获取负责人反馈列表
     * @param name
     * @param pageIndex
     * @param pageSize
     * @param request
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value="ajax_leader_feedback_list.do")
    public String getLeaderFeedbackList(String city, String name, String nickname, String remark, String startTime, String endTime, Integer parms, Integer pageIndex, Integer pageSize, HttpServletRequest request){

        try {
            Map map = new HashMap();
            List<UserArea> userAreas = (List<UserArea>) request.getSession().getAttribute("managerUserAreas");
            map.put("userAreas", userAreas);
            map.put("city", city);
            map.put("title", name);
            map.put("nickname", nickname);
            map.put("remark", remark);
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            map.put("parms", parms);
            pageIndex = Util.isNotNull(pageIndex) && pageIndex > 0 ? pageIndex : 1;
            pageSize = Util.isNotNull(pageSize) && pageSize < 100 ? pageSize : Constants.DEFAULT_PAGE_SIZE;
            map.put("pageSize", pageSize);
            map.put("pageIndex", (pageIndex - 1) * pageSize);

            List<LeaderFeedbackDTO> listData = leaderFeedbackService.getLeaderFeedbackList(map);
            request.setAttribute("listData", listData);
            request.setAttribute("pageIndex", pageIndex);
        } catch (Exception e) {
            logger.error("ajax_leader_feedback_list.do:"+e.getMessage());
        }
        return "manage/ajax/leader_feedback_list";
    }

    /**
     * 删除领队(用户类型改为普通用户)
     * @param id
     * @param response
     */
    @RequestMapping(value="leader_feedback_delete.do")
    public void delete(Integer id, HttpServletResponse response){
        JSONObject result = new JSONObject();
        result.put("code", Constants.CODE_FAIL);
        result.put("msg", Constants.MSG_FAIL);
        try {
            leaderFeedbackService.update(id);
            result.put("code", Constants.CODE_SUCCESS);
            result.put("msg", Constants.MSG_SUCCESS);
        } catch (Exception e) {
            logger.error("leader_feedback_delete.do error:" + e.getMessage());
        }
        jsonAjax(response, result.toString());
    }

    /**
     * ajax获取投诉情况分页总记录数
     * @param fid
     * @param response
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="ajax_leader_feedback_complaint_total.do")
    public void getLeaderFeedbackComplaintTotal(Integer fid, HttpServletResponse response, HttpServletRequest request){
        Integer totalCount = 0 ;
        try {
            Map map = new HashMap();
            map.put("fid", fid);
            totalCount = leaderFeedbackService.getLeaderFeedbackComplaintTotal(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        jsonAjax(response, totalCount+"");
    }

    /**
     * ajax获取投诉情况列表
     * @param fid
     * @param pageIndex
     * @param pageSize
     * @param request
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value="ajax_leader_feedback_complaint_list.do")
    public String getLeaderFeedbackComplaintList(Integer fid, Integer pageIndex, Integer pageSize, HttpServletRequest request){
        try {
            Map map = new HashMap();
            map.put("fid", fid);
            pageIndex = Util.isNotNull(pageIndex) && pageIndex > 0 ? pageIndex : 1;
            pageSize = Util.isNotNull(pageSize) && pageSize < 100 ? pageSize : Constants.DEFAULT_PAGE_SIZE;
            map.put("pageSize", pageSize);
            map.put("pageIndex", (pageIndex - 1) * pageSize);

            List<LeaderFeedbackComplaint> listData = leaderFeedbackService.getLeaderFeedbackComplaintList(map);
            request.setAttribute("listData", listData);
            request.setAttribute("pageIndex", pageIndex);
        } catch (Exception e) {
            logger.error("ajax_leader_feedback_complaint_list.do:"+e.getMessage());
        }
        return "manage/ajax/leader_feedback_complaint_list";
    }

    /**
     * 导出反馈表excel
     * @param city
     * @param name
     * @param nickname
     * @param remark
     * @param parms
     * @param request
     * @param response
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/leader_feedback_export.do")
    public void leaderFeedbackExport(String city, String name, String nickname, String remark, String startTime, String endTime, Integer parms, HttpServletRequest request, HttpServletResponse response) {
        Map map = new HashMap();
        List<UserArea> userAreas = (List<UserArea>) request.getSession().getAttribute("managerUserAreas");
        map.put("userAreas", userAreas);
        map.put("city", city);
        map.put("title", name);
        map.put("nickname", nickname);
        map.put("remark", remark);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("parms", parms);
        List<LeaderFeedbackDTO> listData = leaderFeedbackService.getLeaderFeedbackList(map);
        String[] td = {"城市", "活动名称", "场次名称", "负责人名称", "提交人", "提交时间", "签到表", "领队大合照", "保单发群图"
                , "使用喔图", "投诉海报发群", "大巴资质", "资质截图", "事故投诉-儿童", "事故投诉-描述", "事故投诉-受伤照片"};
        Integer[] mergeCells = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        String fileName = "负责反馈表" + DateUtil.date2Str(new Date(), "yyyyMMddHHmmss") + ".xlsx";
        ExcelMergeUtil.feedbackExelMerge(fileName, td, listData, mergeCells, request, response);
    }

    private void jsonAjax(HttpServletResponse response, String outString) {
        PrintWriter out = null;
        response.setHeader("content-type", "application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            out = response.getWriter();
            out.write(outString);

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

}
