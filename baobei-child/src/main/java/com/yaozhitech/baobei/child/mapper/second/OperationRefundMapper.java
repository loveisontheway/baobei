package com.yaozhitech.baobei.child.mapper.second;


import com.yaozhitech.baobei.child.domain.OperationRefund;

import java.util.List;

public interface OperationRefundMapper {
    List<OperationRefund> list(Integer page, Integer size);
}