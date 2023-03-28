package com.itis.merch.core.controllers;

import com.itis.merch.core.common.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
	@ResponseBody
	@ExceptionHandler(RuntimeException.class)
	public ApiResponse catchException(RuntimeException exception) {
		return new ApiResponse(false, exception.getMessage());
	}
}
