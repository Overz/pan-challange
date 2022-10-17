package com.example.challange.controllers.caches;

import com.example.challange.services.impl.SWApiServiceImpl;
import com.example.challange.services.interfaces.SWApiService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.challange.utils.Constants.ACCEPTED_RESOURCES;

@Slf4j
//@Component
public class SimpleCache {

//	@Autowired
//	@Qualifier(SW_API)
//	private SWApiService api;

	private static SimpleCache instance;

	@Getter
	private final Map<String, BaseCache<?>> data = new HashMap<>();

	private SimpleCache() {
		setup();
	}

	public static SimpleCache getInstance() {
		if (instance == null) {
			instance = new SimpleCache();
		}
		return instance;
	}

	private void setup() {
		try {
			SWApiService api = new SWApiServiceImpl(new RestTemplate());
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

				data.put(key, new BaseCache<>(url, content));
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
