package com.yaozhitech.baobei.child.service.second.impl;

import com.yaozhitech.baobei.child.domain.OperationRefund;
import com.yaozhitech.baobei.child.mapper.second.OperationRefundMapper;
import com.yaozhitech.baobei.child.service.second.OperationRefundService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 退款记录表-ServiceImpl接口实现类
 * @author jiangjialiang on 2018/11/05
 * @version 1.0.0
 */
@Service
@Transactional
public class OperationRefundServiceImpl implements OperationRefundService {
    @Resource
    private OperationRefundMapper operationRefundMapper;

    @Override
    public List<OperationRefund> list(Integer page, Integer size) {
        return operationRefundMapper.list(page, size);
    }
}
