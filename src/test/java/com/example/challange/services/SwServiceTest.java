package com.example.challange.services;

import com.example.challange.File;
import com.example.challange.Setup;
import com.example.challange.utils.Json;
import java.net.URI;
import java.util.Map;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

@ExtendWith({ MockitoExtension.class, SpringExtension.class })
class SwServiceTest extends Assertions {

	@InjectMocks
	SWApiService api;

	@Mock
	RestTemplate rest;

	private MockRestServiceServer mockServer;

	@BeforeAll
	static void setup() {
		Setup.envs();
	}

	@BeforeEach
	public void beforeEach() {
		mockServer = MockRestServiceServer.createServer(rest);
	}

	@SneakyThrows(Exception.class)
	byte[] getRestResponse() {
		return File.readFile("/files/films.json");
	}

	@SneakyThrows(Exception.class)
	String getRestResponseStringMock() {
		return new String(getRestResponse());
	}

	@SneakyThrows(Exception.class)
	Map<String, Object> getRestResponseMapMock() {
		return Json.jsonToMap(getRestResponseStringMock());
	}

	@Test
	@SneakyThrows(Exception.class)
	@DisplayName("should return the resource content when is trying to get")
	void test01() {
		Map<String, Object> mock = getRestResponseMapMock();
		String url = "http://batata.com";

		mockServer
			.expect(ExpectedCount.once(), MockRestRequestMatchers.requestTo(new URI(url)))
			.andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
			.andRespond(
				MockRestResponseCreators
					.withStatus(HttpStatus.OK)
					.contentType(MediaType.APPLICATION_JSON)
					.body(getRestResponse())
			);

		//		Mockito.when(
		//			rest.exchange(
		//				Mockito.any(String.class),
		//				Mockito.eq(HttpMethod.POST),
		//				Mockito.isNull(),
		//				Mockito.<Class<Map>>any()
		//			)
		//		)
		//			.thenReturn(new ResponseEntity<>(mock, HttpStatus.OK));

		Map<String, Object> resource = api.getResourceContent(url);
		assertNotNull(resource);
	}
}
