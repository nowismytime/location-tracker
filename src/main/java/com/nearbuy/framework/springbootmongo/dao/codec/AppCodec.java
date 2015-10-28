package com.nearbuy.framework.springbootmongo.dao.codec;


import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import com.nearbuy.framework.springbootmongo.util.DBUtil;

import java.util.Iterator;

/**
 * Created by tushar on 27/08/15.
 */
public abstract class AppCodec<T> implements Codec<T> {

    @Override
    public T decode(BsonReader reader, DecoderContext decoderContext) {
        Document doc = DBUtil.decode(reader, decoderContext);
        return _decode(doc);
    }

    protected abstract T _decode(Document doc);

    @Override
    public void encode(BsonWriter writer, T value, EncoderContext encoderContext) {
        Document doc = getDoc(value);
        DBUtil.encode(doc, writer, encoderContext);
    }

    public Document getDoc(T value) {
        Document doc = _encode(value);
        Iterator<String> iter = doc.keySet().iterator();
        while (iter.hasNext()){
            if(doc.get(iter.next())==null){
                iter.remove();
            }
        }
        return doc;
    }


    protected abstract Document _encode(T value);

    @Override
    public Class<T> getEncoderClass() {
        return null;
    }
}
