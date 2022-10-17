package com.example.challange.services.interfaces;

import com.example.challange.errors.ServiceRequestError;
import org.springframework.web.client.RestClientException;

import java.util.Map;

public interface SWApiService {
	Map<String, String> getResources() throws RestClientException, ServiceRequestError;

	Map<String, Object> getResourceContent(String path)
		throws RestClientException, ServiceRequestError;
}
