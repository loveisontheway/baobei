package com.yaozhitech.baobei.child.web;

import com.yaozhitech.baobei.child.domain.User;
import com.yaozhitech.baobei.child.service.third.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表-Controller类
 * @author jiangjialiang on 2018/11/05
 * @version 1.0.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/list")
    public void list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        List<User> list = userService.list(page, size);
    }
}
