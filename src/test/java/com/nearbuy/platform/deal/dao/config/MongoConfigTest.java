//package com.nearbuy.platform.deal.dao.config;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.mongodb.client.MongoCollection;
//import com.nearbuy.platform.deal.config.ApplicationConfigTest;
//
///**
// * Created by tushar on 19/08/15.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes= ApplicationConfigTest.class)
//public class MongoConfigTest {
//
//    @Autowired(required = false)
//    @Qualifier("framework")
//    MongoCollection coll;
//
//    @Test
//    public void testDummy(){
//        Assert.assertNotNull(coll);
//    }
//
//}
