package com.example.challange.controllers;

import com.example.challange.Setup;
import com.example.challange.controllers.caches.SimpleCache;
import com.example.challange.services.impl.SWApiServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith({ MockitoExtension.class, SpringExtension.class })
class SwControllerTest extends Assertions {

	@InjectMocks
	SimpleCache cache;

	@Mock
	SWApiServiceImpl rest;

	@BeforeAll
	static void setup() {
		Setup.envs();
		//		SimpleCache.getInstance();
	}

	@Test
	@SneakyThrows(Exception.class)
	@DisplayName("")
	void test01() {
		System.out.println("batata");
	}
}
