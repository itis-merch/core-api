/**
 * This class provides the functionality to manage products in the system.
 */
package com.itis.merch.core.services;

import com.itis.merch.core.dto.product.ProductDTO;
import com.itis.merch.core.models.Category;
import com.itis.merch.core.models.Product;
import com.itis.merch.core.repositories.CategoryRepository;
import com.itis.merch.core.repositories.ProductRepository;
import com.itis.merch.core.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
	@Autowired
	private final ProductRepository productRepository;

	@Autowired
	private final CategoryRepository categoryRepository;

	/**
	 * Adds a new product to the system, given a ProductDTO object.
	 *
	 * @param productDTO The ProductDTO object representing the new product to be added.
	 * @return A ProductDTO object representing the newly created product.
	 * @throws RuntimeException If the category associated with the product does not exist.
	 */
	public ProductDTO addNewProduct(ProductDTO productDTO) throws RuntimeException {
		// Retrieves the category associated with the product.
		Category category = categoryRepository.findById(productDTO.getCategoryID()).orElseThrow(() -> new RuntimeException("Category with given id does not exist"));
		// Creates a new Product object with the retrieved category.
		Product product = new Product(productDTO.getName(), productDTO.getDescription(), productDTO.getQuantity(), productDTO.getPrice(), productDTO.getAvailable(), category);
		// Saves the product to the database and returns a new ProductDTO object representing the created product.
		product = productRepository.save(product);
		return new ProductDTO(product);
	}

	/**
	 * Retrieves all products in the system.
	 *
	 * @return A list of all ProductDTO objects representing the products in the system.
	 */
	public List<ProductDTO> getAll() {
		return productRepository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
	}

	/**
	 * Retrieves a product by its ID.
	 *
	 * @param id The ID of the product to retrieve.
	 * @return A ProductDTO object representing the retrieved product.
	 * @throws RuntimeException If the product with the given ID does not exist.
	 */
	public ProductDTO getById(Integer id) throws RuntimeException {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product with required id does not exist"));

		return new ProductDTO(product);
	}

	/**
	 * Updates a product with the given ID.
	 *
	 * @param productDTO The ProductDTO object containing the updated information for the product.
	 * @param id         The ID of the product to be updated.
	 * @return A ProductDTO object representing the updated product.
	 * @throws RuntimeException If the product with the given ID does not exist or the category associated with the product does not exist.
	 */
	public ProductDTO updateById(ProductDTO productDTO, Integer id) throws RuntimeException {
		Product updatedProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product with required id does not exist"));

//		updatedProduct.setId(productDTO.getId());
		updatedProduct.setCategory(categoryRepository.findById(productDTO.getCategoryID()).orElseThrow(() -> new RuntimeException("Category with given id does not exist")));
		updatedProduct.setName(productDTO.getName());
		updatedProduct.setDescription(productDTO.getDescription());
		//updatedProduct.setProductImages(productDTO.getProductImages());
		updatedProduct.setQuantity(productDTO.getQuantity());
		updatedProduct.setPrice(productDTO.getPrice());

		return new ProductDTO(productRepository.save(updatedProduct));
	}
}


