package com.itis.merch.core.dto.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public final class RegisterRequestDTO {
	@JsonProperty("first_name")
	@NotNull
	@NotBlank
	private final String firstName;

	@JsonProperty("last_name")
	@NotNull
	@NotBlank
	private final String lastName;

	@JsonProperty("email_address")
	@NotNull
	@NotBlank
	private final String emailAddress;

	@JsonProperty("password")
	@NotNull
	@NotBlank
	private final String password;
}
