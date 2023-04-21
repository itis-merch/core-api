package com.itis.merch.core.dto.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * The {@code LoginRequestDTO} class represents a data transfer object used to
 * encapsulate the login request parameters sent from the client to the server.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class LoginRequestDTO {

	/*----- Private fields -----*/

	/**
	 * The email address of the user attempting to log in.
	 */
	@JsonProperty("email_address")
	@NotNull
	@NotBlank
	private String emailAddress;

	/**
	 * The password of the user attempting to log in.
	 */
	@JsonProperty("password")
	@NotNull
	@NotBlank
	private String password;

}
