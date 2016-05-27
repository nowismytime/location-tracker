package com.nearbuy.location.dao;

import com.nearbuy.location.config.ApplicationConfigTest;
import com.nearbuy.location.dao.model.GeoJson;
import com.nearbuy.location.dao.model.GeoJsonFactory;
import com.nearbuy.location.dao.model.UserLocation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= ApplicationConfigTest.class)
public class UserLocationDaoTest {


	@Autowired
	private UserLocationDao dao;

	@Test
	public void test1Success(){
		UserLocation dm = new UserLocation();
		dm.setCustomerId("ff");
		dm.setTime(123l);
		List<Double> list = Arrays.asList(22.43, 44.12);
		dm.setLocation(GeoJsonFactory.getPoint(list));
		dao.add(dm);
		GeoJson<List<Double>> loc = dao.getLastLocation("ff");
		Assert.assertNotNull(loc);
		Assert.assertTrue(loc.getCoordinates().get(0).equals(22.43));
	}
}
