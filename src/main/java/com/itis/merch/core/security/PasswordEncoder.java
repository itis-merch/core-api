package com.itis.merch.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * This is a configuration class for setting up a BCryptPasswordEncoder bean.
 * It provides a single method for creating a new BCryptPasswordEncoder instance.
 */

@Configuration
public class PasswordEncoder {

	/**
	 * This method creates a new BCryptPasswordEncoder instance.
	 *
	 * @return A new instance of the BCryptPasswordEncoder class.
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
