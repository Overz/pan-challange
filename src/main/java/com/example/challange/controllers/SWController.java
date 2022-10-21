package com.example.challange.controllers;

import com.example.challange.entities.SWDto;
import com.example.challange.repositories.SwRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.Collections;
import java.util.Map;

@Slf4j
@RequestMapping("/v1/sw")
@RestController
public class SWController {

	@Autowired
	private SwRepository repo;

	@PostMapping("/reset")
	public ResponseEntity<Map<String, Boolean>> reset() {
		return ResponseEntity.ok(Collections.singletonMap("ok", true));
	}

	@GetMapping
	public ResponseEntity<Object> findAll(
		@RequestParam(name = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize,
		@RequestParam(name = "sort", required = false) String sort
	) {

		return ResponseEntity.ok().build();
	}

	@GetMapping("/{resource}")
	public ResponseEntity<Page<SWDto>> findResource(@PathVariable @NonNull @NotEmpty String resource) {
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
