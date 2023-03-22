package com.itis.merch.core.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * The {@code Category} class represents a category entity in the
 * system. It contains information about a specific category, such
 * as its name and description.
 * <p>
 * The class is annotated with the JPA {@code @Entity} annotation,
 * indicating that it is a persistent entity managed by the system's
 * EntityManager.
 * <p>
 * The {@code @Table} annotation specifies the name of the database
 * table that corresponds to this entity.
 */
@NoArgsConstructor
@Entity
@Table(name = "category")
@Data
public final class Category {

	/*----- Private fields -----*/

	/**
	 * The unique identifier for this category. Generated automatically
	 * when a new category is added to the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Integer id;

	/**
	 * The name of the category.
	 */
	@Column(name = "name")
	@NotBlank
	@NotNull
	private String name;

	/**
	 * A brief description of the category.
	 */
	@Column(name = "description")
	@NotBlank
	@NotNull
	private String description;


	/*----- Constructors -----*/

	/**
	 * Constructs a new Category object with the given name and description.
	 *
	 * @param name        The name of the category. Must not be blank or null.
	 * @param description The description of the category. Must not be blank
	 *                    or null.
	 */
	public Category(@NotBlank @NotNull final String name,
	                @NotBlank @NotNull final String description) {
		this.name = name;
		this.description = description;
	}

}