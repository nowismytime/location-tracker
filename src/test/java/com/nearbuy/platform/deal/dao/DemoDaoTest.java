//package com.nearbuy.platform.deal.dao;
//
//import junit.framework.Assert;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.nearbuy.framework.springbootmongo.dao.DemoDao;
//import DemoModel;
//import com.nearbuy.platform.deal.config.ApplicationConfigTest;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes= ApplicationConfigTest.class)
//public class DemoDaoTest {
//
//
//	@Autowired
//	private DemoDao dao;
//
//	@Test
//	public void test1Success(){
//		DemoModel dm = new DemoModel();
//		dm.setDemo("ff");
//		String id = dao.insertDemo(dm);
//		Assert.assertEquals(dao.getDemoModel(id).getDemo(), dm.getDemo());
//
//	}
//}
