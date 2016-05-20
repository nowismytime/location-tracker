package com.nearbuy.location.controller;

import com.nearbuy.location.dao.UserLocationDao;
import com.nearbuy.location.dao.model.UserLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by tushar on 20/08/15.
 */
@Controller
public class LocationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private UserLocationDao userLocationDao;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    @ResponseBody
    public String postLocation(){
        return "pong";
    }



    @RequestMapping(value = "/location", method = RequestMethod.POST)
    @ResponseBody
    public void postLocation(double lat, double lng, boolean isBackground,
                               @RequestParam(required = false) String customerId, @RequestParam(required = false) Map<String, Object> metaInfo,
                               @RequestParam(required = false) String actionType){
        UserLocation location = new UserLocation(new Double[]{lng, lat}, customerId, !isBackground, actionType, metaInfo);
        userLocationDao.add(location);
    }

}
