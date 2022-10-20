package com.example.challange.controllers.sw;

import com.example.challange.controllers.caches.BaseCache;
import com.example.challange.controllers.caches.SimpleCache;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class SwController {

	@Autowired
	private SimpleCache simpleCache;

	/**
	 * reset the cache
	 */
	public void reset() {
		simpleCache.reset();
	}

	/**
	 * Search and return previously cached resource
	 * <p>
	 * If dosen't exists, an error will be throwed
	 * when passing in the aspect validator
	 *
	 * @see com.example.challange.aspects.routes.SwValidations validation
	 */
	public Object findResource(String resource) {
		return SimpleCache.getData().get(resource).getData();
	}

	/**
	 * Search and return previosly cached resource
	 * looking for specific position inside the results.
	 * <p>
	 * If dosen't exists, an error will be throwed
	 * when passing in the aspect validator
	 *
	 * @see com.example.challange.aspects.routes.SwValidations validation
	 */
	public Object findOneResource(String resource, int index) {
		Map<String, BaseCache<?>> cache = SimpleCache.getData();
		Map<String, Object> content = (Map<String, Object>) cache.get(resource).getData();
		List<Map<String, Object>> results = (List<Map<String, Object>>) content.get("results");
		return results.get(index);
	}

	/**
	 * Update a single item inside the resource list.
	 *
	 * It will look if is able to modify the content
	 * using aspect validation, otherwise an error will be throwed.
	 *
	 * @see com.example.challange.aspects.routes.SwValidations validation
	 */
	public void updateOneResource(String resource, int index, Map<String, Object> body) {
		Map<String, Object> item = (Map<String, Object>) findOneResource(resource, index);

		Set<Map.Entry<String, Object>> bodySet = body.entrySet();
		for (Map.Entry<String, Object> bodyEntry : bodySet) {
			item.computeIfPresent(bodyEntry.getKey(), (k, v) -> bodyEntry.getValue());
		}

		item.computeIfPresent("version", (k, v) -> ((int) v) + 1);
	}
}
