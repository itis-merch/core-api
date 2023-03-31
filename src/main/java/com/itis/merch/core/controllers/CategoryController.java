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
import com.itis.merch.core.models.Category;
import com.itis.merch.core.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	/**
	 * Retrieves all categories from the database.
	 *
	 * @return ResponseEntity containing the list of categories and a status code of 200 OK if successful,
	 *         or a status code of 400 Bad Request and an error message if an exception is caught.
	 */
	@GetMapping
	public ResponseEntity getCategories() {
		try {
			return ResponseEntity.ok(categoryService.getAll());
		}
		catch (Exception e){
			return ResponseEntity.badRequest().body("Error!");
		}
	}

	/**
	 * Retrieves a category with the specified ID from the database.
	 *
	 * @param id the ID of the category to retrieve
	 * @return ResponseEntity containing the category object and a status code of 200 OK if successful,
	 *         or a status code of 400 Bad Request and an error message if an exception is caught.
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
	 * Creates a new category in the database.
	 *
	 * @param category the category object to create
	 * @return ResponseEntity containing a success message and a status code of 201 Created if successful,
	 *         or a failure message and a status code of 400 Bad Request if the category already exists.
	 */
	@PostMapping
	public ResponseEntity createCategory(@RequestBody Category category) {
		try {
			categoryService.create(category);
			return new ResponseEntity<>(
					new ApiResponse(true, "Category was create successfully."),
					HttpStatus.CREATED
			);
		}
		catch (Exception e) {
			return new ResponseEntity<>(
					new ApiResponse(false, "Category with this name already exist!"),
					HttpStatus.BAD_REQUEST
			);
		}
	}

	/**
	 * Updates a category with the specified ID in the database.
	 *
	 * @param category the updated category object
	 * @param id the ID of the category to update
	 * @return ResponseEntity containing a success message and a status code of 202 Accepted if successful,
	 *         or a failure message and a status code of 400 Bad Request if the category does not exist.
	 */
	@PostMapping("/{category_id}")
	public ResponseEntity updateCategoryById(@RequestBody Category category, @PathVariable("category_id") Integer id) {
		try {
			categoryService.updateById(category, id);
			return new ResponseEntity<>(
					new ApiResponse(true, "Category was update successfully."),
					HttpStatus.ACCEPTED
			);
		}
		catch (Exception e) {
			return new ResponseEntity<>(
					new ApiResponse(false, "Category does not Exist!"),
					HttpStatus.BAD_REQUEST
			);
		}
	}
}
