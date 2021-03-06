package com.yaozhitech.baobei.article.service.impl;

import com.yaozhitech.baobei.article.mapper.UserMapper;
import com.yaozhitech.baobei.article.domain.User;
import com.yaozhitech.baobei.article.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.String;
import java.util.List;

import javax.annotation.Resource;

/**
 * 用户 - ServiceImpl接口实现类
 *
 * @author jiangjialiang on 2018/11/08
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

}