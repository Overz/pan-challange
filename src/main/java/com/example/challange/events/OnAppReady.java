package com.example.challange.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OnAppReady {

	@Value("${spring.application.name}")
	private String name;

	@Value("${server.port}")
	private int port;

	@Value("${mode}")
	private String mode;

	@Value("${debug}")
	private boolean debug;

	@EventListener(ApplicationReadyEvent.class)
	public void beforeRun(ApplicationReadyEvent evt) {
		log.info(
			"[{}] Running on port: '{}', mode: '{}', debug: '{}', time taken: '{}ms'",
			name,
			port,
			mode.toUpperCase(),
			debug,
			evt.getTimeTaken()
		);
	}
}
