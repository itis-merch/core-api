package com.itis.merch.core.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * The {@code AppUserRole} enum represents the different roles
 * available to users in the application. Each role is represented
 * by a constant value and has a corresponding set of permissions
 * that define the actions a user with that role can perform in the
 * application.
 */
@AllArgsConstructor
public enum AppUserRole {

	/**
	 * The customer role, which has permission to view categories and
	 * products.
	 */
	CUSTOMER(Set.of(
					AppUserPermission.CATEGORIES_VIEW,
					AppUserPermission.PRODUCTS_VIEW)),

	/**
	 * The admin role, which has permission to view and write (create,
	 * edit, or delete) categories and products.
	 */
	ADMIN(Set.of(
					AppUserPermission.CATEGORIES_VIEW,
					AppUserPermission.CATEGORIES_WRITE,
					AppUserPermission.PRODUCTS_VIEW,
					AppUserPermission.PRODUCTS_WRITE));

	/**
	 * The set of permissions associated with the role.
	 */
	@Getter
	private final Set<AppUserPermission> permissions;

	/**
	 * Returns a set of {@code SimpleGrantedAuthority} objects that
	 * represent the authorities granted to users with this role.
	 * These authorities can be used for authentication and authorization
	 * in the application.
	 *
	 * @return a set of {@code SimpleGrantedAuthority} objects that
	 * represent the authorities granted to users with this role.
	 */
	public Set<SimpleGrantedAuthority> getAuthorities() {
		return getPermissions().stream()
						.map(permissions -> new SimpleGrantedAuthority(permissions.getPermission()))
						.collect(Collectors.toSet());
	}
}
