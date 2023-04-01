package com.itis.merch.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class CustomException extends Exception {
	@Getter
	private final HttpStatus httpStatus;

	public CustomException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
}
