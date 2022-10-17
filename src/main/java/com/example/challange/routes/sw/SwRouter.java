package com.example.challange.routes.sw;

import com.example.challange.controllers.sw.SwController;
import java.util.Collections;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/sw")
public class SwRouter {

	@Autowired
	private SwController ctrl;

	@PostMapping("/reset")
	public ResponseEntity<Object> reset() {
		ctrl.reset();
		return ResponseEntity.ok(Collections.singletonMap("ok", true));
	}

	@GetMapping("/{resource}")
	public ResponseEntity<Object> getResource(@PathVariable("resource") @NonNull String resource) {
		return ResponseEntity.ok(ctrl.findResource(resource));
	}

	@GetMapping("/{resource}/{index}")
	public ResponseEntity<Object> getOneResource(
		@PathVariable("resource") @NonNull String resource,
		@PathVariable("index") @NonNull int index
	) {
		return ResponseEntity.ok(ctrl.findOneResource(resource, index));
	}

	@PutMapping("/{resource}/{index}")
	public ResponseEntity<Object> updateItem(
		@PathVariable("resource") @NonNull String resource,
		@PathVariable("index") @NonNull int index,
		@RequestBody @NonNull @NotEmpty @Valid Map<String, Object> body
	) {
		ctrl.updateOneResource(resource, index, body);
		return ResponseEntity.noContent().build();
	}
}
