package com.nearbuy.location.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public class CounterCollectionDao {

    @Autowired
    @Qualifier(value = "counters")
    private MongoCollection counterCollection;

    private static final String ID = "_id";

    private static final String SEQUENCE = "seq";

    public Long getNextSequence(String sequenceName) {
        Document val = (Document) counterCollection.findOneAndUpdate(Filters.eq(ID, sequenceName), Updates.inc(SEQUENCE, 1),
                new FindOneAndUpdateOptions().projection(Projections.include(SEQUENCE)).returnDocument(ReturnDocument.AFTER));
        Object a = val.get(SEQUENCE);
        if(a instanceof Double){
            return val.getDouble(SEQUENCE).longValue();
        }
        if(a instanceof Integer){
            return val.getInteger(SEQUENCE).longValue();
        }
        if(a instanceof Long){
            return val.getLong(SEQUENCE);
        }
        return val.getLong(SEQUENCE);
    }
}
