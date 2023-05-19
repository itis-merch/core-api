package com.itis.merch.core.services;

import com.itis.merch.core.dto.authentication.LoginRequestDTO;
import com.itis.merch.core.dto.authentication.RegisterRequestDTO;
import com.itis.merch.core.exceptions.CustomException;
import com.itis.merch.core.models.AppUser;
import com.itis.merch.core.models.AppUserRole;
import com.itis.merch.core.repositories.AppUserRepository;
import com.itis.merch.core.security.JWTUtilService;
import com.itis.merch.core.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The {@code AuthenticationService} class provides methods for user registration and
 * authentication. It interacts with the {@code AppUserRepository} to access user data,
 * the {@code AuthenticationManager} to perform user authentication, and the {@code
 * JWTUtilService} to generate authentication tokens.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

	/**
	 * The repository used for accessing user data.
	 */
	private final AppUserRepository appUserRepository;

	/**
	 * The authentication manager used to authenticate users.
	 */
	private final AuthenticationManager authenticationManager;

	/**
	 * The JWT utility service used to generate authentication tokens.
	 */
	private final JWTUtilService jwtUtilService;

	/**
	 * Registers a new user with the given information.
	 *
	 * @param registerRequestDTO the user information to be used for registration.
	 * @throws CustomException If the user with the given email address already exists,
	 *                         an exception with the status.
	 */
	public void register(final RegisterRequestDTO registerRequestDTO) throws CustomException {
		final Optional<AppUser> appUser = appUserRepository.findByEmailAddress(registerRequestDTO.getEmailAddress());

		if (appUser.isPresent()) {
			throw new CustomException("This e-mail address is already taken.", HttpStatus.CONFLICT);
		}

		appUserRepository.save(AppUser.builder()
						.firstName(registerRequestDTO.getFirstName())
						.lastName(registerRequestDTO.getLastName())
						.emailAddress(registerRequestDTO.getEmailAddress())
						.password(
										new PasswordEncoder()
														.bCryptPasswordEncoder()
														.encode(registerRequestDTO.getPassword()))
						.role(AppUserRole.ADMIN)
						.build());
	}

	/**
	 * Authenticates the user with the given credentials and generates a JWT token.
	 *
	 * @param loginRequestDTO The user credentials to be used for authentication.
	 * @return A JWT token generated for the authenticated user.
	 * @throws CustomException If the user credentials are invalid, an exception with the
	 *                         status code {@code HttpStatus.BAD_REQUEST} will be thrown.
	 */
	public String authenticate(final LoginRequestDTO loginRequestDTO) throws CustomException {
		try {
			authenticationManager.authenticate(
							new UsernamePasswordAuthenticationToken(
											loginRequestDTO.getEmailAddress(),
											loginRequestDTO.getPassword()
							)
			);
			final AppUser appUser = getUserByEmailAddress(loginRequestDTO.getEmailAddress());
			return jwtUtilService.generateToken(loginRequestDTO.getEmailAddress(), appUser.getRole().name());
		} catch (BadCredentialsException e) {
			throw new CustomException("You provided bad credentials.", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Retrieves the user with the given email address.
	 *
	 * @param emailAddress the email address of the user to be retrieved.
	 * @return the user with the given email address.
	 * @throws UsernameNotFoundException If the user with the given email address does
	 *                                   not exist, an exception will be thrown.
	 */
	private AppUser getUserByEmailAddress(final String emailAddress) throws UsernameNotFoundException {
		final Optional<AppUser> appUser = appUserRepository.findByEmailAddress(emailAddress);

		// TODO: reduce code duplicate (see AppUserService.java)
		if (appUser.isEmpty()) {
			throw new UsernameNotFoundException("User with provided e-mail address doesn't exist.");
		}

		return appUser.get();
	}
}
