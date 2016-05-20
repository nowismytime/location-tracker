//package com.nearbuy.location.dao.codec;
//
//import com.nearbuy.location.dao.model.UserLocation;
//import org.bson.Document;
//import org.springframework.stereotype.Component;
//
///**
// * Created by tushar on 27/08/15.
// */
//@Component
//public class DemoModelCodec extends AppCodec<UserLocation> {
//
//
//    @Override
//    protected UserLocation _decode(Document demoModelDoc) {
//
//    	String demo = demoModelDoc.getString("demo");
//    	UserLocation dm = new UserLocation();
//    	dm.setDemo(demo);
//    	return dm;
//    }
//
//    @Override
//    protected Document _encode(UserLocation value) {
//    	Document doc = new Document();
//    	doc.put("demo", value.getDemo());
//    	return doc;
//
//    }
//
//}
