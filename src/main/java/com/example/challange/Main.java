package com.example.challange;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan(basePackages = { "com.example.challange.**" })
@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		log.info("Starting....");
		System.setProperty("currentTimeMillis", "" + System.currentTimeMillis());
		SpringApplication.run(Main.class, args);
	}
}
