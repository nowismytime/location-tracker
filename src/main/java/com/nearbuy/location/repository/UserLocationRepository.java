package com.nearbuy.location.repository;

import java.util.ArrayList;

import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.nearbuy.location.dao.model.UserLocation;

public interface UserLocationRepository extends
		PagingAndSortingRepository<UserLocation, String>,
		CrudRepository<UserLocation, String> {
/*
	@Query(value = "{time : {$gte:?0}, \"location.coordinates\" : { $gte:?1, $gte:?2 } }")
	// @Query(value =
	// "{$or:[{time:{$gte:?0}},{time:{$gte:?1}},{time:{$gte:?2}},{time:{$gte:?3}}]}")
	Iterable<UserLocation> findAll(Long time, Double minLong, Double minLat,
			Double maxLong, Double maxLat);*/
	
	//@Query(value = "{ $and: [ {time : {$gte:?0}}, {\"location.coordinates\": { $elemMatch: { $gte: ?1, $gte: ?2 } }}, {\"location.coordinates\": { $elemMatch: { $lte: ?3, $lte: ?4 } }}]}")
	@Query(value = "{ $and: [ {time : {$gte:0}}, "
			+ "{\"location.coordinates.0\": {$gte: ?1}}, "
			+ "{\"location.coordinates.1\": {$gte: ?2}}, "
			+ "{\"location.coordinates.0\": {$lte: ?3}}, "
			+ "{\"location.coordinates.1\": {$lte: ?4}}]}")
	ArrayList<UserLocation> find(Long time, Double minLong, Double minLat, Double maxLong, Double maxLat);
	
	
}

//{ $and: [ {time : {$gte:0}}, {"location.coordinates.0": {$gte: 70}}, {"location.coordinates.1": {$gte: 20}}, {"location.coordinates.0": {$lte:80}}, {"location.coordinates.1": {$lte: 30}}]}