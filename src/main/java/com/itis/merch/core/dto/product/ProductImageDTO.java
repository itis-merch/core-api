package com.itis.merch.core.dto.product;

import com.itis.merch.core.models.ProductImage;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This class represents a Data Transfer Object (DTO) for a {@code
 * ProductImage} entity. It contains an ID, an image URL, and the
 * ID of the product that the image belongs to.
 */
@Data
@NoArgsConstructor
public final class ProductImageDTO {

	/**
	 * The unique identifier for this product image.
	 */
	private Integer id;

	/**
	 * The URL of an image representing the product.
	 */
	@NotBlank
	@NotNull
	private String imageURL;

	/**
	 * The ID of the product that this image belongs to.
	 */
	@NotNull
	private Integer productID;

	/**
	 * Constructs a new instance of the {@code ProductImageDTO} class
	 * with the specified parameters.
	 *
	 * @param id        the ID of the product image
	 * @param imageURL  the URL of the product image
	 * @param productID the ID of the product that this image belongs to
	 */
	public ProductImageDTO(final Integer id, @NotBlank @NotNull final String imageURL,
	                       @NotNull final Integer productID) {
		this.id = id;
		this.imageURL = imageURL;
		this.productID = productID;
	}

	/**
	 * Constructs a new instance of the {@code ProductImageDTO} class
	 * based on an existing ProductImage entity.
	 *
	 * @param productImage the non-null {@code ProductImage} entity to
	 *                     convert to a {@code ProductImageDTO}.
	 */
	public ProductImageDTO(@NotNull final ProductImage productImage) {
		this.setId(productImage.getId());
		this.setImageURL(productImage.getImageURL());
		this.setProductID(productImage.getProduct().getId());
	}

}
