package com.nearbuy.location.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

/**
 * Created by tushar on 26/05/16.
 */
public class BasicDao<T> {

    private Class<T> clazz;
    private MongoCollection collection;

    void init(Class<T> tClass, MongoCollection collection){
        this.clazz = tClass;
        this.collection = collection;
    }

    public T findById(Object id){
        return (T) collection.withDocumentClass(clazz).find(Filters.eq("_id", id)).first();
    }

//    public void insert(T t){
//        collection.withDocumentClass(clazz).insertOne(t);
//    }


}
