package com.nearbuy.framework.springbootmongo.dao.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.ValueCodecProvider;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.nearbuy.framework.springbootmongo.dao.codec.DemoModelCodec;
import com.nearbuy.framework.springbootmongo.dao.codec.DocumentCodecProvider;
import com.nearbuy.framework.springbootmongo.dao.model.DemoModel;

/**
 * Created by tushar on 19/08/15.
 */
@Configuration
@ComponentScan(basePackages = "com.nearbuy.platform.deal.dao")
public class MongoConfig {

    @Autowired
    private Environment env;

    private static Logger logger = LoggerFactory.getLogger(MongoConfig.class);
    private List<? extends CodecProvider> provider;

    @Autowired
    private DemoModelCodec demoModelcodec;

    @Bean
    public MongoDatabase getDb(){
        String db = env.getProperty("mongo.db");
        String host = env.getProperty("mongo.host");
        String port = env.getProperty("mongo.port");
        MongoClient mc = new MongoClient(host, Integer.valueOf(port));
        logger.info("database : " + db);
        CodecRegistry codecRegistry = CodecRegistries.fromProviders(Arrays.asList(getProvider(),new ValueCodecProvider()));
        return mc.getDatabase(db).withCodecRegistry(codecRegistry);
  
    }

    @Bean(name = "framework")
    public MongoCollection<Document> getFrameworkCollection(){
        return getDb().getCollection("framework");
    }

    @Bean
    public CodecProvider getProvider() {
        Map<Class,Codec> map = new HashMap<>();
        map.put(Document.class, new DocumentCodec());
        map.put(DemoModel.class, demoModelcodec);
        return new DocumentCodecProvider(map);
    }

}
