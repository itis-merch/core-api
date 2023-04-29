package com.itis.merch.core.controllers;

import com.itis.merch.core.common.ApiResponse;
import com.itis.merch.core.dto.cartOrder.ShoppingCartItemDTO;
import com.itis.merch.core.exceptions.CustomException;
import com.itis.merch.core.security.JWTUtilService;
import com.itis.merch.core.services.CartOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartOrderController {

	private final CartOrderService cartOrderService;

	private final JWTUtilService jwtUtilService;

	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addCartItem(@RequestBody final ShoppingCartItemDTO shoppingCartItemDTO, Authentication authentication) {

		String userEmail = jwtUtilService.extractUsername(authentication.getPrincipal().toString());

		cartOrderService.addItem(shoppingCartItemDTO, userEmail);

		return new ResponseEntity<>(
						new ApiResponse(true, "Product was added to cart successfully."),
						HttpStatus.CREATED
		);
	}

	@PostMapping("/order")
	public ResponseEntity<ApiResponse> order(Authentication authentication) throws CustomException {

		String userEmail = jwtUtilService.extractUsername(authentication.getPrincipal().toString());

		cartOrderService.order(userEmail);

		return new ResponseEntity<>(
						new ApiResponse(true, "Product was added to cart successfully."),
						HttpStatus.CREATED
		);

	}

}
