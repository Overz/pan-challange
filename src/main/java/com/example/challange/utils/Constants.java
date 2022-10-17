package com.example.challange.utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Constants {

	public static final boolean DEBUG = Boolean.parseBoolean(System.getenv("DEBUG"));
	public static final String SW_API_URL = "https://swapi.dev/api";
	public static final List<String> ACCEPTED_RESOURCES = List.of(
		System.getenv("ACCEPTED_RESOURCES")
	);
	public static final Map<String, List<String>> EDITABLE_RESOURCES = Collections.unmodifiableMap(
		Json.jsonToMap(System.getenv("EDITABLE_RESOURCES"))
	);

	private Constants() {}

	public static class Qualifiers {

		public static final String SW_API = "SW_API";

		private Qualifiers() {}
	}
}
