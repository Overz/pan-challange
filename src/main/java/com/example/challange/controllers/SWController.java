package com.example.challange.controllers;

import static com.example.challange.utils.Pagination.PageRequest;

import com.example.challange.services.SwDatasourceService;
import com.example.challange.utils.Pagination;
import java.util.Collections;
import java.util.Map;
import javax.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/sw")
public class SWController {

	@Autowired
	private SwDatasourceService service;

	@PostMapping("/reset")
	public ResponseEntity<Map<String, Boolean>> reset() {
		return ResponseEntity.ok(Collections.singletonMap("ok", true));
	}

	@GetMapping
	public ResponseEntity<Map<String, Object>> findAll(PageRequest pageRequest) {
		Pagination p = service.findAll(pageRequest);
		return ResponseEntity.ok(p.toDTO());
	}

	@GetMapping("/{resource}")
	public ResponseEntity<Page<Object>> findResource(
		@PathVariable(name = "resource") @NonNull @NotEmpty String resource,
		PageRequest pageRequest
	) {
		service.findResource(resource);
		return ResponseEntity.ok(null);
	}

	@GetMapping("/{resource}/{id}")
	public Object findOneResource(
		@PathVariable("resource") @NonNull @NotEmpty String resource,
		@PathVariable("id") @NonNull @NotEmpty Integer index
	) {
		//		Map<String, SWBaseDTO<?>> cache = SimpleCache.getData();
		//		Map<String, Object> content = (Map<String, Object>) cache.get(resource).getData();
		//		List<Map<String, Object>> results = (List<Map<String, Object>>) content.get("results");
		//		return results.get(index);
		return null;
	}

	@PutMapping("/{resources}/{id}")
	public void updateOneResourceItem(String resource, int index, Map<String, Object> body) {
		//		Map<String, Object> item = (Map<String, Object>) findOneResource(resource, index);
		//
		//		Set<Map.Entry<String, Object>> bodySet = body.entrySet();
		//		for (Map.Entry<String, Object> bodyEntry : bodySet) {
		//			item.computeIfPresent(bodyEntry.getKey(), (k, v) -> bodyEntry.getValue());
		//		}
		//
		//		item.computeIfPresent("version", (k, v) -> ((int) v) + 1);
	}
}
