package com.itis.merch.core.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Represents an option entity in the database.
 * The entity contains an auto-generated identifier, a name, and properties.
 */
@NoArgsConstructor
@Entity
@Table(name = "option")
@Data
public class Option {

	/**
	 * The auto-generated identifier of the option.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Integer id;

	/**
	 * The name of the option.
	 */
	@Column(name = "name")
	@NotBlank
	@NotNull
	private String name;

	/**
	 * The properties of the option.
	 */
	@Column(name = "properties")
	@NotBlank
	@NotNull
	private String properties;

	/**
	 * Constructs a new option with the given name and properties.
	 *
	 * @param name       The name of the option.
	 * @param properties The properties of the option.
	 */
	public Option(@NotNull @NotBlank String name, @NotNull @NotBlank String properties) {
		this.name = name;
		this.properties = properties;
	}
}
