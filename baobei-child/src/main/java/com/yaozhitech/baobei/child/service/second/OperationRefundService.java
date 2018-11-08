package com.yaozhitech.baobei.child.service.second;


import com.yaozhitech.baobei.child.domain.OperationRefund;

import java.util.List;

/**
 * 退款记录表-Service接口类
 * @author jiangjialiang on 2018/11/05
 * @version 1.0.0
 */
public interface OperationRefundService {

    List<OperationRefund> list(Integer page, Integer size);
}
