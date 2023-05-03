/**
 * This class provides the implementation for handling the cart and order for a user.
 */
package com.itis.merch.core.services;

import com.itis.merch.core.dto.cartOrder.ShoppingCartItemDTO;
import com.itis.merch.core.enums.CartOrderStatus;
import com.itis.merch.core.exceptions.CustomException;
import com.itis.merch.core.models.AppUser;
import com.itis.merch.core.models.CartOrder;
import com.itis.merch.core.models.Product;
import com.itis.merch.core.models.ShoppingCartItem;
import com.itis.merch.core.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartOrderService {
	private final CartOrderRepository cartOrderRepository;
	private final ShoppingCartItemRepository shoppingCartItemRepository;
	private final ProductRepository productRepository;
	private final OptionRepository optionRepository;
	private final AppUserRepository appUserRepository;

	/**
	 * This method adds a new item to the cart of a user.
	 *
	 * @param shoppingCartItemDTO DTO object for the shopping cart item.
	 * @param userEmail           Email address of the user.
	 */
	public void addItem(ShoppingCartItemDTO shoppingCartItemDTO, String userEmail) {
		// Find the user by email.
		AppUser user = appUserRepository.findByEmailAddress(userEmail).get();

		// Find the cart of the user. If it doesn't exist, create a new cart.
		CartOrder cartOrder = cartOrderRepository.findCartOrderByUserId(user.getId())
						.orElse(cartOrderRepository.save(new CartOrder(
										user.getId(),
										new ArrayList<>(),
										new BigDecimal(0),
										"",
										CartOrderStatus.CART)));

		// Get the product that is to be added to the cart.
		Product product = productRepository.getById(shoppingCartItemDTO.getProductId());

		// Create a new shopping cart item.
		ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.save(new ShoppingCartItem(
						product,
						optionRepository.getById(shoppingCartItemDTO.getOptionId()),
						shoppingCartItemDTO.getQuantity()));

		// Add the shopping cart item to the cart of the user.
		cartOrder.getShoppingCartItems().add(shoppingCartItem);

		// Update the total price of the cart.
		cartOrder.setTotalPrice(cartOrder.getTotalPrice().add((BigDecimal.valueOf(shoppingCartItem.getQuantity())
						.multiply(product.getPrice()))));

		// Save the cart.
		cartOrderRepository.save(cartOrder);
	}

	/**
	 * This method places an order for the cart of a user.
	 *
	 * @param userEmail Email address of the user.
	 * @param phoneNumber    Phone number of the user.
	 * @throws CustomException if the cart of the user doesn't exist.
	 */
	public void order(String userEmail, String phoneNumber) throws CustomException {
		// Find the user by email.
		AppUser user = appUserRepository.findByEmailAddress(userEmail)
						.orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

		// Find the cart of the user. If it doesn't exist, throw an exception.
		CartOrder cartOrder = cartOrderRepository.findCartOrderByUserId(user.getId())
						.orElseThrow(() -> new CustomException("Cart doesn't exist", HttpStatus.BAD_REQUEST));

		// Set the status of the cart to "Pending" and set the phone number.
		cartOrder.setStatus(CartOrderStatus.PENDING);
		cartOrder.setPhoneNumber(phoneNumber);

		// Save the cart.
		cartOrderRepository.save(cartOrder);
	}

}