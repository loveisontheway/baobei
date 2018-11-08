package com.yaozhitech.baobei.child.service.first;


import com.yaozhitech.baobei.child.domain.ChildArchives;

import java.util.List;

/**
 * 儿童档案-Service接口类
 * @author jiangjialiang on 2018/10/29
 * @version 1.0.0
 */
public interface ChildArchivesService {

    List<ChildArchives> list(Integer page, Integer size);
}
