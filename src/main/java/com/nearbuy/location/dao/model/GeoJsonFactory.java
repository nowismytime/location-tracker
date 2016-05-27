package com.nearbuy.location.dao.model;

import com.mongodb.client.model.geojson.Polygon;
import com.mongodb.client.model.geojson.PolygonCoordinates;
import com.mongodb.client.model.geojson.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by tushar on 25/05/16.
 */
public class GeoJsonFactory {

    public static GeoJson<List<Double>> getPoint(Double[] location) {
        return new GeoJson<List<Double>>("Point", Arrays.asList(location[0], location[1]));
    }

    public static GeoJson<List<List<List<Double>>>> getPolygon(Double[][] coordinates) {
        List<List<Double>> outerCoords = new ArrayList<>();
        for(Double[] points : coordinates){
            List<Double> coord = Arrays.asList(points[0], points[1]);
            outerCoords.add(coord);
        }
        return new GeoJson<List<List<List<Double>>>>("Polygon", Collections.singletonList(outerCoords));
    }

    public static GeoJson<List<Double>> getPoint(List<Double> point) {
        return new GeoJson<List<Double>>("Point", point);
    }

//    public static GeoJson<List<Double[][]>> getPolygon(Double[][] coordinates) {
//        return new GeoJson<List<Double[][]>>("Polygon", Collections.singletonList(coordinates));
//    }

//    public static Polygon getPolygon(Double[][] coordinates) {
//        List<Position> list = new ArrayList<>();
//        for(Double[] points : coordinates){
//            list.add(new Position(points[0], points[1]));
//        }
//        return new Polygon(new PolygonCoordinates(list));
//    }
}
