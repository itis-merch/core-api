package com.itis.merch.core.dto.product;

import com.itis.merch.core.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * The ProductDTO class is a data transfer object that represents a
 * product in an e-commerce system. It is used to transfer data between
 * the front-end and back-end layers of the application.
 * <p>
 * This class contains fields that correspond to the fields of the
 * {@link Product} entity class, except for the {@code category} field,
 * which is replaced by a {@code categoryId} field.
 * <p>
 * Also, this class is annotated with {@code @Data} which generates all
 * the boilerplate code required for a data class such as getters, setters,
 * equals, hashCode, and toString methods.
 * <p>
 * Each ProductDTO has a name, description, quantity, price,
 * availability, and a category ID to which it belongs.
 * <p>
 * NOTE: need to add {@code getProductFromDTO} in the future {@code
 * ProductService} later.
 */
@Data
@NoArgsConstructor
public final class ProductDTO {

	/*----- Private fields -----*/

	/**
	 * The unique identifier for this product.
	 */
	private Integer id;

	/**
	 * The name of the product.
	 */
	@NotBlank
	@NotNull
	private String name;

	/**
	 * A brief description of the product.
	 */
	@NotBlank
	@NotNull
	private String description;

	/**
	 * The number of units of this product that are currently in stock.
	 */
	@NotNull
	private Integer quantity;

	/**
	 * The price of the product.
	 */
	@NotNull
	private BigDecimal price;

	/**
	 * A boolean value indicating whether the product is currently available
	 * for purchase.
	 */
	@NotNull
	private Boolean available;

	/**
	 * The ID of the category to which this product belongs.
	 */
	@NotNull
	private Integer categoryID;


	/*----- Constructors -----*/

	/**
	 * Constructs a new instance of the {@code ProductDTO} class with the
	 * specified parameters.
	 *
	 * @param id          the ID of the product
	 * @param name        the name of the product, which cannot be blank or null
	 * @param description the description of the product, which cannot be
	 *                    blank or null
	 * @param quantity    the quantity of the product, which cannot be null
	 * @param price       the price of the product, which cannot be null
	 * @param available   a boolean indicating whether the product is available
	 *                    or not, which cannot be null
	 * @param categoryID  the ID of the category to which the product belongs,
	 *                    which cannot be null
	 */
	public ProductDTO(final Integer id, @NotBlank @NotNull final String name,
	                  @NotBlank @NotNull final String description, @NotNull final Integer quantity,
	                  @NotNull final BigDecimal price, @NotNull final Boolean available,
	                  @NotNull final Integer categoryID) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.available = available;
		this.categoryID = categoryID;
	}

	/**
	 * Constructs a new instance of the {@code ProductDTO} class based on the
	 * given non-null {@code Product} object.
	 *
	 * @param product the non-null {@code Product} object to be used as the
	 *                source of data for the new {@code ProductDTO} object
	 */
	public ProductDTO(@NotNull final Product product) {
		this.setId(product.getId());
		this.setName(product.getName());
		this.setDescription(product.getDescription());
		this.setQuantity(product.getQuantity());
		this.setPrice(product.getPrice());
		this.setAvailable(product.getAvailable());
		this.setCategoryID(product.getCategory().getId());
	}

}