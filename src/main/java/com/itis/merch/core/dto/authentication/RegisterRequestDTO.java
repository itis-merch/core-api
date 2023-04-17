package com.itis.merch.core.dto.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class RegisterRequestDTO {
	@JsonProperty("first_name")
	@NotNull
	@NotBlank
	private String firstName;

	@JsonProperty("last_name")
	@NotNull
	@NotBlank
	private String lastName;

	@JsonProperty("email_address")
	@NotNull
	@NotBlank
	private String emailAddress;

	@JsonProperty("password")
	@NotNull
	@NotBlank
	private String password;
}
