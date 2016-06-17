package com.nearbuy.location.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.nearbuy.location.dao.model.GeoJson;
import com.nearbuy.location.dao.model.UserLocation;

@Component
public class UserLocationDao {
    private static final String TIME = "time";
    private static final String CUSTOMER_ID = "customerId";
    private static final String LOCATION = "location";

//    private static final String LOCATION_INDEX = "location";
//    private static final java.lang.String USER_LOCATION_TYPE = "userLocation";

//    @Autowired
//    private Client client;

//    public String add(UserLocation userLocation) {
//        IndexResponse resp = client.prepareIndex(LOCATION_INDEX, USER_LOCATION_TYPE)
//                .setSource(AppUtil.getJsonBytes(userLocation))
//                .get();
//        return resp.getId();
//    }

    @Qualifier("userLocation")
    @Autowired
    MongoCollection collection;

    public void add(UserLocation userLocation){
        collection.withDocumentClass(UserLocation.class).insertOne(userLocation);
    }

    public GeoJson<List<Double>>    getLastLocation(String customerId){
        UserLocation loc = (UserLocation) collection.withDocumentClass(UserLocation.class).find(Filters.eq(CUSTOMER_ID, customerId))
                .sort(Sorts.descending(TIME)).projection(Projections.include(LOCATION)).first();
        return loc==null?null:loc.getLocation();
    }

}
