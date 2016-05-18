package com.nearbuy.location.dao.model;

import org.bson.types.ObjectId;

public class DemoModel {

	String demo;
	private ObjectId _id;

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

}
