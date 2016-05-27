package com.nearbuy.location.util;

import org.bson.BsonDocument;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.conversions.Bson;
import org.springframework.hateoas.alps.Doc;

import java.util.List;

/**
 * Created by tushar on 26/05/16.
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

    public static Bson geoNearBson(List<Double> coordinates, int numberOfDocs) {
        Document point = new Document("type", "Point").append("coordinates", coordinates);
        Document geoNear = new Document("near", point)
                .append("distanceField", "distance")
                .append("num", numberOfDocs)
                .append("spherical", true)
                .append("distanceMultiplier", 1);
        return new Document("$geoNear", geoNear);
    }
}
