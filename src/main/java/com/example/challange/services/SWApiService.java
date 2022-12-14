package com.example.challange.services;

import static com.example.challange.utils.Constants.SW_API_URL;

import com.example.challange.errors.ServiceRequestError;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class SWApiService {

	@Autowired
	private RestTemplate restTemplate;

	public Map<String, String> getResources() throws RestClientException, ServiceRequestError {
		ResponseEntity<Map> res = restTemplate.exchange(SW_API_URL, HttpMethod.GET, null, Map.class);
		return getBody(res);
	}

	public Map<String, Object> getResourceContent(String url)
		throws RestClientException, ServiceRequestError {
		ResponseEntity<Map> res = restTemplate.exchange(url, HttpMethod.GET, null, Map.class);
		return getBody(res, "Error getting data from '" + url + "'");
	}

	private <T> T getBody(ResponseEntity<?> res) throws ServiceRequestError {
		return getBody(res, HttpStatus.OK, null);
	}

	private <T> T getBody(ResponseEntity<?> res, String msg) throws ServiceRequestError {
		return getBody(res, HttpStatus.OK, msg);
	}

	private <T> T getBody(ResponseEntity<?> res, HttpStatus condition, String msg)
		throws ServiceRequestError {
		if (res.getStatusCode() != condition) {
			throw new ServiceRequestError(res.getStatusCode(), msg);
		}
		return (T) res.getBody();
	}
}
