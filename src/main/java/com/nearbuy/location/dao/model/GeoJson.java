package com.nearbuy.location.dao.model;

/**
 * Created by tushar on 25/05/16.
 */
public class GeoJson<T> {

    String type;
    T coordinates;

    public GeoJson() {
    }

    public GeoJson(String type, T coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(T coordinates) {
        this.coordinates = coordinates;
    }
}
