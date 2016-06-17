package com.nearbuy.location.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.nearbuy.location.dao.model.UserLocation;


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

	public static String getJson(Object source) {
		return new Gson().toJson(source);
	}

	public static byte[] getJsonBytes(Object source) {
		return getJson(source).getBytes();
	}

	public static Long currentTime() {
		return System.currentTimeMillis();
	}
	
	public static JSONArray getJsonArray(ArrayList<UserLocation> users)
	{
		JSONArray result = new JSONArray(users);
		return result;
	}
}
