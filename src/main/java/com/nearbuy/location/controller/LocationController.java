package com.nearbuy.location.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by tushar on 20/08/15.
 */
@Controller
public class LocationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);

//    @Autowired
//    private DemoDao demoDao;

    @RequestMapping(value = "/location", method = RequestMethod.POST)
    @ResponseBody
    public void postLocation(double lat, double lng, boolean isBackground,
                               @RequestParam(required = false) String customerId, @RequestParam(required = false) String metaInfo,
                               @RequestParam(required = false) String actionType){
    }

}
