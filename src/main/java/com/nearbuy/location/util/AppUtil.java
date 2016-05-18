package com.nearbuy.location.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;



public class AppUtil {

	public static final Gson gson;
	
	static{
		
		gson = new Gson();
	}
	public static <T> T getFromJson(String json, Class<T> class1) {
		return new Gson().fromJson(json, class1);
	}

	
	public static <T> T parseJson(String json, Type type) {
		return new Gson().fromJson(json, type);
	}
	
	public static <T> T parseJson(String json, Class<T> type) {
        return new Gson().fromJson(json, type);
    }
	
	public static <T> T parseObject(Object json, Type type) {
        return new Gson().fromJson(getJson(json), type);
    }

	public static String getJson(Object user) {
		return new Gson().toJson(user);
	}
}
