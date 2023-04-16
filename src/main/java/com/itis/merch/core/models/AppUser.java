package com.itis.merch.core.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "first_name")
	@NotNull
	@NotBlank
	private String firstName;

	@Column(name = "last_name")
	@NotNull
	@NotBlank
	private String lastName;

	@Column(name = "email_address")
	@NotNull
	@NotBlank
	private String emailAddress;

	@Column(name = "password")
	@NotNull
	@NotBlank
	private String password;

	@Column(name = "role")
	@Enumerated(value = EnumType.STRING)
	@NotNull
	private AppUserRole role;
}
