package com.example.challange;

public class Setup {

	public static void envs() {
		System.setProperty("DEBUG", "false");
		System.setProperty("ACCEPTED_RESOURCES", "people,planets,films,species,vehicles,starships");
		System.setProperty("EDITABLE_RESOURCES", "{\"films\":[\"opening_crawl\"]}");
	}
}
