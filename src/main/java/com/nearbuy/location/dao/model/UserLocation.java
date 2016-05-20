package com.nearbuy.location.dao.model;

import java.util.Map;

public class UserLocation {

	/**
	 * longitude,latitude
	 */
	Double [] location;
	String customerId;
	Boolean isUsingApp;
	String actionType;
	Map metaInfo;

	public UserLocation() {
	}

	public UserLocation(Double[] location, String customerId, Boolean isUsingApp, String actionType, Map metaInfo) {
		this.location = location;
		this.customerId = customerId;
		this.isUsingApp = isUsingApp;
		this.actionType = actionType;
		this.metaInfo = metaInfo;
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

	public Double[] getLocation() {
		return location;
	}

	public void setLocation(Double[] location) {
		this.location = location;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
