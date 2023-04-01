/**
 * The CategoryService class provides methods for interacting with the Category table in the database.
 * It provides methods for retrieving, creating, and updating categories.
 * It also provides a method for converting Category objects to CategoryDTO objects.
 *
 * @author [Marat]
 * @version 1.0
 * @since 2023.03.31
 */
package com.itis.merch.core.services;

import com.itis.merch.core.dto.category.CategoryDTO;
import com.itis.merch.core.models.Category;
import com.itis.merch.core.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This service class provides CRUD operations for the Category entity.
 * <p>
 * It interacts with the database through the {@code CategoryRepository} class.
 */
@RequiredArgsConstructor
@Service
public class CategoryService {

	@Autowired
	final CategoryRepository categoryRepository;

	/**
	 * Retrieves all categories from the database and converts them to CategoryDTO objects.
	 *
	 * @return a list of CategoryDTO objects
	 */
	public List<CategoryDTO> getAll() {
		return categoryRepository.findAll()
				.stream()
				.map(CategoryDTO::new)
				.collect(Collectors.toList());
	}

	/**
	 * Retrieves the category with the specified name from the database.
	 * @param name the name of the category to retrieve.
	 * @return a {@code CategoryDTO} object representing the retrieved category, or null if
	 * the category with the specified name does not exist in the database.
	 */
	public CategoryDTO readCategory(final String name) {
		final Category category = categoryRepository.findByName(name);
		return Objects.nonNull(category) ? new CategoryDTO(category) : null;
	}

	/**
	 * Retrieves a category with the specified ID from the database and converts it to a CategoryDTO object.
	 *
	 * @param id the ID of the category to retrieve
	 * @return a CategoryDTO object
	 * @throws Exception if the category does not exist
	 */
	public CategoryDTO getById(Integer id) throws Exception{
		Category category = categoryRepository.findById(id).get();
		if(category == null){
			throw new Exception("Category does not exist!");
		}

		return new CategoryDTO(category);
	}

	/**
	 * Creates a new category in the database.
	 *
	 * @param categoryDTO the CategoryDTO object to create
	 * @throws Exception if a category with the same name already exists
	 */
	public void create(final CategoryDTO categoryDTO) {
		final Category category = new Category();

		category.setId(categoryDTO.getId());
		category.setName(categoryDTO.getName());
		category.setDescription(categoryDTO.getDescription());

		categoryRepository.save(category);
	}

	/**
	 * Updates a category with the specified ID in the database.
	 *
	 * @param category the updated category object
	 * @param id the ID of the category to update
	 * @return the updated category object
	 * @throws Exception if the category does not exist
	 */
	public Category updateById(Category category, Integer id) throws Exception {
		Category updateCategory = categoryRepository.findById(id).get();

		if(updateCategory == null) {
			throw new Exception("Category does not Exist!");
		}

		updateCategory.setName(category.getName());
		updateCategory.setDescription(category.getDescription());

		return categoryRepository.save(updateCategory);
	}
}
