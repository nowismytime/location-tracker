package com.nearbuy.location.dao;


import com.nearbuy.location.dao.model.UserLocation;
import com.nearbuy.location.util.AppUtil;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserLocationDao {

    private static final String LOCATION_INDEX = "location";
    private static final java.lang.String USER_LOCATION_TYPE = "userLocation";

    @Autowired
    private Client client;

    public String add(UserLocation userLocation) {
        IndexResponse resp = client.prepareIndex(LOCATION_INDEX, USER_LOCATION_TYPE)
                .setSource(AppUtil.getJsonBytes(userLocation))
                .get();
        return resp.getId();
    }



}
