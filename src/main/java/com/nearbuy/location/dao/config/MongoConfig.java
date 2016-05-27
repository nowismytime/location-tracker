package com.nearbuy.location.dao.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.geojson.Polygon;
import com.mongodb.client.model.geojson.codecs.GeoJsonCodecProvider;
import com.mongodb.client.model.geojson.codecs.PolygonCodec;
import com.nearbuy.location.dao.codec.DocumentCodecProvider;
import com.nearbuy.location.dao.codec.HotspotCodec;
import com.nearbuy.location.dao.codec.UserLocationCodec;
import com.nearbuy.location.dao.model.Hotspot;
import com.nearbuy.location.dao.model.UserLocation;
import org.bson.Document;
import org.bson.codecs.*;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tushar on 19/08/15.
 */
@Configuration
@PropertySource(ignoreResourceNotFound = false, value ={"classpath:env.properties"})
public class MongoConfig {

    private static final String LOCATION = "location";
    @Autowired
    private Environment env;

    private static Logger logger = LoggerFactory.getLogger(MongoConfig.class);
    private List<? extends CodecProvider> provider;

    @Autowired
    private UserLocationCodec userLocationCodec;
    @Autowired
    private HotspotCodec hotspotCodec;

    @Bean
    public MongoDatabase getDb(){
        String db = env.getProperty("db");
        String host = env.getProperty("host");
        String port = env.getProperty("port");
        MongoClient mc = new MongoClient(host, Integer.valueOf(port));
        logger.info("database : " + db);
        CodecRegistry codecRegistry = CodecRegistries.fromProviders(getProvider());
//                CodecRegistries.fromCodecs(
//                hotspotCodec,userLocationCodec,new StringCodec(), new IntegerCodec(), new LongCodec(),new DoubleCodec(),new DocumentCodec());
//                new PolygonCodec(CodecRegistries.fromProviders(new GeoJsonCodecProvider())));
        return mc.getDatabase(db).withCodecRegistry(codecRegistry);

    }

    @Bean(name = "userLocation")
    public MongoCollection<Document> getUserLocationColl(){
        MongoCollection<Document> coll = getDb().getCollection("userLocation");
        coll.createIndex(new Document(LOCATION, "2dsphere"));
        return coll;
    }

    @Bean(name = "counters")
    public MongoCollection<Document> getCounterCollColl(){
        MongoCollection<Document> counters = getDb().getCollection("counters");
        counters.updateOne(Filters.eq("_id", "hotspot"), Updates.set("_id", "hotspot"), new UpdateOptions().upsert(true));
        return counters;

    }

    @Bean(name = "hotspot")
    public MongoCollection<Document> getHotspotColl(){
        MongoCollection<Document> coll = getDb().getCollection("hotspot");
        coll.createIndex(new Document(LOCATION, "2dsphere"));
        return coll;
    }

    @Bean
    public CodecProvider getProvider() {
        new GeoJsonCodecProvider();
        Map<Class,Codec> map = new HashMap();
        map.put(Document.class, new DocumentCodec());
        map.put(String.class, new StringCodec());
        map.put(Double.class, new DoubleCodec());
        map.put(Integer.class, new IntegerCodec());
        map.put(Long.class, new LongCodec());
        map.put(UserLocation.class, userLocationCodec);
        map.put(Hotspot.class, hotspotCodec);
        return new DocumentCodecProvider(map);
    }

}
