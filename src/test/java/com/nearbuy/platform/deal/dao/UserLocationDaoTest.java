package com.nearbuy.platform.deal.dao;

import com.nearbuy.location.dao.UserLocationDao;
import com.nearbuy.location.dao.model.UserLocation;
import com.nearbuy.platform.deal.config.ApplicationConfigTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= ApplicationConfigTest.class)
public class UserLocationDaoTest {


	@Autowired
	private UserLocationDao dao;

	@Test
	public void test1Success(){
		UserLocation dm = new UserLocation();
		dm.setCustomerId("ff");
		String id = dao.add(dm);

	}
}
