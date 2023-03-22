package com.itis.merch.core.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * The Product class represents a product in an e-commerce system.
 * <p>
 * This class is annotated with the JPA annotations {@code @Entity}
 * and {@code @Table}, indicating that it is a JPA entity and that
 * it should be mapped to a database table called "product".
 * <p>
 * The class contains a ManyToOne association with the {@code Category}
 * class, indicating that a product belongs to a single category.
 * This association is annotated with the {@code @ManyToOne}, {@code
 * @JoinColumn}, and {@code @NotBlank} annotations.
 * <p>
 * Each product has a name, description, image URL, quantity, price,
 * availability, and a category to which it belongs.
 */
@NoArgsConstructor
@Entity
@Table(name = "product")
public final class Product {

	/*----- Private fields -----*/

	/**
	 * The unique identifier for this product. Generated automatically
	 * when a new product is added to the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter @NotBlank private Integer id;

	/**
	 * The name of the product.
	 */
	@Column(name = "name")
	@Getter @Setter @NotBlank private String name;

	/**
	 * A brief description of the product.
	 */
	@Column(name = "description")
	@Getter @Setter @NotBlank private String description;

	/**
	 * The URL of an image representing the product.
	 */
	@Column(name = "image_url")
	@Getter @Setter @NotBlank private String imageURL;

	/**
	 * The number of units of this product that are currently in
	 * stock.
	 */
	@Column(name = "quantity")
	@Getter @Setter @NotBlank private Integer quantity;

	/**
	 * The price of the product.
	 */
	@Column(name = "price")
	@Getter @Setter @NotBlank private BigDecimal price;

	/**
	 * A boolean value indicating whether the product is currently
	 * available for purchase.
	 */
	@Column(name = "available")
	@Getter @Setter @NotBlank private Boolean available;

	/**
	 * The category to which this product belongs.
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "category_id", nullable = false)
	@Getter @Setter @NotBlank private Category category;


	/*----- Constructors -----*/

	/**
	 * This constructor takes the various attributes of a product and
	 * initializes its fields. All the arguments to the constructor
	 * are annotated with the {@code @NotBlank} annotation, indicating
	 * that they cannot be null or blank.
	 *
	 * @param name The name of the product. Must not be blank or null.
	 * @param description The description of the product. Must not be
	 *                    blank or null.
	 * @param imageURL The link to the image of the product. Must not be
	 *                 blank or null.
	 * @param quantity The quantity of the product in the market. Must
	 *                 not be blank or null.
	 * @param price The price of the product. Must not be blank or null.
	 * @param available Shows that product is not out of stock. Must not
	 *                  be blank or null.
	 * @param category The {@code Category} class that this product belongs
	 *                 to. Must not be blank or null.
	 */
	public Product(@NotBlank final String name, @NotBlank final String description,
	               @NotBlank final String imageURL, @NotBlank final Integer quantity,
	               @NotBlank final BigDecimal price, @NotBlank final Boolean available,
	               @NotBlank final Category category) {
		this.name = name;
		this.description = description;
		this.imageURL = imageURL;
		this.quantity = quantity;
		this.price = price;
		this.available = available;
		this.category = category;
	}

}
