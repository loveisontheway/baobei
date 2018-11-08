package com.yaozhitech.baobei.article.service;
import com.yaozhitech.baobei.article.domain.User;
import java.util.List;
import java.lang.String;

/**
 * 用户 - Service接口类
 *
 * @author jiangjialiang on 2018/11/08
 */
public interface UserService {

    int insert(User user);

    List<User> selectAll();

}