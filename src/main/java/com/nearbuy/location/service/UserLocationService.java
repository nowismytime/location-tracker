package com.nearbuy.location.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nearbuy.location.dao.model.UserLocation;
import com.nearbuy.location.repository.UserLocationRepository;

/**
 * Created by Rajat Tulasyan on 21/06/16.
 */

@Component
public class UserLocationService {

	@Autowired
	public UserLocationRepository userLocationRepository;

	public List<UserLocation> findUsers(Long duration, double lat, double lng,
			double x) {
		double change = x / 111000;
		double maxLat = lat + change;
		double minLat = lat - change;
		double rlat = (lat * Math.PI) / 180;
		double unitLong = (Math.cos(rlat)) * 111000;
		double minLong = lng - x / unitLong;
		double maxLong = lng + x / unitLong;
		Long time = System.currentTimeMillis() - duration * 1000;
		List<UserLocation> users = userLocationRepository.find(time, minLong,
				minLat, maxLong, maxLat);
		return users;
	}
}
