package com.itis.merch.core.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Represents an order made by a user that includes a list of shopping cart items, a total price, a phone number, and a status.
 * The status can be one of four possible values: CART, PENDING, PAYED, or CLOSED.
 * CART means the order is still in the user's cart, PENDING means the order has been submitted but not yet paid for,
 * PAYED means the order has been paid for, and CLOSED means the order has been delivered or cancelled.
 * PAYED means the order has been paid for, and CLOSED means the order has been delivered or cancelled.
 */
@NoArgsConstructor
@Entity
@Table(name = "cart_order")
@Data
public class CartOrder {

	/**
	 * The unique identifier for the cart order.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Integer id;

	/**
	 * The ID of the user who made the cart order.
	 */
	@NotBlank
	@NotNull
	private Integer userId;

	/**
	 * The list of shopping cart items included in the cart order.
	 */
	@OneToMany(mappedBy = "cart_order", cascade = CascadeType.ALL, orphanRemoval = true)
	@NotNull
	private List<ShoppingCartItem> shoppingCartItems;

	/**
	 * The total price of the cart order.
	 */
	@Column(name = "total_price")
	@NotBlank
	@NotNull
	private double totalPrice;

	/**
	 * The phone number associated with the cart order.
	 */
	@Column(name = "phone_number")
	@NotBlank
	@NotNull
	private String phoneNumber;

	/**
	 * The status of the cart order, which can be one of four possible values: CART, PENDING, PAYED, or CLOSED.
	 */
	@Column(name = "status")
	@NotNull
	private CartOrderStatus status;

	/**
	 * The possible values of the CartOrderStatus enum.
	 */
	private enum CartOrderStatus {
		CART,
		PENDING,
		PAYED,
		CLOSED
	}

	/**
	 * Creates a new cart order with the given user ID, list of shopping cart items, total price, phone number, and status.
	 *
	 * @param userId           the ID of the user who made the cart order (must not be null or blank)
	 * @param shoppingCartItems the list of shopping cart items included in the cart order (must not be null)
	 * @param totalPrice       the total price of the cart order (must not be null or blank)
	 * @param phoneNumber      the phone number associated with the cart order (must not be null or blank)
	 * @param status           the status of the cart order (must not be null)
	 */
	public CartOrder(@NotNull @NotBlank final Integer userId,@NotNull List<ShoppingCartItem> shoppingCartItems,
					 @NotNull @NotBlank Integer totalPrice, @NotNull @NotBlank String phoneNumber,
					 @NotNull @NotBlank CartOrderStatus status) {
		this.userId = userId;
		this.shoppingCartItems = shoppingCartItems;
		this.totalPrice = totalPrice;
		this.phoneNumber = phoneNumber;
		this.status = status;
	}
}
