package com.itis.merch.core.dto.cartOrder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itis.merch.core.models.ShoppingCartItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ShoppingCartItemDTO {

	@JsonProperty("id")
	private Integer id;

	@NotNull
	@JsonProperty("product_id")
	private Integer productId;

	@NotNull
	@JsonProperty("option_id")
	private Integer optionId;

	@NotNull
	@NotBlank
	@JsonProperty("quantity")
	private Integer quantity;

	public ShoppingCartItemDTO(final Integer id, @NotBlank @NotNull Integer productId,
							   @NotBlank @NotNull Integer optionId, @NotBlank @NotNull Integer quantity) {
		this.id = id;
		this.productId = productId;
		this.optionId = optionId;
		this.quantity = quantity;
	}

	public ShoppingCartItemDTO(@NotNull final ShoppingCartItem shoppingCartItem) {
		this.setId(shoppingCartItem.getId());
		this.setProductId(shoppingCartItem.getProduct().getId());
		this.setOptionId(shoppingCartItem.getOption().getId());
		this.setQuantity(shoppingCartItem.getQuantity());
	}
}
