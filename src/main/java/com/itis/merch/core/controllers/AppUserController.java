package com.itis.merch.core.controllers;

import com.itis.merch.core.common.ApiResponse;
import com.itis.merch.core.dto.authentication.RegisterRequestDTO;
import com.itis.merch.core.services.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AppUserController {
	private final AppUserService appUserService;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse> register(@RequestBody final RegisterRequestDTO registerRequestDTO) {
		appUserService.register(registerRequestDTO);
		return new ResponseEntity<>(new ApiResponse(true, "You have been registered successfully."), HttpStatus.ACCEPTED);
	}
}
