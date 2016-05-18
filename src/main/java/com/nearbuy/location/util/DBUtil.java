package com.nearbuy.location.util;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;

import com.mongodb.util.JSON;

/**
 * Created by tushar on 27/08/15.
 */
public class DBUtil {

    private static final DocumentCodec dc = new DocumentCodec();

    public static Document decode(BsonReader reader, DecoderContext decoderContext) {
        return dc.decode(reader, decoderContext);
    }

    public static void encode(Document doc, BsonWriter writer, EncoderContext encoderContext) {
//        doc.keySet().stream().filter(key -> doc.get(key) == null).forEach(doc::remove);
        dc.encode(writer, doc, encoderContext);
    }
    

}
