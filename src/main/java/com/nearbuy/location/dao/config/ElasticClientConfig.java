//package com.nearbuy.location.dao.config;
//
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PreDestroy;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
///**
// * Created by tushar on 20/05/16.
// */
//@Configuration
//public class ElasticClientConfig {
//
//    private static final String DEFAULT_CLUSTER_NAME = "elasticsearch";
//    private TransportClient client;
//
//    /**
//     * Elasticsearch-Cluster-1
//     10.20.2.117
//     Elasticsearch-Cluster-2
//     10.20.2.115
//     Elasticsearch-Cluster-3
//     10.20.2.116
//     * @return
//     * @throws UnknownHostException
//     */
//    @Bean
//    public TransportClient elasticClient() throws UnknownHostException {
//        Settings settings = Settings.settingsBuilder()
//                .put("cluster.name", DEFAULT_CLUSTER_NAME).build();
//        client = TransportClient.builder().settings(settings).build()
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
//        return client;
//    }
//
//    @PreDestroy
//    public void destroy(){
//        client.close();
//    }
//}
