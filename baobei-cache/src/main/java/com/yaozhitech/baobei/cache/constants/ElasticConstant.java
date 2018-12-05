package com.yaozhitech.baobei.cache.constants;

/**
 * Elasticsearch连接参数
 *
 * @author jiangjialiang on 2018/11/06
 */
public final class ElasticConstant {
    public static final String CLUST_NAME = "baobei-es";     /** 集群名称 */
    public static final String INDEX_NAME = "child";         /** 索引名称 */
    public static final String CLUST_IP = "120.55.124.69";   /** ip */
    // http请求的端口是9200，客户端是9300
    public static final int CLUST_PORT = 9300;               /** 端口号 */
    /** 文件地址 */
    public static final String FILE_PATH = "/Users/loveisontheway/Downloads/bbztx/child_archives.json";
    public static final String TYPE_NAME = "archives";       /** 类型名称 */
}
