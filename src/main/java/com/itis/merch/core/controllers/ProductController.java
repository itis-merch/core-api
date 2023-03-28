package com.itis.merch.core.controllers;


import com.itis.merch.core.dto.product.ProductDTO;
import com.itis.merch.core.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;
	public ResponseEntity<ProductDTO> addNewProduct(@RequestBody ProductDTO productDTO) {
		return ResponseEntity
						.status(HttpStatus.CREATED)
						.body(productService.addNewProduct(productDTO));
	}
}
