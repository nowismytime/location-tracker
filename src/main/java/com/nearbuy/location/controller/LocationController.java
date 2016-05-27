package com.nearbuy.location.controller;

import com.nearbuy.location.dao.HotspotDao;
import com.nearbuy.location.dao.UserLocationDao;
import com.nearbuy.location.dao.model.GeoJson;
import com.nearbuy.location.dao.model.GeoJsonFactory;
import com.nearbuy.location.dao.model.Hotspot;
import com.nearbuy.location.dao.model.UserLocation;
import com.nearbuy.location.util.AppUtil;
import com.nearbuy.location.util.GeoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by tushar on 20/08/15.
 */
@Controller
public class LocationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);
    private static final Double ZERO = 0.0;

    @Autowired
    private UserLocationDao userLocationDao;
    @Autowired
    private HotspotDao hotspotDao;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    @ResponseBody
    public String postLocation(){
        return "pong";
    }

    @RequestMapping(value = "/location", method = RequestMethod.POST)
    @ResponseBody
    public String postLocation(double lat, double lng, boolean isBackground,
                               @RequestParam(required = false) String customerId, @RequestParam(required = false) String metaInfo,
                               @RequestParam(required = false) String actionType){

        Map _metaInfo = AppUtil.parseJson(metaInfo, Map.class);
        UserLocation newLocation = new UserLocation(new Double[]{lng, lat}, customerId, !isBackground, actionType, _metaInfo);
        if(customerId!=null){
            GeoJson<List<Double>> lastLocation = userLocationDao.getLastLocation(customerId);
            Hotspot hotspot = hotspotDao.findNearest(newLocation.getLocation());
            if (hotspot != null) {
                if (hotspot.getDistance().equals(ZERO) && ( lastLocation==null || !GeoUtil.isInside(lastLocation, hotspot.getLocation()))) {
//                    User has entered hotspot here
                    return "you have entered hotspot with id : " + hotspot.get_id();
                }
                if (!hotspot.getDistance().equals(ZERO) && (lastLocation!=null && GeoUtil.isInside(lastLocation, hotspot.getLocation()))) {
//                    user has left hotspot
                    return "you have left hotspot with id : " + hotspot.get_id();
                }
            }
        }
        userLocationDao.add(newLocation);
        return "no event";
    }

    @RequestMapping(value = "/hotspot", method = RequestMethod.POST)
    @ResponseBody
    public Long postHotspot(String name, Double[][] coordinates) {
        Hotspot hotspot = new Hotspot();
        hotspot.setName(name);
        hotspot.setLocation(GeoJsonFactory.getPolygon(coordinates));
        return hotspotDao.insert(hotspot);
    }

}
