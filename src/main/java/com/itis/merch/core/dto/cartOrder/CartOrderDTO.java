package com.itis.merch.core.dto.cartOrder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itis.merch.core.enums.CartOrderStatus;
import com.itis.merch.core.models.CartOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * CartOrderDTO is a data transfer object that represents a shopping cart order.
 * It contains the shopping cart items, the user ID, the total price, the phone number,
 * and the status of the order.
 */
@Data
@NoArgsConstructor
public class CartOrderDTO {

	/**
	 * The ID of the cart order.
	 */
	@JsonProperty("id")
	private Integer id;

	/**
	 * The user ID associated with the cart order.
	 */
	@NotNull
	@NotBlank
	@JsonProperty("user_id")
	private Integer userId;

	/**
	 * The list of shopping cart items associated with the cart order.
	 */
	@NotNull
	@JsonProperty("products")
	private List<ShoppingCartItemDTO> shoppingCartItemDTOs;

	/**
	 * The total price of the cart order.
	 */
	@NotNull
	@NotBlank
	@JsonProperty("total_price")
	private double totalPrice;

	/**
	 * The phone number associated with the cart order.
	 */
	@NotNull
	@NotBlank
	@JsonProperty("phone_number")
	private String phoneNumber;

	/**
	 * The status of the cart order, which can be one of four possible values: CART, PENDING, PAYED, or CLOSED.
	 */
	@NotNull
	@NotBlank
	@JsonProperty("status")
	private CartOrderStatus status;

	/**
	 * Constructor for the CartOrderDTO class.
	 *
	 * @param id The ID of the cart order.
	 * @param userId The user ID associated with the cart order.
	 * @param shoppingCartItemDTOs The list of shopping cart items associated with the cart order.
	 * @param totalPrice The total price of the cart order.
	 * @param phoneNumber The phone number associated with the cart order.
	 * @param status The status of the cart order.
	 */
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

	/**
	 * Constructor for the CartOrderDTO class that takes in a CartOrder object.
	 *
	 * @param cartOrder The CartOrder object to convert to a CartOrderDTO object.
	 */
	public CartOrderDTO(@NotNull final CartOrder cartOrder) {
		this.setId(cartOrder.getId());
		this.setUserId(cartOrder.getUserId());
		this.setTotalPrice(cartOrder.getTotalPrice());
		this.setPhoneNumber(cartOrder.getPhoneNumber());
		this.setStatus(cartOrder.getStatus());
		cartOrder.getShoppingCartItems().forEach(shoppingCartItem -> this.shoppingCartItemDTOs.add(new ShoppingCartItemDTO(shoppingCartItem)));
	}
}
