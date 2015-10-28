package com.nearbuy.framework.springbootmongo.dao;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoCollection;
import com.nearbuy.framework.springbootmongo.dao.codec.DemoModelCodec;
import com.nearbuy.framework.springbootmongo.dao.model.DemoModel;

@Component
public class DemoDao extends BaseDao<DemoModel>{

	@Autowired
	private MongoCollection collection;

	@Autowired
	private DemoModelCodec demoModelcodec;
	
	@Override
	   public String insertDemo(DemoModel model) {
	        Document obj = demoModelcodec.getDoc(model);
	        collection.insertOne(obj);
            String modelId = (String) obj.get("_id").toString();
	        model.set_id(new ObjectId(modelId));
	        return modelId;
	    }


	
}
