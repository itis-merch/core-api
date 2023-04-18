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

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {
	private final AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AppUser> appUser = appUserRepository.findByEmailAddress(username);

		if (appUser.isEmpty()) {
			throw new UsernameNotFoundException("User with provided e-mail address doesn't exist.");
		}

		return SecurityUser.fromAppUser(appUser.get());
	}
}
