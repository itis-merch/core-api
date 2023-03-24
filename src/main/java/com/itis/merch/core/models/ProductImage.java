package com.itis.merch.core.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This class represents an image of a product, which contains
 * an ID, an image URL, and a reference to the product it belongs
 * to.
 * <p>
 * The product field represents the product that the image belongs
 * to, and is annotated with {@code @ManyToOne} to indicate that
 * multiple images can belong to a single product.
 */
@NoArgsConstructor
@Entity
@Table(name = "product_image")
@Data
public final class ProductImage {

	/*----- Private fields -----*/

	/**
	 * The unique identifier for this product image. Generated
	 * automatically when a new product image is added to the
	 * database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Integer id;

	/**
	 * The URL of an image representing the product.
	 */
	@Column(name = "image_url")
	@NotBlank
	@NotNull
	private String imageURL;

	/**
	 * The product to which this image belongs.
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "product_id", nullable = false)
	@NotNull
	private Product product;

	/*----- Constructors -----*/

	/**
	 * This constructor creates a new {@code ProductImage} instance
	 * with the given image URL and product.
	 *
	 * @param imageURL a non-null and non-blank String representing
	 *                 the URL of the product image
	 * @param product  a non-null and non-blank Product object representing
	 *                 the product that the image belongs to
	 */
	public ProductImage(@NotBlank @NotNull final String imageURL, @NotBlank @NotNull final Product product) {
		this.imageURL = imageURL;
		this.product = product;
	}

}
