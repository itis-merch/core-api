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

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartOrderService {

	private final CartOrderRepository cartOrderRepository;

	private final ShoppingCartItemRepository shoppingCartItemRepository;

	private final ProductRepository productRepository;

	private final OptionRepository optionRepository;

	private final AppUserRepository appUserRepository;

	public void addItem(ShoppingCartItemDTO shoppingCartItemDTO, String userEmail) {
		AppUser user = appUserRepository.findByEmailAddress(userEmail).get();
		CartOrder cartOrder = cartOrderRepository.findCartOrderByUserId(user.getId())
						.orElse(cartOrderRepository.save(new CartOrder(
										user.getId(),
										new ArrayList<ShoppingCartItem>(),
										0,
										user.getPhoneNumber(),
										CartOrderStatus.CART)));
		Product product = productRepository.getById(shoppingCartItemDTO.getProductId());
		ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.save(new ShoppingCartItem(
						product,
						optionRepository.getById(shoppingCartItemDTO.getOptionId()),
						shoppingCartItemDTO.getQuantity()));
		cartOrder.getShoppingCartItems().add(shoppingCartItem);
		cartOrder.setTotalPrice(cartOrder.getTotalPrice()+shoppingCartItem.getQuantity()*product.getPrice());
		cartOrderRepository.save(cartOrder);
	}

}
