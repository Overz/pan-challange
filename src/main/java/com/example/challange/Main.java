package com.example.challange;

import com.example.challange.utils.SimpleCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan(basePackages = { "com.example.challange.**" })
@SpringBootApplication
public class Main {

	@Autowired
	SimpleCache cache;

	public static void main(String[] args) {
		System.setProperty("currentTimeMillis", "" + System.currentTimeMillis());
		SpringApplication.run(Main.class, args);
	}

	@Bean
	InitializingBean init() {
		return () -> {
			try {
				// setup instance and cache;
				cache.setup();
			} catch (Exception e) {
				log.error("Error setting up cache", e);
				System.exit(1);
			}
		};
	}
}
