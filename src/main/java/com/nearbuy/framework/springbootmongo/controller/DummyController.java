package com.nearbuy.framework.springbootmongo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nearbuy.framework.springbootmongo.dao.BaseDao;
import com.nearbuy.framework.springbootmongo.dao.model.DemoModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

/**
 * Created by tushar on 20/08/15.
 */
@Controller
public class DummyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyController.class);

    @Autowired
    private BaseDao baseDao;
    @RequestMapping(value = "/framework/hello")
    @ResponseBody
    public String getDummyVal(){
        LOGGER.info("Hello! From Spring boot and mongo");
        DemoModel model = new DemoModel();
        model.setDemo("demoo");
        baseDao.insertDemo(model);
        
        return "Hello! From Spring boot and mongo";
    }

}
