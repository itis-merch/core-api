package com.itis.merch.core.dto.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * The {@code AuthenticationResponseDTO} class represents a data transfer object (DTO)
 * for authentication response. It contains two fields, {@code emailAddress} and {@code
 * jwtToken}, both of which are annotated with {@code @JsonProperty} to specify their
 * JSON property names when serialized and deserialized.
 */
@Data
public final class AuthenticationResponseDTO {

	/*----- Private fields -----*/

	/**
	 * User's email address, which generated JWT token belongs to.
	 */
	@JsonProperty("email_address")
	@NotNull
	@NotBlank
	private final String emailAddress;

	/**
	 * JWT token itself.
	 */
	@JsonProperty("jwt_token")
	@NotNull
	@NotBlank
	private final String jwtToken;

}
