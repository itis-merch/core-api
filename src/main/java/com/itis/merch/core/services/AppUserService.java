package com.itis.merch.core.services;

import com.itis.merch.core.models.AppUser;
import com.itis.merch.core.repositories.AppUserRepository;
import com.itis.merch.core.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class is responsible for providing an implementation of the {@link UserDetailsService}
 * interface, which loads user-specific data for authentication purposes. It uses the {@link
 * AppUserRepository} to retrieve an {@link AppUser} by their email address, and then transforms
 * it to a {@link SecurityUser} instance.
 */
@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

	/**
	 * The repository instance used to retrieve {@link AppUser} instances by email address.
	 */
	private final AppUserRepository appUserRepository;

	/**
	 * Loads a user by their email address, and returns a {@link UserDetails} instance representing them.
	 *
	 * @param username the username identifying the user whose data is required.
	 * @return a {@link UserDetails} instance representing the user with the provided email address.
	 * @throws UsernameNotFoundException if no user is found with the provided email address.
	 */
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final Optional<AppUser> appUser = appUserRepository.findByEmailAddress(username);

		if (appUser.isEmpty()) {
			throw new UsernameNotFoundException("User with provided e-mail address doesn't exist.");
		}

		return SecurityUser.fromAppUser(appUser.get());
	}
}
