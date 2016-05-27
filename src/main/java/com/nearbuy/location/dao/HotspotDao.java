package com.nearbuy.location.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.nearbuy.location.dao.codec.HotspotCodec;
import com.nearbuy.location.dao.model.GeoJson;
import com.nearbuy.location.dao.model.Hotspot;
import com.nearbuy.location.util.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tushar on 26/05/16.
 */
@Component
public class HotspotDao extends BasicDao<Hotspot>{

    private static final String HOTSPOT = "hotspot";
    @Qualifier("hotspot")
    @Autowired
    MongoCollection collection;

    @Autowired
    HotspotCodec codec;

    @PostConstruct
    public void init() {
        super.init(Hotspot.class, collection);
    }

    @Autowired
    CounterCollectionDao counterCollectionDao;

    public Long insert(Hotspot hotspot){
        Long seq = counterCollectionDao.getNextSequence(HOTSPOT);
        hotspot.set_id(seq);
        collection.withDocumentClass(Hotspot.class).insertOne(hotspot);
        return seq;
    }


    public Hotspot findIntersectingHotspot(GeoJson<List<Double>> lastLocation, GeoJson<List<Double>> location) {
        return null;
    }

    public Hotspot findNearest(GeoJson<List<Double>> point) {
        return (Hotspot) collection.aggregate(Arrays.asList(DBUtil.geoNearBson(point.getCoordinates(), 1)), Hotspot.class).first();
    }
}
