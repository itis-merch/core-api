package com.itis.merch.core.dto.cartOrder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itis.merch.core.enums.CartOrderStatus;
import com.itis.merch.core.models.CartOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class CartOrderDTO {

	@JsonProperty("id")
	private Integer id;

	@NotNull
	@NotBlank
	@JsonProperty("user_id")
	private Integer userId;

	@NotNull
	@JsonProperty("products")
	private List<ShoppingCartItemDTO> shoppingCartItemDTOs;


	@NotNull
	@NotBlank
	@JsonProperty("total_price")
	private double totalPrice;

	@NotNull
	@NotBlank
	@JsonProperty("phone_number")
	private String phoneNumber;

	@NotNull
	@NotBlank
	@JsonProperty("status")
	private CartOrderStatus status;

	public CartOrderDTO(final Integer id,@NotNull @NotBlank final Integer userId,@NotNull List<ShoppingCartItemDTO> shoppingCartItemDTOs,
					 @NotNull @NotBlank double totalPrice, @NotNull @NotBlank String phoneNumber,
					 @NotNull @NotBlank CartOrderStatus status) {
		this.id = id;
		this.userId = userId;
		this.shoppingCartItemDTOs = shoppingCartItemDTOs;
		this.totalPrice = totalPrice;
		this.phoneNumber = phoneNumber;
		this.status = status;
	}

	public CartOrderDTO(@NotNull final CartOrder cartOrder) {
		this.setId(cartOrder.getId());
		this.setUserId(cartOrder.getUserId());
		this.setTotalPrice(cartOrder.getTotalPrice());
		this.setPhoneNumber(cartOrder.getPhoneNumber());
		this.setStatus(cartOrder.getStatus());
		cartOrder.getShoppingCartItems().forEach(shoppingCartItem -> this.shoppingCartItemDTOs.add(new ShoppingCartItemDTO(shoppingCartItem)));
	}
}
