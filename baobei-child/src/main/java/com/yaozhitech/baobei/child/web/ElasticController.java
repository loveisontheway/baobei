package com.yaozhitech.baobei.child.web;

import com.yaozhitech.baobei.child.constants.ESConstant;
import com.yaozhitech.baobei.child.utils.ESUtil;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@RestController
public class ElasticController {

    @RequestMapping(value = "elastic", method = RequestMethod.GET)
    public void elastic() {
        this.fileToEs(ESConstant.CLUST_NAME, ESConstant.INDEX_NAME, ESConstant.CLUST_IP, ESConstant.CLUST_PORT, ESConstant.FILE_PATH, ESConstant.TYPE_NAME);
    }

    /**
     * 读取文件内容，将数据添加到ES
     * @param clustName 集群名称
     * @param indexName 索引名称
     * @param clustIp ip
     * @param clustPort 端口号
     * @param filePath 文件地址
     * @param typeName 类型名称
     */
    private void fileToEs(String clustName, String indexName, String clustIp, int clustPort, String filePath, String typeName) {
        TransportClient client = ESUtil.createClient(clustName, clustIp, clustPort);
        try {
            // 把导出的结果以JSON的格式写到文件里
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String json = null;
            int count = 0;
            // 开启批量插入
            BulkRequestBuilder bulkRequest = client.prepareBulk();
            while ((json = br.readLine()) != null) {
                bulkRequest.add(client.prepareIndex(indexName, typeName).setSource(json, XContentType.JSON));
                // 每一千条提交一次
                count++;
                if (count % 1000 == 0) {
                    System.out.println("本次提交了1000条");
                    BulkResponse bulkResponse = bulkRequest.execute().actionGet();
                    if (bulkResponse.hasFailures()) {
                        System.out.println("message:" + bulkResponse.buildFailureMessage());
                    }
                    // 重新创建一个bulk
                    bulkRequest = client.prepareBulk();
                }
            }
            bulkRequest.execute().actionGet();
            System.out.println("总共提交了：" + count);
            br.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
