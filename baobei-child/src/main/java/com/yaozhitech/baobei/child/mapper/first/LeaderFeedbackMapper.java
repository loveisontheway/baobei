package com.yaozhitech.baobei.child.mapper.first;


import com.yaozhitech.baobei.child.domain.LeaderFeedbackComplaint;
import com.yaozhitech.baobei.child.dto.LeaderFeedbackDTO;

import java.util.List;
import java.util.Map;

public interface LeaderFeedbackMapper {

    Integer getLeaderFeedbackTotal(Map map);

    List<LeaderFeedbackDTO> getLeaderFeedbackList(Map map);

    void update(Integer id);

    Integer getLeaderFeedbackComplaintTotal(Map map);

    List<LeaderFeedbackComplaint> getLeaderFeedbackComplaintList(Map map);
}