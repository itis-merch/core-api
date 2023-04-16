package com.itis.merch.core.repositories;

import com.itis.merch.core.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
	/**
	 * Returns the user with the specified email address.
	 *
	 * @param emailAddress the email address of the user to find.
	 * @return the user with the specified email address, or null if no user is found.
	 */
	AppUser findByEmailAddress(final String emailAddress);
}
