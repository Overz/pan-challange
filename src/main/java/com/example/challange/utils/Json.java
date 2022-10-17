package com.example.challange.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Json {

	private static final Gson gson = new GsonBuilder()
		//		.setDateFormat("dd/MM/yyyy HH:mm:ss")
		.setPrettyPrinting()
		.serializeNulls()
		.create();

	private Json() {}

	public static String toJson(Object o, Class<?> cls) {
		return gson.toJson(o, cls);
	}

	public static String toJson(Object o) {
		return gson.toJson(o);
	}

	public static <T> T fromJson(String json, Class<T> cls) {
		return gson.fromJson(json, cls);
	}

	public static Object fromJson(String json) {
		return gson.fromJson(json, Object.class);
	}

	public static <T> String listToJson(List<T> l) {
		return gson.toJson(l);
	}

	public static <T> T[] jsonToArray(String json, Class<T[]> cls) {
		TypeToken<?> t = TypeToken.get(cls);
		return gson.fromJson(json, t.getType());
	}

	public static <T> List<T> jsonArrayToList(String json, Class<T[]> cls) {
		T[] tmp = jsonToArray(json, cls);
		List<T> l = new ArrayList<>();
		Collections.addAll(l, tmp);
		return l;
	}

	public static <K, T> Map<K, T> jsonToMap(String json) {
		Type type = new TypeToken<Map<K, T>>() {}.getType();
		return gson.fromJson(json, type);
	}
}
