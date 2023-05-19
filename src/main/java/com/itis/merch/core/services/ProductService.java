/**
 * This class provides the functionality to manage products in the system.
 */
package com.itis.merch.core.services;

import com.itis.merch.core.dto.product.ProductDTO;
import com.itis.merch.core.dto.product.ProductImageDTO;
import com.itis.merch.core.exceptions.CustomException;
import com.itis.merch.core.models.Category;
import com.itis.merch.core.models.Product;
import com.itis.merch.core.models.ProductImage;
import com.itis.merch.core.repositories.CategoryRepository;
import com.itis.merch.core.repositories.ProductImageRepository;
import com.itis.merch.core.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	@Autowired
	private final ProductImageRepository productImageRepository;

	/**
	 * Adds a new product to the system, given a ProductDTO object.
	 *
	 * @param productDTO The ProductDTO object representing the new product to be added.
	 * @throws RuntimeException If the category associated with the product does not exist.
	 */
	public void createProduct(ProductDTO productDTO) throws CustomException {
		Category category = categoryRepository.findById(productDTO.getCategoryID()).orElseThrow(() ->
						new CustomException("Category with given id does not exist.", HttpStatus.NOT_FOUND)
		);
		Product product = new Product(
						productDTO.getName(),
						productDTO.getDescription(),
						productDTO.getQuantity(),
						productDTO.getPrice(),
						productDTO.getAvailable(),
						category
		);
		productRepository.save(product);
	}

	/**
	 * Retrieves all products in the system.
	 *
	 * @return A list of all ProductDTO objects representing the products in the system.
	 */
	public List<ProductDTO> getAllProducts() {
		return productRepository.findAll()
						.stream()
						.map(ProductDTO::new)
						.collect(Collectors.toList());
	}

	/**
	 * Retrieves a product by its ID.
	 *
	 * @param id The ID of the product to retrieve.
	 * @return A ProductDTO object representing the retrieved product.
	 * @throws RuntimeException If the product with the given ID does not exist.
	 */
	public ProductDTO getProductById(Integer id) throws CustomException {
		Product product = productRepository.findById(id).orElseThrow(() ->
						new CustomException("Product with required id does not exist.", HttpStatus.NOT_FOUND));

		return new ProductDTO(product);
	}

	/**
	 * Updates a product with the given ID.
	 *
	 * @param productDTO The ProductDTO object containing the updated information for the product.
	 * @param id         The ID of the product to be updated.
	 * @throws RuntimeException If the product with the given ID does not exist or the category associated with the product does not exist.
	 */
	public void updateProductById(ProductDTO productDTO, Integer id) throws CustomException {
		Product updatedProduct = productRepository.findById(id).orElseThrow(() -> new CustomException("Product with required id does not exist.", HttpStatus.NOT_FOUND));

		updatedProduct.setCategory(categoryRepository.findById(productDTO.getCategoryID()).orElseThrow(() -> new CustomException("Category with given id does not exist.", HttpStatus.NOT_FOUND)));
		updatedProduct.setName(productDTO.getName());
		updatedProduct.setDescription(productDTO.getDescription());
//		updatedProduct.setProductImages(productDTO.getImageURLs());
		updatedProduct.setQuantity(productDTO.getQuantity());
		updatedProduct.setPrice(productDTO.getPrice());

		productRepository.save(updatedProduct);
	}

	public void addProductImage(Integer productId, ProductImageDTO productImageDTO) throws CustomException {
		Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product with required id does not exist.", HttpStatus.NOT_FOUND));
		ProductImage productImage = new ProductImage(productImageDTO.getImageURL(), product);
		productImageRepository.save(productImage);
	}
}


