/**
 * The CategoryController class is a REST API controller that handles all HTTP requests related to categories.
 * It provides endpoints for retrieving, creating, and updating categories.
 *
 * @author [Marat]
 * @version 1.0
 * @since 2023.03.31
 */
package com.itis.merch.core.controllers;

import com.itis.merch.core.common.ApiResponse;
import com.itis.merch.core.dto.category.CategoryDTO;
import com.itis.merch.core.models.Category;
import com.itis.merch.core.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a RESTful controller for handling HTTP requests related to categories.
 * <p>
 * It contains methods for retrieving, creating, updating categories and is mapped to the
 * "/categories" endpoint.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	final CategoryService categoryService;

	/**
	 * Retrieves all categories from the database.
	 *
	 * @return ResponseEntity containing the list of categories and a status code of 200 OK.
	 */
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getCategories() {
		return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
	}

	/**
	 * Retrieves a category with the specified ID from the database.
	 *
	 * @param id the ID of the category to retrieve
	 * @return ResponseEntity containing the category object and a status code of 200 OK
	 * if successful, or a status code of 400 Bad Request and an error message if an
	 * exception is caught.
	 */
	@GetMapping("/{category_id}")
	public ResponseEntity getCategoryById(@PathVariable("category_id") Integer id) {
		try {
			return ResponseEntity.ok(categoryService.getById(id));
		}
		catch (Exception e){
			return ResponseEntity.badRequest().body("Category with this id does not exist!");
		}
	}

	/**
	 * Creates a new category by processing a POST request with the specified category DTO.
	 *
	 * @param categoryDTO the DTO containing the information for the category to be created.
	 * @return a ResponseEntity containing an ApiResponse indicating whether the category was
	 * created successfully or not, and the appropriate HTTP status code.
	 */
	@PostMapping
	public ResponseEntity<ApiResponse> createCategory(@RequestBody CategoryDTO categoryDTO) {
		if (Objects.nonNull(categoryService.readCategory(categoryDTO.getName()))) {
			return new ResponseEntity<>(new ApiResponse(false, "Category with this name already exists!"), HttpStatus.BAD_REQUEST);
		}

		categoryService.create(categoryDTO);
		return new ResponseEntity<>(new ApiResponse(true, "Category created successfully!"), HttpStatus.CREATED);
	}

	/**
	 * Updates a category with the specified ID in the database.
	 *
	 * @param category the updated category object
	 * @param id the ID of the category to update
	 * @return ResponseEntity containing a success message and a status code of 202 Accepted
	 * if successful, or a failure message and a status code of 400 Bad Request if the category
	 * does not exist.
	 */
	@PostMapping("/{category_id}")
	public ResponseEntity updateCategoryById(@RequestBody Category category, @PathVariable("category_id") Integer id) {
		try {
			categoryService.updateById(category, id);
			return new ResponseEntity<>(
					new ApiResponse(true, "Category was updated successfully!"),
					HttpStatus.ACCEPTED
			);
		}
		catch (Exception e) {
			return new ResponseEntity<>(
					new ApiResponse(false, "Category you want to update does not exist!"),
					HttpStatus.BAD_REQUEST
			);
		}
	}
}
