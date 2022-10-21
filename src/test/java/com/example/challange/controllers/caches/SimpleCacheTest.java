package com.example.challange.controllers.caches;

import static com.example.challange.utils.Constants.Qualifiers.SW_API;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.challange.File;
import com.example.challange.Setup;
import com.example.challange.utils.SimpleCache;
import com.example.challange.services.SWApiService;
import com.example.challange.utils.Json;
import java.util.Map;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({ MockitoExtension.class, SpringExtension.class })
class SimpleCacheTest {

	@InjectMocks
	@Qualifier(SW_API)
	SimpleCache cache;

	@Mock
	SWApiService api;

	@BeforeAll
	static void setup() {
		Setup.envs();
	}

	@Test
	@SneakyThrows(Exception.class)
	@DisplayName("should setup the required data in memory correctly")
	void test01() {
		byte[] content = File.readFile("/files/cache.json");
		Map<String, Object> mock = Json.jsonToMap(new String(content));
		when(api.getResourceContent(any(String.class))).thenReturn(mock);
	}
}
