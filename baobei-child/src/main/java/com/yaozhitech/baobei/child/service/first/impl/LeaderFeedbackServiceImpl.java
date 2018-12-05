package com.yaozhitech.baobei.child.service.first.impl;

import com.yaozhitech.baobei.child.domain.LeaderFeedbackComplaint;
import com.yaozhitech.baobei.child.dto.LeaderFeedbackDTO;
import com.yaozhitech.baobei.child.mapper.first.LeaderFeedbackMapper;
import com.yaozhitech.baobei.child.service.first.LeaderFeedbackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 领队负责人反馈表-ServiceImpl接口实现类
 * @author jiangjialiang on 2018/11/22
 * @version 1.0.0
 */
@Service
@Transactional
public class LeaderFeedbackServiceImpl implements LeaderFeedbackService {

    @Resource
    private LeaderFeedbackMapper leaderFeedbackMapper;

    @Override
    public Integer getLeaderFeedbackTotal(Map map) {
        return leaderFeedbackMapper.getLeaderFeedbackTotal(map);
    }

    @Override
    public List<LeaderFeedbackDTO> getLeaderFeedbackList(Map map) {
        return leaderFeedbackMapper.getLeaderFeedbackList(map);
    }

    @Override
    public void update(Integer id) {
        leaderFeedbackMapper.update(id);
    }

    @Override
    public Integer getLeaderFeedbackComplaintTotal(Map map) {
        return leaderFeedbackMapper.getLeaderFeedbackComplaintTotal(map);
    }

    @Override
    public List<LeaderFeedbackComplaint> getLeaderFeedbackComplaintList(Map map) {
        return leaderFeedbackMapper.getLeaderFeedbackComplaintList(map);
    }
}
