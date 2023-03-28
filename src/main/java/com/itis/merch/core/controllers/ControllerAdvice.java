/**
 * The ControllerAdvice class provides global exception handling for all controllers in the system.
 */
package com.itis.merch.core.controllers;

import com.itis.merch.core.common.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The ControllerAdvice class provides global exception handling for all controllers in the system.
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

	/**
	 * Handles runtime exceptions that occur in any controller in the system and returns an ApiResponse object containing an error message.
	 *
	 * @param exception The RuntimeException that occurred.
	 * @return An ApiResponse object containing a false status and the error message.
	 */
	@ResponseBody
	@ExceptionHandler(RuntimeException.class)
	public ApiResponse catchException(RuntimeException exception) {
		return new ApiResponse(false, exception.getMessage());
	}
}
