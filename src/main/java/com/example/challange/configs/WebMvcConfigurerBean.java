package com.example.challange.configs;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfigurerBean {

	@Bean
	public WebMvcConfigurer mvcConfigurer(
		@Value("${custom.cors.methods}") List<String> methods,
		@Value("${custom.cors.origins}") List<String> origins,
		@Value("${custom.cors.credentials}") boolean credentials
	) {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
					.addMapping("/**")
					.allowCredentials(credentials)
					.allowedOrigins(String.valueOf(origins))
					.allowedMethods(String.valueOf(methods));
			}
		};
	}
}
