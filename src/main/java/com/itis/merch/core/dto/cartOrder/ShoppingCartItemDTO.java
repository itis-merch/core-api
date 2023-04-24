package com.itis.merch.core.dto.cartOrder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itis.merch.core.models.ShoppingCartItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Represents a data transfer object for a shopping cart item, including the unique identifier for the item,
 * the ID of the associated product, the ID of the associated option, and the quantity of the product in the item.
 * This class provides methods for creating instances of the class with the given field values.
 */
@Data
@NoArgsConstructor
public class ShoppingCartItemDTO {

	/**
	 * The unique identifier for the shopping cart item.
	 */
	@JsonProperty("id")
	private Integer id;

	/**
	 * The ID of the associated product.
	 */
	@NotNull
	@JsonProperty("product_id")
	private Integer productId;

	/**
	 * The ID of the associated option.
	 */
	@NotNull
	@JsonProperty("option_id")
	private Integer optionId;

	/**
	 * The quantity of the product in the shopping cart item.
	 */
	@NotNull
	@NotBlank
	@JsonProperty("quantity")
	private Integer quantity;

	/**
	 * Creates a new instance of the {@code ShoppingCartItemDTO} class with the given field values.
	 *
	 * @param id The unique identifier for the shopping cart item.
	 * @param productId The ID of the associated product.
	 * @param optionId The ID of the associated option.
	 * @param quantity The quantity of the product in the shopping cart item.
	 */
	public ShoppingCartItemDTO(final Integer id, @NotBlank @NotNull Integer productId,
							   @NotBlank @NotNull Integer optionId, @NotBlank @NotNull Integer quantity) {
		this.id = id;
		this.productId = productId;
		this.optionId = optionId;
		this.quantity = quantity;
	}

	/**
	 * Creates a new instance of the {@code ShoppingCartItemDTO} class with the field values from the given
	 * {@code ShoppingCartItem} instance.
	 *
	 * @param shoppingCartItem The {@code ShoppingCartItem} instance to create the DTO from.
	 */
	public ShoppingCartItemDTO(@NotNull final ShoppingCartItem shoppingCartItem) {
		this.setId(shoppingCartItem.getId());
		this.setProductId(shoppingCartItem.getProduct().getId());
		this.setOptionId(shoppingCartItem.getOption().getId());
		this.setQuantity(shoppingCartItem.getQuantity());
	}
}
