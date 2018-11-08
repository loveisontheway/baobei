package com.yaozhitech.baobei.child.service.first.impl;

import com.yaozhitech.baobei.child.domain.ChildArchives;
import com.yaozhitech.baobei.child.mapper.first.ChildArchivesMapper;
import com.yaozhitech.baobei.child.service.first.ChildArchivesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 儿童档案-ServiceImpl接口实现类
 * @author jiangjialiang on 2018/10/29
 * @version 1.0.0
 */
@Service
@Transactional
public class ChildArchivesServiceImpl implements ChildArchivesService {
    @Resource
    private ChildArchivesMapper childArchivesMapper;

    @Override
    public List<ChildArchives> list(Integer page, Integer size) {
        return childArchivesMapper.list(page, size);
    }
}
