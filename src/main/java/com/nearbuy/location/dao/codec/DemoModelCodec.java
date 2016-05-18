package com.nearbuy.location.dao.codec;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.nearbuy.location.dao.model.DemoModel;

/**
 * Created by tushar on 27/08/15.
 */
@Component
public class DemoModelCodec extends AppCodec<DemoModel> {


    @Override
    protected DemoModel _decode(Document demoModelDoc) {
    	
    	String demo = demoModelDoc.getString("demo");
    	DemoModel dm = new DemoModel();
    	dm.setDemo(demo);
    	return dm;
    }

    @Override
    protected Document _encode(DemoModel value) {
    	Document doc = new Document(); 
    	doc.put("demo", value.getDemo()); 
    	return doc;
  
    }

}
