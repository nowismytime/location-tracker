package com.nearbuy.location.controller;

import io.swagger.models.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.nearbuy.location.dao.HotspotDao;
import com.nearbuy.location.dao.UserLocationDao;
import com.nearbuy.location.dao.model.GeoJson;
import com.nearbuy.location.dao.model.GeoJsonFactory;
import com.nearbuy.location.dao.model.Hotspot;
import com.nearbuy.location.dao.model.UserLocation;
import com.nearbuy.location.service.UserLocationService;
import com.nearbuy.location.util.AppUtil;
import com.nearbuy.location.util.GeoUtil;

/**
 * Created by tushar on 20/08/15.
 */
@Controller
public class LocationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);
    private static final Double ZERO = 0.0;

    @Autowired
    public UserLocationDao userLocationDao;
    @Autowired
    public HotspotDao hotspotDao;
    @Autowired
    public UserLocationService userLocationService;

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
        String retVal = null;
        Map _metaInfo = AppUtil.parseJson(metaInfo, Map.class);
        UserLocation newLocation = new UserLocation(new Double[]{lng, lat}, customerId, !isBackground, actionType, _metaInfo);
        if(customerId!=null){
            GeoJson<List<Double>> lastLocation = userLocationDao.getLastLocation(customerId);
            Hotspot hotspot = hotspotDao.findNearest(newLocation.getLocation());
            if (hotspot != null) {
                if (hotspot.getDistance().equals(ZERO) && ( lastLocation==null || !GeoUtil.isInside(lastLocation, hotspot.getLocation()))) {
//                    User has entered hotspot here
                    retVal =  "you have entered hotspot with id : " + hotspot.get_id();
                }
                if (!hotspot.getDistance().equals(ZERO) && (lastLocation!=null && GeoUtil.isInside(lastLocation, hotspot.getLocation()))) {
//                    user has left hotspot
                    retVal =  "you have left hotspot with id : " + hotspot.get_id();
                }
            }
        }
        userLocationDao.add(newLocation);
        if(retVal==null) {
            retVal = "no event";
        }
        return retVal;
    }

    @RequestMapping(value = "/hotspot", method = RequestMethod.POST)
    @ResponseBody
    public Long postHotspot(String name, String coordinates) {
        Double[][] coords = AppUtil.parseJson(coordinates, Double[][].class);
        Hotspot hotspot = new Hotspot();
        hotspot.setName(name);
        hotspot.setLocation(GeoJsonFactory.getPolygon(coords));
        return hotspotDao.insert(hotspot);
    }
    
    /*@RequestMapping(value = "/location", method = RequestMethod.GET)
    public String findAllUsers(@RequestParam(value="lat")double lat, @RequestParam(value="lng")double lng, @RequestParam(value="dist")double x){
    	double change = x/111000;
    	double maxLat = lat + change;
    	double minLat = lat - change;
    	double unitLong = (Math.cos(lat))*111000;
    	double minLong = lng - x/unitLong;
    	double maxLong = lng + x/unitLong;
    	Long time = System.currentTimeMillis() - 15*60*1000;
    	Iterable<UserLocation> users = userLocationService.findUsers(time, Math.min(minLong, maxLong), minLat, Math.max(minLong, maxLong), maxLat);
    	return AppUtil.getJsonArray(users) ;
    }*/
    
    @RequestMapping(value = "/location", method = RequestMethod.GET)
    @ResponseBody
    public String findUsers(@RequestParam(value="lat") double lat, @RequestParam(value="lng")  double lng,@RequestParam(value="dist")  double x){
    	double change = x/111000;
    	double maxLat = lat + change;
    	double minLat = lat - change;
    	double unitLong = (Math.cos(lat))*111000;
    	double minLong = lng - x/unitLong;
    	double maxLong = lng + x/unitLong;
    	/*System.out.println(maxLat);
    	System.out.println(minLat);
    	System.out.println(maxLong);
    	System.out.println(minLong);*/
    	Long time = System.currentTimeMillis() - 15*60*1000;
    	ArrayList<UserLocation> users = userLocationService.findUsersBox(time, Math.min(minLong, maxLong), minLat, Math.max(minLong, maxLong), maxLat);
    	Gson gson = new Gson();
    	String json1 = gson.toJson(users);
    	return json1;
    }

}
