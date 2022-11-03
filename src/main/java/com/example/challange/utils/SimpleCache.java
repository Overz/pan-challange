package com.example.challange.utils;

import static com.example.challange.utils.Constants.ACCEPTED_RESOURCES;

import com.example.challange.services.SWApiService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleCache {

	@Autowired
	private SWApiService api;

	@Getter
	private static final Map<String, Object> data = new HashMap<>();

	public void setup() {
		try {
			Map<String, String> resources = api.getResources();
			Set<Map.Entry<String, String>> entrySet = resources.entrySet();

			for (Map.Entry<String, String> entry : entrySet) {
				String key = entry.getKey();
				String url = entry.getValue();
				Map<String, Object> content = api.getResourceContent(url);
				List<Map<String, Object>> results = (List<Map<String, Object>>) content.get("results");
				for (Map<String, Object> item : results) {
					if (!ACCEPTED_RESOURCES.contains(key)) {
						break;
					}

					item.put("version", 0);
				}

				data.put(key, content);
			}
		} catch (Exception e) {
			log.error("Service was not able to request or cache the data", e);
			System.exit(1);
		}
	}

	public void reset() {
		setup();
	}
}
