package com.yaozhitech.baobei.child.web;

import com.yaozhitech.baobei.child.domain.BiHuaxiang;
import com.yaozhitech.baobei.child.service.first.BiHuaxiangService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
import java.lang.Integer;

/**
 * 用户画像 - Controller类
 *
 * @author jiangjialiang on 2018/11/06
 */
@Api(description = "用户画像")
@Controller
@RequestMapping("/biHuaxiang")
public class BiHuaxiangController {

    @Resource
    private BiHuaxiangService biHuaxiangService;

    @ApiOperation(value = "删除", notes = "单表删除")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "主键", required = true, dataType = "Integer")
    })
    @PostMapping("/delete")
    public String deleteByPrimaryKey(@RequestParam Integer id) {
        int result = biHuaxiangService.deleteByPrimaryKey(id);
        return "" + result;
    }

    @ApiOperation(value = "新增", notes = "单表新增")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "BiHuaxiang", value = "BiHuaxiang对象", required = true, dataType = "Object")
    })
    @PostMapping("/add")
    public String insert(BiHuaxiang biHuaxiang) {
        int result = biHuaxiangService.insert(biHuaxiang);
        return "" + result;
    }

    @ApiOperation(value = "详情", notes = "单表详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "主键", required = true, dataType = "Integer")
    })
    @PostMapping("/detail")
    public BiHuaxiang detail(@RequestParam Integer id) {
        BiHuaxiang biHuaxiang = biHuaxiangService.selectByPrimaryKey(id);
        return biHuaxiang;
    }

    @ApiOperation(value = "集合", notes = "单表集合")
    @PostMapping("/list")
    public List<BiHuaxiang> selectAll() {
        List<BiHuaxiang> list = biHuaxiangService.selectAll();
        return list;
    }

    @ApiOperation(value = "更新", notes = "单表更新")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "BiHuaxiang", value = "BiHuaxiang对象", required = true, dataType = "Object")
    })
    @PostMapping("/update")
    public String updateByPrimaryKey(BiHuaxiang biHuaxiang) {
        int result = biHuaxiangService.updateByPrimaryKey(biHuaxiang);
        return "" + result;
    }

}
