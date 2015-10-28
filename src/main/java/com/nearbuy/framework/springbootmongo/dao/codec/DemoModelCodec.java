package com.nearbuy.framework.springbootmongo.dao.codec;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.nearbuy.framework.springbootmongo.dao.model.DemoModel;
import com.nearbuy.framework.springbootmongo.util.AppUtil;

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
        String json = AppUtil.getJson(value);
        return Document.parse(json);
    }

}
