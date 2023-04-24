package com.itis.merch.core.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Entity
@Table(name = "shopping_cart_item")
@Data
public class ShoppingCartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Integer id;

	@OneToOne(mappedBy = "shopping_cart_item", cascade = CascadeType.ALL, orphanRemoval = true )
	@NotNull
	private Product product;

	@OneToOne(mappedBy = "shopping_cart_item", cascade = CascadeType.ALL, orphanRemoval = true )
	@NotNull
	private Option option;

	@Column(name = "quantity")
	@NotNull
	@NotBlank
	private Integer quantity;

	public ShoppingCartItem(@NotNull final Product product, @NotNull final Option option, @NotNull @NotBlank Integer quantity) {
		this.product = product;
		this.option = option;
		this.quantity = quantity;
	}
}
