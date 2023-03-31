/**
 * This Java class is called CategoryService and is part of a project for a merchandising company.
 * It provides several methods to interact with the Category entity in the database.
 *
 * The class is annotated with the "@Service" annotation, which indicates that it is a Spring service that can be injected into other classes.
 * The CategoryRepository is also injected using the "@Autowired" annotation.
 *
 * The methods provided by the class are:
 *
 * - getAll(): Returns a list of all categories. The list is transformed into a list of CategoryDTO using the Java Stream API.
 *
 * - getById(id): Retrieves a category by its ID. If the category does not exist, an Exception is thrown.
 *
 * - create(category): Creates a new Category entity. If a category with the same name already exists, an Exception is thrown.
 *
 * - updateById(category, id): Updates an existing Category entity by its ID. If the category does not exist, an Exception is thrown.
 *
 * The CategoryDTO is a simplified version of the Category entity that contains only the necessary information for the client.
 *
 * This class is useful for managing Category entities in a database and provides a convenient way to interact with them.
 */
package com.itis.merch.core.services;

import com.itis.merch.core.dto.category.CategoryDTO;
import com.itis.merch.core.models.Category;
import com.itis.merch.core.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public List<CategoryDTO> getAll() {
		return categoryRepository.findAll()
				.stream()
				.map(CategoryDTO::new)
				.collect(Collectors.toList());
	}

	public CategoryDTO getById(Integer id) throws Exception{
		Category category = categoryRepository.findById(id).get();
		if(category == null){
			throw new Exception("Category does not exist!");
		}

		return new CategoryDTO(category);
	}

	public Category create(Category category) throws Exception {
		if(categoryRepository.findByName(category.getName()) != null) {
			throw new Exception("Category with this name already exist!");
		}

		return categoryRepository.save(category);
	}

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
