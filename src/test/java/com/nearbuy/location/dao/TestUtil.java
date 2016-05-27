package com.nearbuy.location.dao;

import com.nearbuy.location.dao.model.GeoJson;
import com.nearbuy.location.dao.model.GeoJsonFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tushar on 27/05/16.
 */
public class TestUtil {
    public static GeoJson<List<List<List<Double>>>> getTestPolygon1() {
        return GeoJsonFactory.getPolygon(new Double[][]
//                Sita apartments
                        {{77.133750, 28.716911},
                                {77.134160, 28.717277},
                                {77.134846, 28.716968},
                                {77.134359, 28.716511},
                                {77.133750, 28.716911}
                        }
        );
    }

    public static GeoJson<List<List<List<Double>>>> getTestPolygon2() {
        return GeoJsonFactory.getPolygon(new Double[][]
                        {
//                        RG Complex
                                {77.134096, 28.715755},
                                {77.134650, 28.716316},
                                {77.135580, 28.715745},
                                {77.134996, 28.715081},
                                {77.134096, 28.715755}
                        }
        );
    }

    public static GeoJson<List<Double>> getTestPolygon2Center() {
//        RG center
        return GeoJsonFactory.getPoint(Arrays.asList(77.134795, 28.715543));
    }
}
