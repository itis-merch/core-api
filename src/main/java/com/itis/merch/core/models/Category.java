package com.itis.merch.core.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
public final class Category {

	/*----- Private fields -----*/

	/**
	 * The unique identifier for this category. Generated automatically
	 * when a new category is added to the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter @NotBlank private Integer id;

	/**
	 * The name of the category.
	 */
	@Column(name = "name")
	@Getter @Setter @NotBlank private String name;

	/**
	 * A brief description of the category.
	 */
	@Column(name = "description")
	@Getter @Setter @NotBlank private String description;


	/*----- Constructors -----*/

	/**
	 * Constructs a new Category object with the given name and description.
	 *
	 * @param name The name of the category. Must not be blank or null.
	 * @param description The description of the category. Must not be blank
	 *                    or null.
	 */
	public Category(@NotBlank final String name, @NotBlank final String description) {
		this.name = name;
		this.description = description;
	}

}