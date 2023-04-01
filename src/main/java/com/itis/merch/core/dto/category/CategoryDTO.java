package com.itis.merch.core.dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itis.merch.core.dto.product.ProductDTO;
import com.itis.merch.core.models.Category;
import com.itis.merch.core.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code CategoryDTO} class represents a data transfer object for
 * a category entity. It is used to transfer category data between
 * different layers of an application or across a network.
 * <p>
 * This class contains fields that correspond to the fields of the {@link
 * Category} entity class.
 * <p>
 * Also, this class is annotated with {@code @Data} which generates all
 * the boilerplate code required for a data class such as getters, setters,
 * equals, hashCode, and toString methods.
 * <p>
 * Each CategoryDTO has a name and description.
 * NOTE: need to add {@code getCategoryFromDTO} in the future {@code
 * CategoryService} later.
 */
@Data
@NoArgsConstructor
public final class CategoryDTO {

	/*----- Private fields -----*/

	/**
	 * The id of the category.
	 */
	@JsonProperty("id")
	private Integer id;

	/**
	 * The name of the category.
	 */
	@NotBlank
	@NotNull
	@JsonProperty("name")
	private String name;

	/**
	 * The description of the category.
	 */
	@NotBlank
	@NotNull
	@JsonProperty("description")
	private String description;

	/**
	 * The list of products that belong to this category.
	 */
	@NotNull
	@JsonProperty("products")
	private List<ProductDTO> productDTOs;


	/*----- Constructors -----*/

	/**
	 * Constructs a new {@code CategoryDTO} object with the specified
	 * parameters.
	 *
	 * @param id          the ID of the category
	 * @param name        the name of the category (must not be blank or null)
	 * @param description the description of the category (must not be blank or null)
	 */
	public CategoryDTO(final Integer id, @NotBlank @NotNull final String name, @NotBlank @NotNull final String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.productDTOs = new ArrayList<>();
	}

	/**
	 * Constructs a new {@code CategoryDTO} object using a {@link Category}
	 * entity object.
	 *
	 * @param category the {@link Category} object to convert to a DTO object
	 */
	public CategoryDTO(@NotNull final Category category) {
		this.setId(category.getId());
		this.setName(category.getName());
		this.setDescription(category.getDescription());
		this.productDTOs = new ArrayList<>();
		category.getProducts().forEach(product -> this.productDTOs.add(new ProductDTO(product)));
	}

}
