package com.itis.merch.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * This class represents a custom exception with an associated HTTP status code.
 */
public class CustomException extends Exception {

	/*----- Private fields -----*/

	/**
	 * The HTTP status code associated with the exception.
	 */
	@Getter
	private final HttpStatus httpStatus;


	/*----- Constructors -----*/

	/**
	 * Constructs a new CustomException object with the given message and HTTP status
	 * code.
	 *
	 * @param message    the message to associate with the exception.
	 * @param httpStatus the HTTP status code to associate with the exception.
	 */
	public CustomException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

}
