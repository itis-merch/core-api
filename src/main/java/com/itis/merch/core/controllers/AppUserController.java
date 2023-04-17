package com.itis.merch.core.controllers;

import com.itis.merch.core.common.ApiResponse;
import com.itis.merch.core.dto.authentication.AuthenticationResponseDTO;
import com.itis.merch.core.dto.authentication.LoginRequestDTO;
import com.itis.merch.core.dto.authentication.RegisterRequestDTO;
import com.itis.merch.core.exceptions.CustomException;
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
	public ResponseEntity<ApiResponse> register(@RequestBody final RegisterRequestDTO registerRequestDTO) throws CustomException {
		appUserService.register(registerRequestDTO);
		return new ResponseEntity<>(new ApiResponse(true, "You have been registered successfully."), HttpStatus.ACCEPTED);
	}

	@PostMapping
	public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody final LoginRequestDTO loginRequestDTO) throws CustomException {
		String jwtToken = appUserService.authenticate(loginRequestDTO);
		return new ResponseEntity<>(new AuthenticationResponseDTO(loginRequestDTO.getEmailAddress(), jwtToken), HttpStatus.ACCEPTED);
	}
}
