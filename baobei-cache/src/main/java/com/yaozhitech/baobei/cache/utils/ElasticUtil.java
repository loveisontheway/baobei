package com.yaozhitech.baobei.cache.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaozhitech.baobei.cache.constants.ElasticConstant;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;


public abstract class ElasticUtil {

    /**
     * Transport Client ( 不设置集群名称 )
     * @return TransportClient
     */
	public static TransportClient createClient() {
        TransportClient client = null;
        try {
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(ElasticConstant.CLUST_IP), ElasticConstant.CLUST_PORT));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }

    /**
     * Transport Client ( 设置集群名称 )
     * @param clusterName 集群名称
     * @return TransportClient
     */
    public static TransportClient createClient(String clusterName) {
        Settings settings = Settings.builder()
                // 设置ES实例的名称
                .put("cluster.name", clusterName)
                // 自动嗅探整个集群的状态，把集群中其他ES节点的ip添加到本地的客户端列表中
                .put("client.transport.sniff", true)
                .build();
        TransportClient client = new PreBuiltTransportClient(settings);
        try {
            // 此步骤添加IP，至少一个，其实一个就够了，因为添加了自动嗅探配置
            client.addTransportAddress(new TransportAddress(InetAddress.getByName(ElasticConstant.CLUST_IP), ElasticConstant.CLUST_PORT));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }

    /**
     * 获取索引库表文档
     * @param index 索引（库名）
     * @param type 类型（表名）
     * @param id 唯一标识（id主键）
     * @return DeleteResponse
     */
    public static GetResponse getDoc(String index, String type, String id) {
        TransportClient client = createClient();
        GetResponse response = client.prepareGet(index, type, id)
                .get();
        client.close();
        return response;
    }

    /**
     * 删除索引库表文档
     * @param index 索引（库名）
     * @param type 类型（表名）
     * @param id 唯一标识（id主键）
     * @return DeleteResponse
     */
    public static DeleteResponse deleteDoc(String index, String type, String id) {
        TransportClient client = createClient();
        DeleteResponse response = client.prepareDelete(index, type, id)
                .get();
        client.close();
        return response;
    }

    /**
     * 通过查询条件删除
     * @param index 索引（库名）
     * @param name 字段名
     * @param text 字段值
     * @return BulkByScrollResponse
     */
    public static BulkByScrollResponse deleteByQuery(String index, String name, String text) {
        TransportClient client = createClient();
        BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                        .filter(QueryBuilders.matchQuery(name, text))   // 查询条件
                        .source(index)                                  // index(索引名)
                        .get();                                         // 执行
        client.close();
        return response;
    }

    /**
     * 新增文档 ( JSON )
     * @param index 索引（库名）
     * @param type 类型（表名）
     * @param json 实体类
     * @return
     */
    public static IndexResponse insertDocByJSON(String index, String type, String json) {
        TransportClient client = createClient();
        IndexResponse response = null;
        JSONObject jo = JSONObject.parseObject(json);
        // 通过获取对象的id主键作为Elasticsearch文档中的唯一标识(_id)
        String id = jo.containsKey("id") ? jo.getString("id") : "";
        // 指定 _id 的值，否则就自动生成
        if (StringUtils.isBlank(id)) {
            response = client.prepareIndex(index, type)
                    .setSource(JSONObject.parseObject(json, Map.class))
                    .get();
        } else {
            response = client.prepareIndex(index, type)
                    .setSource(JSONObject.parseObject(json, Map.class))
                    .get();
        }
        client.close();
        return response;
    }

    /**
     * 新增文档 ( Map )
     * @param index 索引（库名）
     * @param type 类型（表名）
     * @param map 实体类
     * @return
     */
    public static IndexResponse insertDocByMap(String index, String type, Map<String, Object> map) {
        TransportClient client = createClient();
        IndexResponse response = null;
        // 通过获取对象的id主键作为Elasticsearch文档中的唯一标识(_id)
        String id = map.get("id") != null ? map.get("id").toString() : "";
        // 指定 _id 的值，否则就自动生成
        if (StringUtils.isBlank(id)) {
            response = client.prepareIndex(index, type)
                    .setSource(map)
                    .get();
        } else {
            response = client.prepareIndex(index, type, id)
                    .setSource(map)
                    .get();
        }
        client.close();
        return response;
    }

    /**
     * 新增文档 ( Object )
     * @param index 索引（库名）
     * @param type 类型（表名）
     * @param obj 实体类
     * @return
     */
    public static IndexResponse insertDocByObject(String index, String type, Object obj) {
        TransportClient client = createClient();
        ObjectMapper mapper = new ObjectMapper();
        IndexResponse response = null;
        try {
//            byte[] json = mapper.writeValueAsBytes(obj);
            String json = JSONObject.toJSONString(obj);
            JSONObject jo = JSONObject.parseObject(json);
            // 通过获取对象的id主键作为Elasticsearch文档中的唯一标识(_id)
            String id = jo.containsKey("id") ? jo.getString("id") : "";
            // 指定 _id 的值，否则就自动生成
            if (StringUtils.isBlank(id)) {
                response = client.prepareIndex(index, type)
                        .setSource(JSONObject.parseObject(json, Map.class))
                        .get();
            } else {
                response = client.prepareIndex(index, type, id)
                        .setSource(JSONObject.parseObject(json, Map.class))
                        .get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        client.close();
        return response;
    }

    /**
     * 新增文档 ( List )
     * @param index 索引（库名）
     * @param type 类型（表名）
     * @param list 集合
     */
    public static void insertDocByList(String index, String type, List<?> list) {
        TransportClient client = createClient();
        try {
            String json = null;
            // 开启批量插入
            BulkRequestBuilder bulkRequest = client.prepareBulk();
            for (int i=0; i<list.size(); i++) {
                Object obj = list.get(i);
                JSONObject jo = JSONObject.parseObject(JSONObject.toJSONString(obj));
                // 通过获取对象的id主键作为Elasticsearch文档中的唯一标识(_id)
                String id = jo.containsKey("id") ? jo.getString("id") : "";
                if ((json = JSONObject.toJSONString(obj)) != null) {
                    // 指定 _id 的值，否则就自动生成
                    if (StringUtils.isBlank(id)) {
                        bulkRequest.add(client.prepareIndex(index, type).setSource(json, XContentType.JSON));
                    } else {
                        bulkRequest.add(client.prepareIndex(index, type, id).setSource(json, XContentType.JSON));
                    }
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

    /**
     * 更新文档
     * @param index 索引（库名）
     * @param type 类型（表名）
     * @param id 唯一标识（id主键）
     * @param name 字段名
     * @param text 字段值
     */
    public static void updateDoc(String index, String type, String id, String name, String text) {
        TransportClient client = createClient();
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(index);
        updateRequest.type(type);
        updateRequest.id(id);
        try {
            updateRequest.doc(XContentFactory.jsonBuilder()
                    .startObject()
                    .field(name, text)
                    .endObject());
            client.update(updateRequest).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();
    }

}
