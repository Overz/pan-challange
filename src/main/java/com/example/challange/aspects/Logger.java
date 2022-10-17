package com.example.challange.aspects;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class Logger {

	@Autowired
	private HttpServletRequest req;

	@Before("execution(* com.example.challange.aspects.routes..*(..))")
	public void beforeValidations(JoinPoint joinPoint) {
		logRequest(joinPoint);
	}

	@Before("execution(* com.example.challange.routes..*(..))")
	public void beforeRoutes(JoinPoint joinPoint) {
		logRequest(joinPoint);
	}

	/**
	 * log every request passed in routes and validations
	 */
	public void logRequest(JoinPoint joinPoint) {
		String msg =
			"[REQUEST_INFO] IP: '{}', METHOD: '{}', PATH: '{}', TARGET: '{}', AUTH_TYPE: '{}', CONTENT_TYPE: '{}', CONTENT_LENGTH: '{}', QUERY: '{}', ARGS: '{}'";
		log.info(
			msg,
			req.getRemoteAddr(),
			req.getMethod(),
			req.getServletPath(),
			joinPoint.toShortString(),
			req.getAuthType(),
			req.getContentType(),
			req.getContentLength(),
			req.getQueryString(),
			joinPoint.getArgs()
		);
	}
}
