package com.yaozhitech.baobei.child.client;

import com.yaozhitech.baobei.child.domain.ChildArchives;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "baobei-cache", fallback = ElasticClientFallback.class)
public interface ElasticClient {

    @RequestMapping(value = "/elastic/addByList", method = RequestMethod.POST)
    public String addByList(@RequestParam(value = "index") String index,
                            @RequestParam(value = "type") String type,
                            @RequestBody List<ChildArchives> list);

}
