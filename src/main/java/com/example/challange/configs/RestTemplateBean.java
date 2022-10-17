package com.example.challange.configs;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateBean {

	@Bean
	public RestTemplate restTemplate(
		RestTemplateBuilder builder,
		@Value("${custom.rest.connection.request-timeout}") int reqTimeout,
		@Value("${custom.rest.connection.response-timeout}") int resTimeout
	) {
		Duration req = Duration.ofMillis(reqTimeout);
		Duration res = Duration.ofMillis(resTimeout);
		return builder.setConnectTimeout(req).setReadTimeout(res).build();
	}
}
