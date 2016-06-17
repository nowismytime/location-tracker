package com.nearbuy.location.dao.model;

import com.nearbuy.location.util.AppUtil;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userLocation")
public class UserLocation {

	GeoJson<List<Double>> location;
	String customerId;
	Boolean isUsingApp;
	String actionType;
	Map<String, Object> metaInfo;
	Long time;

	public UserLocation() {
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public UserLocation(Double[] location, String customerId, Boolean isUsingApp, String actionType, Map metaInfo) {
		this.location = GeoJsonFactory.getPoint(location);
		this.customerId = customerId;
		this.isUsingApp = isUsingApp;
		this.actionType = actionType;
		this.metaInfo = metaInfo;
		time = AppUtil.currentTime();

	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Map getMetaInfo() {
		return metaInfo;
	}

	public void setMetaInfo(Map metaInfo) {
		this.metaInfo = metaInfo;
	}

	public Boolean getUsingApp() {
		return isUsingApp;
	}

	public void setUsingApp(Boolean usingApp) {
		isUsingApp = usingApp;
	}

	public GeoJson<List<Double>> getLocation() {
		return location;
	}

	public void setLocation(GeoJson<List<Double>> location) {
		this.location = location;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
