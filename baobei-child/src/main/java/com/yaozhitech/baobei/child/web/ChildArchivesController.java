package com.yaozhitech.baobei.child.web;

import com.yaozhitech.baobei.child.client.ElasticClient;
import com.yaozhitech.baobei.child.constants.ElasticConstant;
import com.yaozhitech.baobei.child.domain.ChildArchives;
import com.yaozhitech.baobei.child.service.first.ChildArchivesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 儿童档案-Controller类
 * @author jiangjialiang on 2018/10/29
 * @version 1.0.0
 */
@Controller
@RequestMapping("/childArchives")
public class ChildArchivesController {

    @Resource
    private ChildArchivesService childArchivesService;

    @Resource
    private ElasticClient elasticClient;

    @RequestMapping("/list")
    public void list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        List<ChildArchives> list = childArchivesService.list(page, size);
        elasticClient.addByList(ElasticConstant.INDEX_NAME, ElasticConstant.TYPE_NAME, list);
    }

}
