package com.example.challange.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Classe utilizada para lançamentos de exceções
 * de serviços http
 */
@Getter
public class ServiceRequestError extends Exception {

	private final HttpStatus status;

	public ServiceRequestError(HttpStatus status) {
		super();
		this.status = status;
	}

	public ServiceRequestError(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}
}
