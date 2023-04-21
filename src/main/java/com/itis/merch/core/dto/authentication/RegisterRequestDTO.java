package com.itis.merch.core.dto.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This class represents a data transfer object for a register request, containing
 * information about a user's first name, last name, email address, and password.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class RegisterRequestDTO {

	/*----- Private fields -----*/

	/**
	 * The user's first name.
	 */
	@JsonProperty("first_name")
	@NotNull
	@NotBlank
	private String firstName;

	/**
	 * The user's last name.
	 */
	@JsonProperty("last_name")
	@NotNull
	@NotBlank
	private String lastName;

	/**
	 * The user's email address.
	 */
	@JsonProperty("email_address")
	@NotNull
	@NotBlank
	private String emailAddress;

	/**
	 * The user's password.
	 */
	@JsonProperty("password")
	@NotNull
	@NotBlank
	private String password;
}
