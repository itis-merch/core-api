package com.itis.merch.core.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "cart_order")
@Data
public class CartOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Integer id;

	@NotBlank
	@NotNull
	private Integer userId;
	@OneToMany(mappedBy = "cart_order", cascade = CascadeType.ALL, orphanRemoval = true)
	@NotNull
	private List<Product> products;

	@Column(name = "total_price")
	@NotBlank
	@NotNull
	private double totalPrice;

	@Column(name = "phone_number")
	@NotBlank
	@NotNull
	private String phoneNumber;

	@Column(name = "cart_order_status")
	@NotNull
	private Cart_Order_Status cartOrderStatus;

	private enum Cart_Order_Status {
		CART,
		PENDING,
		PAYED,
		CLOSED
	}

	public CartOrder(@NotNull @NotBlank final Integer userId) {
		this.userId = userId;
		this.cartOrderStatus = Cart_Order_Status.CART;
	}
}
