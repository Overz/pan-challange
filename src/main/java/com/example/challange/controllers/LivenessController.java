package com.example.challange.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/system")
public class LivenessController {

	@Value("${spring.application.name}")
	private String name;

	@Value("${mode}")
	private String mode;

	@Value("${version}")
	private String version;

	@Value("${debug}")
	private boolean debug;

	@GetMapping("/liveness")
	public ResponseEntity<Map<String, Object>> liveness() {
		Map<String, Object> liveness = new HashMap<>();
		liveness.put("name", name);
		liveness.put("mode", mode);
		liveness.put("version", version);
		liveness.put("debug", debug);
		liveness.put("time", new Date());
		return ResponseEntity.ok(liveness);
	}
}
