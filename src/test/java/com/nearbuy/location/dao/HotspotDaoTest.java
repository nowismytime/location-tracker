package com.nearbuy.location.dao;

import com.mongodb.client.MongoCollection;
import com.nearbuy.location.config.ApplicationConfigTest;
import com.nearbuy.location.dao.model.GeoJsonFactory;
import com.nearbuy.location.dao.model.Hotspot;
import org.bson.Document;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * Created by tushar on 26/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= ApplicationConfigTest.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HotspotDaoTest {

    @Autowired
    HotspotDao dao;

    @Qualifier(value = "hotspot")
    @Autowired
    private MongoCollection collection;
    private static Long id1;
    private static Long id2;

    @Test
    public void t0init(){
        System.out.println("deleting hotspots");
        collection.deleteMany(new Document());

    }
    @Test
    public void t1save(){
        Hotspot hotspot1 = new Hotspot();
        hotspot1.setLocation(TestUtil.getTestPolygon1());
        Long id = dao.insert(hotspot1);

        Hotspot hotspot2 = new Hotspot();
        hotspot2.setLocation(TestUtil.getTestPolygon2());
        id1 = dao.insert(hotspot1);
        id2 = dao.insert(hotspot2);
    }

    @Test
    public void t2findById(){
        Assert.assertTrue(dao.findById(id1).getLocation().getCoordinates().get(0).get(0).get(0).equals(77.133750));
        Assert.assertTrue(dao.findById(id2).getLocation().getCoordinates().get(0).get(0).get(0).equals(77.134096));
    }

    @Test
    public void t3(){
//        RG center
        Hotspot hotspot = dao.findNearest(TestUtil.getTestPolygon2Center());
        Assert.assertTrue(hotspot.get_id().equals(id2));
        Assert.assertTrue(hotspot.getDistance().equals(0.0));
    }

}
