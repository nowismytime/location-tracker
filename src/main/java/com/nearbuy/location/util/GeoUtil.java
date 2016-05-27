package com.nearbuy.location.util;

import com.nearbuy.location.dao.model.GeoJson;
import com.sromku.polygon.Point;
import com.sromku.polygon.Polygon;

import java.util.List;

/**
 * Created by tushar on 27/05/16.
 * Uses https://github.com/sromku/polygon-contains-point
 */
public class GeoUtil {

    public static boolean isInside(GeoJson<List<Double>> lastLocation, GeoJson<List<List<List<Double>>>> polygon) {
        Polygon.Builder polygonBuilder = Polygon.Builder();
        for(List<Double> coordinates : polygon.getCoordinates().get(0)){
            /**
             * X - longitude, Y latitude
             * https://locationtech.github.io/spatial4j/apidocs/
             */
            polygonBuilder.addVertex(new Point(coordinates.get(0).floatValue(), coordinates.get(1).floatValue()));
        }
        Polygon _polygon = polygonBuilder.build();
        return _polygon.contains(new Point(lastLocation.getCoordinates().get(0).floatValue(), lastLocation.getCoordinates().get(1).floatValue()));
    }


//    public static boolean isInside(GeoJson<List<Double>> lastLocation, GeoJson<List<List<List<Double>>>> polygon) {
//
//        ShapeFactory.PolygonBuilder polygonBuilder = new ShapeFactoryImpl(JtsSpatialContext.GEO, new JtsSpatialContextFactory()).polygon();
//        for(List<Double> coordinates : polygon.getCoordinates().get(0)){
//            /**
//             * X - longitude, Y latitude
//             * https://locationtech.github.io/spatial4j/apidocs/
//             */
//            polygonBuilder.pointXY(coordinates.get(0), coordinates.get(1));
//
//        }
//        Shape _polygon = polygonBuilder.build();
//        PointImpl point = new PointImpl(lastLocation.getCoordinates().get(0), lastLocation.getCoordinates().get(1), JtsSpatialContext.GEO);
//        return point.relate(_polygon).intersects();
//    }

}
