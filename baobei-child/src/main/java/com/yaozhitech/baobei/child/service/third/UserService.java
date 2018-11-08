package com.yaozhitech.baobei.child.service.third;

import com.yaozhitech.baobei.child.domain.User;

import java.util.List;

/**
 * 用户表-Service接口类
 * @author jiangjialiang on 2018/11/05
 * @version 1.0.0
 */
public interface UserService {

    List<User> list(Integer page, Integer size);
}
