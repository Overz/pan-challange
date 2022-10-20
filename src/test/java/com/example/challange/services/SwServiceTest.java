package com.example.challange.controllers;

import com.example.challange.File;
import com.example.challange.Setup;
import com.example.challange.controllers.caches.BaseCache;
import com.example.challange.controllers.caches.ContentCache;
import com.example.challange.controllers.caches.ResultCache;
import com.example.challange.controllers.caches.SimpleCache;
import com.example.challange.services.impl.SWApiServiceImpl;
import com.example.challange.utils.Json;
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

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({ MockitoExtension.class, SpringExtension.class })
class SwControllerTest extends Assertions {

	@InjectMocks
	SimpleCache cache;

	@Mock
	SWApiServiceImpl api;

	@BeforeAll
	static void setup() {
		Setup.envs();
	}

	@Test
	@SneakyThrows(Exception.class)
	@DisplayName("should return the content when ")
	void test01() {
		byte[] content = File.readFile("/cache/mock.json");
		Map<String, Object> mock = Json.jsonToMap(new String(content));
		when(api.getResourceContent(any(String.class))).thenReturn(mock);
	}
}
