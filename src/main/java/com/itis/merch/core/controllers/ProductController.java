/**
 * The ProductController class handles incoming HTTP requests and returns HTTP responses for product-related operations.
 */
package com.itis.merch.core.controllers;

import com.itis.merch.core.common.ApiResponse;
import com.itis.merch.core.dto.product.ProductDTO;
import com.itis.merch.core.exceptions.CustomException;
import com.itis.merch.core.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The ProductController class handles incoming HTTP requests and returns HTTP
 * responses for product-related operations.
 */
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
	@Autowired
	private final ProductService productService;

	/**
	 * Adds a new product to the database and returns the created product with HTTP
	 * status 201 CREATED.
	 *
	 * @param productDTO The ProductDTO object representing the product to be added.
	 * @return A ResponseEntity containing the created ProductDTO object and HTTP
	 * status 201 CREATED.
	 */
	@PostMapping
	public ResponseEntity<ApiResponse> addNewProduct(@RequestBody final ProductDTO productDTO) throws CustomException {
		productService.addNewProduct(productDTO);
		return new ResponseEntity<>(
						new ApiResponse(true, "Product was added successfully."),
						HttpStatus.CREATED
		);
	}

	/**
	 * Retrieves a list of all products from the database and returns it with HTTP
	 * status 200 OK.
	 *
	 * @return A ResponseEntity containing the list of all products and HTTP status
	 * 200 OK.
	 */
	@GetMapping
	public ResponseEntity<List<ProductDTO>> getProducts() {
		return ResponseEntity.ok(productService.getAll());
	}

	/**
	 * Retrieves a product with a specific ID from the database and returns it with
	 * HTTP status 200 OK.
	 *
	 * @param productId The ID of the product to be retrieved.
	 * @return A ResponseEntity containing the retrieved ProductDTO object and HTTP
	 * status 200 OK.
	 */
	@GetMapping("/{product_id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable("product_id") Integer productId) throws CustomException {
		return ResponseEntity.ok(productService.getById(productId));
	}

	/**
	 * Updates a product with a specific ID in the database and returns the updated
	 * product with HTTP status 200 OK.
	 *
	 * @param productId  The ID of the product to be updated.
	 * @param productDTO The ProductDTO object representing the updated product.
	 * @return A ResponseEntity containing the updated ProductDTO object and HTTP
	 * status 200 OK.
	 */
	@PostMapping("/{product_id}")
	public ResponseEntity<ApiResponse> updateProductById(@PathVariable("product_id") Integer productId,
	                                                     @RequestBody ProductDTO productDTO) throws CustomException {
		productService.updateById(productDTO, productId);
		return new ResponseEntity<>(
						new ApiResponse(true, "Product was updated successfully."),
						HttpStatus.OK
		);
	}
}


