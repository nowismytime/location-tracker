package com.nearbuy.location.dao.codec;

import com.mongodb.client.model.geojson.codecs.PolygonCodec;
import com.nearbuy.location.dao.model.GeoJson;
import com.nearbuy.location.dao.model.Hotspot;
import org.bson.Document;
import org.bson.codecs.DocumentCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tushar on 26/05/16.
 */
@Component
public class HotspotCodec extends AppCodec<Hotspot> {
    private static final String LOCATION = "location";
    private static final String TYPE = "type";
    private static final String COORDINATES = "coordinates";
    private static final String DISTANCE = "distance";

    @Override
    protected Hotspot _decode(Document doc) {
        Hotspot hotspot = new Hotspot();
        hotspot.set_id(doc.getLong("_id"));
        Document loc = (Document) doc.get(LOCATION);
        GeoJson<List<List<List<Double>>>> location = new GeoJson<>();
        location.setType(loc.getString(TYPE));
        location.setCoordinates((List<List<List<Double>>>) loc.get(COORDINATES));
        hotspot.setLocation(location);
        hotspot.setDistance(doc.getDouble(DISTANCE));
        return hotspot;
    }

    @Override
    public Document _encode(Hotspot value) {
        Document doc = new Document();
        doc.put("_id", value.get_id());
        doc.put(LOCATION, getLocationDoc(value.getLocation()));
        return doc;
    }

    private Document getLocationDoc(GeoJson<List<List<List<Double>>>> location) {
        return new Document().append(TYPE, location.getType())
            .append(COORDINATES, location.getCoordinates());
    }
}
