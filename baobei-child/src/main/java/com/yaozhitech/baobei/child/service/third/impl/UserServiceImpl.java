package com.yaozhitech.baobei.child.service.third.impl;

import com.yaozhitech.baobei.child.domain.User;
import com.yaozhitech.baobei.child.mapper.third.UserMapper;
import com.yaozhitech.baobei.child.service.third.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表-ServiceImpl接口实现类
 * @author jiangjialiang on 2018/11/05
 * @version 1.0.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> list(Integer page, Integer size) {
        return userMapper.list(page, size);
    }
}
