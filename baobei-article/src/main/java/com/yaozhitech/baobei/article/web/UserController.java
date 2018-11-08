package com.yaozhitech.baobei.article.web;

import com.yaozhitech.baobei.article.domain.User;
import com.yaozhitech.baobei.article.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
import java.lang.String;

/**
 * 用户 - Controller类
 *
 * @author jiangjialiang on 2018/11/08
 */
@Api(description = "用户")
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "新增", notes = "单表新增")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "User", value = "User对象", required = true, dataType = "Object")
    })
    @PostMapping("/add")
    public String insert(User user) {
        int result = userService.insert(user);
        return "" + result;
    }

    @ApiOperation(value = "集合", notes = "单表集合")
    @GetMapping("/list")
    public List<User> selectAll() {
        List<User> list = userService.selectAll();
        return list;
    }

}