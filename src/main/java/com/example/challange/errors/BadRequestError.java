package com.example.challange.errors;

import lombok.Getter;

/**
 * Classe utilizada para lançamento de exceções
 * por parte do client durante a validação de dados.
 */
@Getter
public class BadRequestError extends RuntimeException {

	private final String message;

	public BadRequestError(String message) {
		super();
		this.message = message;
	}
}
