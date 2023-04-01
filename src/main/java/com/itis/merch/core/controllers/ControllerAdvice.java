package com.itis.merch.core.controllers;

import com.itis.merch.core.common.ApiResponse;
import com.itis.merch.core.exceptions.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The ControllerAdvice class provides global exception handling for all controllers in the system.
 * <p>
 * It is annotated with @ControllerAdvice to indicate that it is a global exception handler for all controllers.
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

	/**
	 * Handles runtime exceptions that occur in any controller in the system and returns an ApiResponse object containing an error message.
	 *
	 * @param customException The CustomException that occurred and needs to be caught.
	 * @return An ApiResponse object containing a false status and the error message of the exception.
	 * The method is annotated with @ExceptionHandler to indicate that it handles exceptions of the specified type.
	 * The method is also annotated with @ResponseBody to indicate that it returns a response body that will be written directly to the response.
	 */
	@ResponseBody
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ApiResponse> catchException(final CustomException customException) {
		return new ResponseEntity<>(new ApiResponse(false, customException.getMessage()), customException.getHttpStatus());
	}
}
