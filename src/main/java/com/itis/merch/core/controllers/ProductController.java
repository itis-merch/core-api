package com.itis.merch.core.controllers;

import com.itis.merch.core.dto.product.ProductDTO;
import com.itis.merch.core.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The ProductController class handles incoming HTTP requests and returns HTTP responses for product-related operations.
 */
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	/**
	 * Adds a new product to the database and returns the created product with HTTP status 201 CREATED.
	 *
	 * @param productDTO The ProductDTO object representing the product to be added.
	 * @return A ResponseEntity containing the created ProductDTO object and HTTP status 201 CREATED.
	 */
	@PostMapping
	public ResponseEntity<ProductDTO> addNewProduct(@RequestBody ProductDTO productDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.addNewProduct(productDTO));
	}

	@GetMapping
	public ResponseEntity<List<ProductDTO>> getProducts() {
		return ResponseEntity.ok(productService.getAll());
	}

	@GetMapping("/{product-id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable("product-id") Integer productId) {
		return ResponseEntity.ok(productService.getById(productId));
	}

	@PostMapping("/{product-id}")
	public ResponseEntity<ProductDTO> updateProductById(@PathVariable("product-id") Integer productId, @RequestBody ProductDTO productDTO) {
		return ResponseEntity.ok(productService.updateById(productDTO, productId));
	}
}


