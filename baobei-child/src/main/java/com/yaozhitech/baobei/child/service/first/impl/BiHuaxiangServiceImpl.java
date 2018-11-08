package com.yaozhitech.baobei.child.service.first.impl;

import com.yaozhitech.baobei.child.mapper.first.BiHuaxiangMapper;
import com.yaozhitech.baobei.child.domain.BiHuaxiang;
import com.yaozhitech.baobei.child.service.first.BiHuaxiangService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.Integer;
import java.util.List;

import javax.annotation.Resource;

/**
 * 用户画像 - ServiceImpl接口实现类
 *
 * @author jiangjialiang on 2018/11/06
 */
@Service
@Transactional
public class BiHuaxiangServiceImpl implements BiHuaxiangService {

    @Resource
    private BiHuaxiangMapper biHuaxiangMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return biHuaxiangMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BiHuaxiang biHuaxiang) {
        return biHuaxiangMapper.insert(biHuaxiang);
    }

    @Override
    public BiHuaxiang selectByPrimaryKey(Integer id) {
        return biHuaxiangMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BiHuaxiang> selectAll() {
        return biHuaxiangMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(BiHuaxiang biHuaxiang) {
        return biHuaxiangMapper.updateByPrimaryKey(biHuaxiang);
    }

}
