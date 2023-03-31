/**
 * The CategoryController class is responsible for handling HTTP requests related to Category objects.
 * This class uses the CategoryService class to perform operations on the Category objects.
 *
 * This class is annotated with @RestController, which indicates that it is a controller that handles requests and
 * returns responses in JSON format.
 *
 * The @RequestMapping annotation specifies the base URL for all requests handled by this controller.
 *
 * This class contains four methods:
 *  - getCategories(): handles GET requests to retrieve all Category objects in the database. Returns a ResponseEntity
 *                    object with the result of the operation.
 *  - getCategoryById(): handles GET requests to retrieve a specific Category object by ID. Takes an Integer parameter
 *                       representing the ID of the Category to retrieve. Returns a ResponseEntity object with the result
 *                       of the operation.
 *  - createCategory(): handles POST requests to create a new Category object. Takes a Category object in the request
 *                      body. Returns a ResponseEntity object with the result of the operation.
 *  - updateCategoryById(): handles POST requests to update an existing Category object by ID. Takes a Category object in
 *                          the request body and an Integer parameter representing the ID of the Category to update.
 *                          Returns a ResponseEntity object with the result of the operation.
 *
 *  In each of these methods, a try-catch block is used to catch any exceptions that may occur during the operation. If an
 *  exception occurs, a bad request response is returned with an error message.
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

	@GetMapping
	public ResponseEntity getCategories() {
		try {
			return ResponseEntity.ok(categoryService.getAll());
		}
		catch (Exception e){
			return ResponseEntity.badRequest().body("Error!");
		}
	}

	@GetMapping("/{category_id}")
	public ResponseEntity getCategoryById(@PathVariable("category_id") Integer id) {
		try {
			return ResponseEntity.ok(categoryService.getById(id));
		}
		catch (Exception e){
			return ResponseEntity.badRequest().body("Error!");
		}
	}

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
