package com.yaozhitech.baobei.child.service.first;


import com.yaozhitech.baobei.child.domain.LeaderFeedbackComplaint;
import com.yaozhitech.baobei.child.dto.LeaderFeedbackDTO;

import java.util.List;
import java.util.Map;

/**
 * 领队负责人反馈表-Service接口类
 * @author jiangjialiang on 2018/11/22
 * @version 1.0.0
 */
public interface LeaderFeedbackService {

    Integer getLeaderFeedbackTotal(Map map);

    List<LeaderFeedbackDTO> getLeaderFeedbackList(Map map);

    void update(Integer id);

    Integer getLeaderFeedbackComplaintTotal(Map map);

    List<LeaderFeedbackComplaint> getLeaderFeedbackComplaintList(Map map);
}
