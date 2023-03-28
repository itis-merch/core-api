package com.itis.merch.core.services;

import com.itis.merch.core.dto.product.ProductDTO;
import com.itis.merch.core.models.Category;
import com.itis.merch.core.models.Product;
import com.itis.merch.core.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	public ProductDTO addNewProduct(ProductDTO productDTO) {
		Category category = categoryRepository.findById(productDTO.getCategoryID()).orElseThrow(new RuntimeException("Category with given id does not exist"));
		Product product = new Product(productDTO.getName(), productDTO.getDescription(), productDTO.getQuantity(), productDTO.getPrice(), productDTO.getAvailable(), category);
		product = productRepository.save(product);
		return new ProductDTO(product);
	}
}
