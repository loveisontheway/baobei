package com.yaozhitech.baobei.child.mapper.third;

import com.yaozhitech.baobei.child.domain.User;

import java.util.List;

public interface UserMapper {
    List<User> list(Integer page, Integer size);
}