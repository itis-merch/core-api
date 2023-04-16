package com.itis.merch.core.security;


import com.itis.merch.core.models.AppUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is a UserDetails implementation class for representing a user in the security context.
 * It provides getters for the user's email address, password, and authorities.
 * It also provides methods for checking if the user account is expired, locked, or has expired credentials.
 * Additionally, it provides a static factory method for creating instances of the SecurityUser class.
 */
@Data
public class SecurityUser implements UserDetails {

	/**
	 * The user's email address.
	 */
	private final String emailAddress;
	/**
	 * The user's password.
	 */
	private final String password;
	/**
	 * The user's authorities.
	 */
	private final List<SimpleGrantedAuthority> authorities;

	/**
	 * Returns the user's authorities.
	 *
	 * @return The user's authorities.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * Returns the user's password.
	 *
	 * @return The user's password.
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * Returns the user's email address.
	 *
	 * @return The user's email address.
	 */
	@Override
	public String getUsername() {
		return emailAddress;
	}

	/**
	 * Returns true if the user account is not expired, false otherwise.
	 *
	 * @return True if the user account is not expired, false otherwise.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Returns true if the user account is not locked, false otherwise.
	 *
	 * @return True if the user account is not locked, false otherwise.
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Returns true if the user credentials are not expired, false otherwise.
	 *
	 * @return True if the user credentials are not expired, false otherwise.
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Returns true if the user account is enabled, false otherwise.
	 *
	 * @return True if the user account is enabled, false otherwise.
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * This is a static factory method for creating instances of the SecurityUser class from an instance of the AppUser class.
	 * It takes an instance of the AppUser class and returns a new SecurityUser instance initialized with the user's email address, password, and authorities.
	 *
	 * @param appUser The AppUser instance to create the SecurityUser from.
	 * @return A new instance of the SecurityUser class initialized with the user's email address, password, and authorities.
	 */
	public static SecurityUser fromAppUser(AppUser appUser) {

		return new SecurityUser(appUser.getEmailAddress(), appUser.getPassword(), new ArrayList<>(appUser.getRole().getAuthorities()));
	}
}
