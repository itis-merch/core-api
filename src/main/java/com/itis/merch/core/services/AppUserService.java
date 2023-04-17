package com.itis.merch.core.services;

import com.itis.merch.core.dto.authentication.LoginRequestDTO;
import com.itis.merch.core.dto.authentication.RegisterRequestDTO;
import com.itis.merch.core.exceptions.CustomException;
import com.itis.merch.core.models.AppUser;
import com.itis.merch.core.models.AppUserRole;
import com.itis.merch.core.repositories.AppUserRepository;
import com.itis.merch.core.security.JWTUtilService;
import com.itis.merch.core.security.PasswordEncoder;
import com.itis.merch.core.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {
	private final AppUserRepository appUserRepository;
	private final AuthenticationManager authenticationManager;
	private final JWTUtilService jwtUtilService;

	public void register(final RegisterRequestDTO registerRequestDTO) throws CustomException {
		Optional<AppUser> appUser = appUserRepository.findByEmailAddress(registerRequestDTO.getEmailAddress());

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
						.role(AppUserRole.CUSTOMER)
						.build());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return SecurityUser.fromAppUser(getUserByEmailAddress(username));
	}

	private AppUser getUserByEmailAddress(String emailAddress) {
		Optional<AppUser> appUser = appUserRepository.findByEmailAddress(emailAddress);

		if (appUser.isEmpty()) {
			throw new UsernameNotFoundException("User with provided e-mail address doesn't exist.");
		}

		return appUser.get();
	}

	public String authenticate(final LoginRequestDTO loginRequestDTO) throws CustomException {
		try {
			authenticationManager.authenticate(
							new UsernamePasswordAuthenticationToken(
											loginRequestDTO.getEmailAddress(),
											loginRequestDTO.getPassword()
							)
			);
			AppUser appUser = getUserByEmailAddress(loginRequestDTO.getEmailAddress());
			return jwtUtilService.generateToken(loginRequestDTO.getEmailAddress(), appUser.getRole().name());
		} catch (BadCredentialsException e) {
			throw new CustomException("You provided bad credentials.", HttpStatus.BAD_REQUEST);
		}
	}
}
