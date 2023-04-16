package com.itis.merch.core.security;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

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

	public static SecurityUser fromAppUser() {
		//TODO

		return null;
	}
}
