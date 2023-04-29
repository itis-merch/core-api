package com.itis.merch.core.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Represents an item in a shopping cart that includes a product, an option, and a quantity.
 */
@NoArgsConstructor
@Entity
@Table(name = "shopping_cart_item")
@Data
public class ShoppingCartItem {

	/**
	 * The unique identifier for the shopping cart item.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Integer id;

	/**
	 * The product associated with the shopping cart item.
	 */
	@OneToOne(mappedBy = "shopping_cart_item", cascade = CascadeType.ALL, orphanRemoval = true )
	@NotNull
	private Product product;

	/**
	 * The option associated with the shopping cart item.
	 */
	@OneToOne(mappedBy = "shopping_cart_item", cascade = CascadeType.ALL, orphanRemoval = true )
	@NotNull
	private Option option;

	/**
	 * The quantity of the product in the shopping cart item.
	 */
	@Column(name = "quantity")
	@NotNull
	private Integer quantity;

	/**
	 * Creates a new shopping cart item with the given product, option, and quantity.
	 *
	 * @param product  the product associated with the shopping cart item (must not be null)
	 * @param option   the option associated with the shopping cart item (must not be null)
	 * @param quantity the quantity of the product in the shopping cart item (must not be null or blank)
	 */
	public ShoppingCartItem(@NotNull final Product product, @NotNull final Option option, @NotNull @NotBlank Integer quantity) {
		this.product = product;
		this.option = option;
		this.quantity = quantity;
	}
}
