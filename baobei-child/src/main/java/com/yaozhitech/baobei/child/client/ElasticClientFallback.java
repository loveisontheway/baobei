package com.yaozhitech.baobei.child.client;

import com.yaozhitech.baobei.child.domain.ChildArchives;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Hystrix熔断器
 *
 * @author jiangjialiang on 2018/11/21
 */
@Component
public class ElasticClientFallback implements ElasticClient {
    @Override
    public String addByList(String index, String type, List<ChildArchives> list) {
        System.out.println("hystrix fallback");
        return "hystrix fallback";
    }
}
