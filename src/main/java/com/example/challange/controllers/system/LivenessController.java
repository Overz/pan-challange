package com.example.challange.controllers.system;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class LivenessController {

	@Value("${spring.application.name}")
	private String name;

	@Value("${mode}")
	private String mode;

	@Value("${version}")
	private String version;

	@Value("${debug}")
	private boolean debug;

	public Map<String, Object> getSystemInfo() {
		Map<String, Object> liveness = new HashMap<>();
		liveness.put("name", name);
		liveness.put("mode", mode);
		liveness.put("version", version);
		liveness.put("debug", debug);
		liveness.put("time", new Date());
		return liveness;
	}
}
