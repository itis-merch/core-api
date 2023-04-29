/**

 This class represents the controller for handling requests related to the shopping cart and orders.
 */
package com.itis.merch.core.controllers;

import com.itis.merch.core.common.ApiResponse;
import com.itis.merch.core.dto.cartOrder.ShoppingCartItemDTO;
import com.itis.merch.core.exceptions.CustomException;
import com.itis.merch.core.security.JWTUtilService;
import com.itis.merch.core.services.CartOrderService;
import lombok.RequiredArgsConstructor;
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

	/**
	 * This method handles the request for adding an item to the shopping cart.
	 *
	 * @param shoppingCartItemDTO the DTO object containing the details of the item to be added.
	 * @param authentication      the object representing the authentication of the user making the request.
	 * @return ResponseEntity      the response containing the success status and message.
	 */
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addCartItem(@RequestBody final ShoppingCartItemDTO shoppingCartItemDTO, Authentication authentication) {

		String userEmail = jwtUtilService.extractUsername(authentication.getPrincipal().toString());

		cartOrderService.addItem(shoppingCartItemDTO, userEmail);

		return ResponseEntity.ok()
						.body(new ApiResponse(true, "Product was added to cart successfully."));
	}

	/**
	 * This method handles the request for placing an order based on the contents of the shopping cart.
	 *
	 * @param authentication the object representing the authentication of the user making the request.
	 * @return ResponseEntity      the response containing the success status and message.
	 * @throws CustomException if there is an error while processing the order.
	 */
	@PostMapping("/order")
	public ResponseEntity<ApiResponse> order(Authentication authentication) throws CustomException {

		String userEmail = jwtUtilService.extractUsername(authentication.getPrincipal().toString());

		cartOrderService.order(userEmail);

		return ResponseEntity.ok()
						.body(new ApiResponse(true, "Order was placed successfully."));
	}

}