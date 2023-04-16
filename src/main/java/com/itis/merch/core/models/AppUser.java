package com.itis.merch.core.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The AppUser class represents a user entity in a database. This
 * class uses Java annotations for Object-Relational Mapping (ORM)
 * to map its fields to corresponding columns in the database table
 * {@code user}.
 */
@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

	/*----- Private fields -----*/

	/**
	 * The unique identifier for the user entity.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * The first name of the user.
	 */
	@Column(name = "first_name")
	@NotNull
	@NotBlank
	private String firstName;

	/**
	 * The last name of the user.
	 */
	@Column(name = "last_name")
	@NotNull
	@NotBlank
	private String lastName;

	/**
	 * The email address of the user.
	 */
	@Column(name = "email_address")
	@NotNull
	@NotBlank
	private String emailAddress;

	/**
	 * The password of the user.
	 */
	@Column(name = "password")
	@NotNull
	@NotBlank
	private String password;

	/**
	 * The role of the user in the application.
	 */
	@Column(name = "role")
	@Enumerated(value = EnumType.STRING)
	@NotNull
	private AppUserRole role;

}
