package com.nearbuy.location.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.nearbuy.location.dao.model.UserLocation;

/**
 * Created by Rajat Tulasyan on 21/06/16.
 */

public interface UserLocationRepository extends
		PagingAndSortingRepository<UserLocation, String>,
		CrudRepository<UserLocation, String> {

	@Query(value = "{ $and: [ {time : {$gte:?0}}, "
			+ "{\"location.coordinates.0\": {$gte: ?1}}, "
			+ "{\"location.coordinates.1\": {$gte: ?2}}, "
			+ "{\"location.coordinates.0\": {$lte: ?3}}, "
			+ "{\"location.coordinates.1\": {$lte: ?4}}]}")
	List<UserLocation> find(Long time, Double minLong, Double minLat,
			Double maxLong, Double maxLat);

}
