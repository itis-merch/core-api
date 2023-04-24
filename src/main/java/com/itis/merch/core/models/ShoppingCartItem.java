package com.itis.merch.core.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "shopping_cart_item")
@Data
public class ShoppingCartItem {

	@Id
	private Integer id;
}
