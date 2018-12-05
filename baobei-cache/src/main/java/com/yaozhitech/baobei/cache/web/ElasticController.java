package com.yaozhitech.baobei.cache.web;

import com.yaozhitech.baobei.cache.utils.ElasticUtil;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/elastic")
public class ElasticController {

    @GetMapping("/get")
    public String get(String index, String type, String id) {
        GetResponse response = ElasticUtil.getDoc(index, type, id);
        String str = response.toString();
        return str;
    }

    @DeleteMapping("/del")
    public String del(String index, String type, String id) {
        DeleteResponse response = ElasticUtil.deleteDoc(index, type, id);
        String str = response.toString();
        return str;
    }

    @DeleteMapping("/delByQuery")
    public String delByQuery(String index, String name, String text) {
        BulkByScrollResponse response = ElasticUtil.deleteByQuery(index, name, text);
        String str = response.toString();
        return str;
    }

    @PostMapping("/addByJSON")
    public String addByJSON(String index, String type, String json) {
        IndexResponse response = ElasticUtil.insertDocByJSON(index, type, json);
        String str = response.toString();
        return str;
    }

    @PostMapping("/addByMap")
    public String addByMap(String index, String type, @RequestParam Map<String, Object> map) {
        IndexResponse response = ElasticUtil.insertDocByMap(index, type, map);
        String str = response.toString();
        return str;
    }

    @PostMapping("/addByObj")
    public String addByObj(String index, String type, @RequestBody Object obj) {
        IndexResponse response = ElasticUtil.insertDocByObject(index, type, obj);
        String str = response.toString();
        return str;
    }

    @PostMapping("/addByList")
    public void addByList(String index, String type, @RequestBody List<Object> list) {
        ElasticUtil.insertDocByList(index, type, list);
    }

    @PutMapping("/update")
    public void update(String index, String type, String id, String name, String text) {
        ElasticUtil.updateDoc(index, type, id, name, text);
    }

}
