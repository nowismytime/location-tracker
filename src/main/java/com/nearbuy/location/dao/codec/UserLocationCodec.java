package com.nearbuy.location.dao.codec;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.nearbuy.location.dao.model.GeoJson;
import com.nearbuy.location.dao.model.GeoJsonFactory;
import com.nearbuy.location.dao.model.UserLocation;
import org.bson.BsonDocument;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tushar on 27/08/15.
 */
@Component
public class UserLocationCodec extends AppCodec<UserLocation> {


    private static final String CUSTOMER_ID = "customerId";
    private static final String IS_USING_APP = "isUsingApp";
    private static final String ACTION_TYPE = "actionType";
    private static final String META_INFO = "metaInfo";
    private static final String LOCATION = "location";
    private static final String TYPE = "type";
    private static final String COORDINATES = "coordinates";
    private static final String TIME = "time";

    @Override
    protected UserLocation _decode(Document doc) {
    	UserLocation userLocation = new UserLocation();
        userLocation.setCustomerId(doc.getString(CUSTOMER_ID));
        userLocation.setActionType(doc.getString(ACTION_TYPE));
        if(doc.get(META_INFO)!=null){
//            dm.setMetaInfo();
        }
        userLocation.setUsingApp(doc.getBoolean(IS_USING_APP));
        Document loc = (Document) doc.get(LOCATION);
        if(loc!=null) {
            userLocation.setLocation(GeoJsonFactory.getPoint((List<Double>) loc.get(COORDINATES)));
        }
        userLocation.setTime(doc.getLong(TIME));
    	return userLocation;
    }

    @Override
    protected Document _encode(UserLocation value) {
    	Document doc = new Document();
    	doc.put(CUSTOMER_ID, value.getCustomerId());
    	doc.put(ACTION_TYPE, value.getActionType());
        if(value.getMetaInfo()!=null) {
            doc.put(META_INFO, new Document(value.getMetaInfo()));
        }
    	doc.put(IS_USING_APP, value.getUsingApp());
        if(value.getLocation()!=null) {
            Document location = new Document().append(TYPE, value.getLocation().getType())
                    .append(COORDINATES, value.getLocation().getCoordinates());
            doc.put(LOCATION, location);
        }
        doc.put(TIME, value.getTime());
    	return doc;

    }

}
