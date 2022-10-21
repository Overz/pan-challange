package com.example.challange.aspects.routes;

import static com.example.challange.utils.Constants.DEBUG;
import static com.example.challange.utils.Constants.EDITABLE_RESOURCES;

import com.example.challange.utils.SimpleCache;
import com.example.challange.entities.SWBaseDTO;
import com.example.challange.errors.BadRequestError;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class SwValidations {

	//	@Before(
	//		value = "execution(* com.example.challange.routes.sw.SwRouter.getResource(..)) && args(resource)",
	//		argNames = "resource"
	//	)
	public void getSwResource(String resource) throws BadRequestError {
		checkResource(resource);
	}

	//	@Before(
	//		value = "execution(* com.example.challange.routes.sw.SwRouter.getOneResource(..)) && args(resource, index)",
	//		argNames = "resource,index"
	//	)
	public void getSwOneResource(String resource, Integer index) throws BadRequestError {
		checkResource(resource, index, false);
	}

	//	@Before(
	//		value = "execution(* com.example.challange.routes.sw.SwRouter.updateItem(..)) && args(resource, index, body)",
	//		argNames = "resource,index,body"
	//	)
	public void swValidateRequestBody(String resource, Integer index, Map<String, Object> body)
		throws BadRequestError {
		checkResource(resource, index, false);
		validateBody(resource, body);
	}

	/**
	 * check the resource before get inside the route
	 * and validate if is able to do something.
	 * <p>
	 * will return the found content item if needed
	 */
	private Map<String, Object> checkResource(String resource) throws BadRequestError {
		return checkResource(resource, null, false);
	}

	/**
	 * check the resource before get inside the route
	 * and validate if is able to do something.
	 * <p>
	 * will return the found content item if needed
	 */
	private Map<String, Object> checkResource(String resource, Integer index, boolean canGet)
		throws BadRequestError {
		if (DEBUG) {
			return Collections.emptyMap();
		}

		Map<String, SWBaseDTO<?>> cache = SimpleCache.getData();
		if (!cache.containsKey(resource)) {
			throw new BadRequestError("resource '" + resource + "' could not be found!");
		}

		Map<String, Object> item = null;
		if (index != null) {
			Map<String, Object> content = (Map<String, Object>) cache.get(resource).getData();
			List<Map<String, Object>> results = (List<Map<String, Object>>) content.get("results");

			if (index < 0 || index > results.size()) {
				throw new BadRequestError("element with index '" + index + "' could not be found!");
			}

			if (canGet) item = results.get(index);
		}

		return item;
	}

	/**
	 * check if the body is able to be sended to the route.
	 * <p>
	 * otherwise will throw error if some fields is not able
	 * to be modified in the resource requested.
	 */
	private void validateBody(String resource, Map<String, Object> body) throws BadRequestError {
		if (DEBUG) {
			return;
		}

		List<String> keys = EDITABLE_RESOURCES.get(resource);
		if (keys == null || keys.isEmpty()) {
			throw new BadRequestError("Resource '" + resource + "' is not able to modifications!");
		}

		Set<Map.Entry<String, Object>> bodySet = body.entrySet();
		for (Map.Entry<String, Object> bodyEntry : bodySet) {
			String key = bodyEntry.getKey();
			if (!keys.contains(key)) {
				throw new BadRequestError("the request is not able to edit the content of '" + key + "'");
			}
		}
	}
}
