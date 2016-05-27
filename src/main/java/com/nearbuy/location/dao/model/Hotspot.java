package com.nearbuy.location.dao.model;

import com.mongodb.client.model.geojson.Polygon;

import java.beans.Transient;
import java.util.List;

/**
 * Created by tushar on 26/05/16.
 */
public class Hotspot {

    GeoJson<List<List<List<Double>>>> location;
    Long _id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    transient Double distance;

    public GeoJson<List<List<List<Double>>>> getLocation() {
        return location;
    }

    public void setLocation(GeoJson<List<List<List<Double>>>> location) {
        this.location = location;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }
}
