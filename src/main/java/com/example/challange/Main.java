package com.example.challange;

import com.example.challange.controllers.caches.SimpleCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan(basePackages = { "com.example.challange.**" })
@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		System.setProperty("currentTimeMillis", "" + System.currentTimeMillis());
		SpringApplication.run(Main.class, args);

		try {
			// setup instance and cache;
		  SimpleCache.getInstance();
		} catch (Exception e) {
			log.error("Error setting up cache", e);
			System.exit(1);
		}
	}
}
