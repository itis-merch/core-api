package com.itis.merch.core.controllers;

import com.itis.merch.core.common.ApiResponse;
import com.itis.merch.core.dto.authentication.AuthenticationResponseDTO;
import com.itis.merch.core.dto.authentication.LoginRequestDTO;
import com.itis.merch.core.dto.authentication.RegisterRequestDTO;
import com.itis.merch.core.exceptions.CustomException;
import com.itis.merch.core.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The {@code AppUserController} class is a Spring REST controller that handles
 * authentication-related requests.
 * <p>
 * It provides two endpoints for user registration and user login.
 * The class is annotated with {@code @RestController} and {@code @RequestMapping("/auth")},
 * which maps all requests with the /auth path to this controller.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AppUserController {

	/**
	 * The {@code AuthenticationService} used by this controller for authentication-related
	 * operations.
	 */
	private final AuthenticationService authenticationService;

	/**
	 * Handles user registration requests sent to /auth/register endpoint.
	 *
	 * @param registerRequestDTO the request body containing the user's registration details.
	 * @return a {@code ResponseEntity} object containing a {@code ApiResponse} object indicating
	 * the success or failure of the registration process.
	 * @throws CustomException if an error occurs during registration.
	 */
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> register(@RequestBody final RegisterRequestDTO registerRequestDTO) throws CustomException {
		authenticationService.register(registerRequestDTO);
		return new ResponseEntity<>(new ApiResponse(true, "You have been registered successfully."), HttpStatus.ACCEPTED);
	}

	/**
	 * Handles user login requests sent to /auth/login endpoint.
	 *
	 * @param loginRequestDTO the request body containing the user's login credentials.
	 * @return a {@code ResponseEntity} object containing an AuthenticationResponseDTO object with
	 * the user's email address and a JSON Web Token (JWT) used for authentication.
	 * @throws CustomException if an error occurs during authentication.
	 */
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody final LoginRequestDTO loginRequestDTO) throws CustomException {
		String jwtToken = authenticationService.authenticate(loginRequestDTO);
		return new ResponseEntity<>(new AuthenticationResponseDTO(loginRequestDTO.getEmailAddress(), jwtToken), HttpStatus.ACCEPTED);
	}
}
