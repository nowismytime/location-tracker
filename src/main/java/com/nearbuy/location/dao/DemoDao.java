//package com.nearbuy.framework.springbootmongo.dao;
//
//import org.bson.Document;
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.model.Filters;
//import DemoModelCodec;
//import DemoModel;
//
//@Component
//public class DemoDao {
//
//	@Autowired
//	private MongoCollection collection;
//
//	@Autowired
//	private DemoModelCodec demoModelcodec;
//
//	   public String insertDemo(DemoModel model) {
//	        Document obj = demoModelcodec.getDoc(model);
//	        collection.insertOne(obj);
//            String modelId = (String) obj.get("_id").toString();
//	        model.set_id(new ObjectId(modelId));
//	        return modelId;
//	    }
//
//
//	public DemoModel getDemoModel(String id)
//	{
//	      return (DemoModel)collection.withDocumentClass(DemoModel.class).find(Filters.eq("_id", new ObjectId(id))).first();
//
//
//	}
//}
