package com.yaozhitech.baobei.child.utils;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class ESUtil {

    /**
     * 建立连接
     * @param cluster 集群名称
     * @param ip ip
     * @param port 端口号
     * @return TransportClient
     */
    public static TransportClient createClient(String cluster, String ip, Integer port) {
        TransportClient client = null;
        if (client == null) {
            synchronized (ESUtil.class) {
                try {
                    client = new PreBuiltTransportClient(Settings.EMPTY)
                            .addTransportAddress(new TransportAddress(InetAddress.getByName(ip), port));
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        return client;
    }
}
