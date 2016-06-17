package com.nearbuy.location.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nearbuy.location.dao.model.UserLocation;
import com.nearbuy.location.repository.UserLocationRepository;

@Component
public class UserLocationService {
	
	@Autowired
	public UserLocationRepository userLocationRepository;
	
	/*public Iterable<UserLocation> findUsers(Long time, double minLong, double minLat, double maxLong, double maxLat)
	{
		Iterable<UserLocation> findAll = userLocationRepository.findAll(time, minLong, minLat, maxLong, maxLat);
		return findAll;
		
	}*/
	
	public ArrayList<UserLocation> findUsersBox(Long time, double minLong, double minLat, double maxLong, double maxLat)
	{
		ArrayList<UserLocation> users = userLocationRepository.find(time, minLong, minLat, maxLong, maxLat);
		return users;
	}
}
