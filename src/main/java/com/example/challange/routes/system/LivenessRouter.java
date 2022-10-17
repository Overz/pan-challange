package com.example.challange.routes.system;

import com.example.challange.controllers.system.LivenessController;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/system")
public class LivenessRouter {

	@Autowired
	private LivenessController ctrl;

	@GetMapping("/liveness")
	public ResponseEntity<Map<String, Object>> liveness() {
		return ResponseEntity.ok(ctrl.getSystemInfo());
	}
}
