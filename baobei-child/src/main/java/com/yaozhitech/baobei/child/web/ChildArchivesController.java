package com.yaozhitech.baobei.child.web;

import com.alibaba.fastjson.JSONObject;
import com.yaozhitech.baobei.child.constants.ESConstant;
import com.yaozhitech.baobei.child.domain.ChildArchives;
import com.yaozhitech.baobei.child.service.first.ChildArchivesService;
import com.yaozhitech.baobei.child.utils.ESUtil;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/list")
    public void list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        List<ChildArchives> list = childArchivesService.list(page, size);
        this.fileToEs(ESConstant.CLUST_NAME, ESConstant.INDEX_NAME, ESConstant.CLUST_IP, ESConstant.CLUST_PORT, list, ESConstant.TYPE_NAME);
    }

    /**
     * 读取文件内容，将数据添加到ES
     * @param clustName 集群名称
     * @param indexName 索引名称
     * @param clustIp ip
     * @param clustPort 端口号
     * @param list 集合
     * @param typeName 类型名称
     */
    private void fileToEs(String clustName, String indexName, String clustIp, int clustPort, List<ChildArchives> list, String typeName) {
        TransportClient client = ESUtil.createClient(clustName, clustIp, clustPort);
        try {
            String json = null;
            // 开启批量插入
            BulkRequestBuilder bulkRequest = client.prepareBulk();
            for (int i=0; i<list.size(); i++) {
                ChildArchives ca = list.get(i);
                if ((json = JSONObject.toJSONString(ca)) != null) {
                    bulkRequest.add(client.prepareIndex(indexName, typeName).setSource(json, XContentType.JSON));
                    // 每一万条提交一次
                    if (i % 10000 == 0) {
                        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
                        if (bulkResponse.hasFailures()) {
                            System.out.println("message:" + bulkResponse.buildFailureMessage());
                        }
                        // 重新创建一个bulk
                        bulkRequest = client.prepareBulk();
                    }
                }
            }

            bulkRequest.execute().actionGet();
            System.out.println("总共提交了：" + list.size());
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/test")
    public void test() {
        batchToES(ESConstant.CLUST_NAME, ESConstant.CLUST_IP, ESConstant.CLUST_PORT);
    }

    private static void batchToES(String clustName, String clustIp, int clustPort) {
        try {
            TransportClient client = ESUtil.createClient(clustName, clustIp, clustPort);
            // 写入数据
            System.out.println(">>>>>>>>>>>>>>>>>> write to es >>>>>>>>>>>>>>>>>>");

//            IndexResponse response = client.prepareIndex("test", "es_dt", "t1")
//                    .setSource(jsonBuilder()
//                            .startObject()
//                            .endObject()
//                    ).get();
            // 搜索数据
//            GetResponse response = client.prepareGet("test", "es_dt", "t1").execute().actionGet();
            // 输出结果
//            System.out.println(response.getSource());
            long startTimeall = System.currentTimeMillis();
            for (int i = 100; i < 200; i++) {
                long startTime = System.currentTimeMillis();
                createManyDates(client, i);
                long endTime = System.currentTimeMillis(); // 获取结束时间
                System.out.println("十万条插入时间： " + (endTime - startTime) + "ms");
            }
            long endTimeall = System.currentTimeMillis(); // 获取结束时间
            System.out.println("所有运行时间： " + (endTimeall - startTimeall) + "ms");

            // 关闭client
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 批量插入（每次插入100000条）
     * @param client
     * @param j
     */
    private static void createManyDates(TransportClient client, Integer j){
        int count = (j * 100000 + 1);
        int k = j * 100000;
        Map<String, Object> map = new HashMap<String, Object>();
        String index = "test";
        String type = "es_dt";
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        for (int i = (j - 1) * 100000 + 1; i < count; i++) {
            map.put("bbztx_no", i);
            //Map<String, Object> map = new HashMap<String, Object>();
            bulkRequestBuilder.add(client.prepareIndex(index, type, i + "").setSource(map));
            if (i % k == 0) {
                bulkRequestBuilder.execute().actionGet();
            }
        }
    }

}
