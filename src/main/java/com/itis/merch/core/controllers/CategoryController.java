package com.itis.merch.core.controllers;

import com.itis.merch.core.common.ApiResponse;
import com.itis.merch.core.dto.category.CategoryDTO;
import com.itis.merch.core.exceptions.CustomException;
import com.itis.merch.core.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * The CategoryController class is a REST API controller that handles all HTTP requests related to categories.
 * It provides endpoints for retrieving, creating, and updating categories.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
	private final CategoryService categoryService;

	/**
	 * Retrieves all categories from the database.
	 *
	 * @return ResponseEntity containing the list of categories and a status code of 200 OK.
	 */
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getAllCategories() {
		return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
	}

	/**
	 * Retrieves a category with the specified ID from the database.
	 *
	 * @param categoryId the ID of the category to retrieve.
	 * @return ResponseEntity containing the category DTO object and a status code of 302 FOUND.
	 */
	@GetMapping("/{category_id}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("category_id") final Integer categoryId) throws CustomException {
		return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.FOUND);
	}

	/**
	 * Creates a new category by processing a POST request with the specified category DTO.
	 *
	 * @param categoryDTO the DTO containing the information for the category to be created.
	 * @return a ResponseEntity containing an ApiResponse indicating whether the category was
	 * created successfully or not, and the appropriate HTTP status code.
	 */
	@PostMapping
	@PreAuthorize("hasAuthority('categories::write')")
	public ResponseEntity<ApiResponse> createCategory(@RequestBody final CategoryDTO categoryDTO) throws CustomException {
		if (Objects.nonNull(categoryService.getCategoryByName(categoryDTO.getName()))) {
			throw new CustomException("Category with this name already exists.", HttpStatus.CONFLICT);
		}

		categoryService.createCategory(categoryDTO);
		return new ResponseEntity<>(new ApiResponse(true, "Category created successfully."), HttpStatus.CREATED);
	}

	/**
	 * Updates a category with the specified ID in the database.
	 *
	 * @param categoryDTO the updated category DTO object.
	 * @param categoryID  the ID of the category to update.
	 * @return ResponseEntity containing a success message and a status code of 200 OK.
	 */
	@PostMapping("/{category_id}")
	@PreAuthorize("hasAuthority('categories::write')")
	public ResponseEntity<ApiResponse> updateCategoryById(@PathVariable("category_id") final Integer categoryID,
	                                                      @RequestBody final CategoryDTO categoryDTO) throws CustomException {
		categoryService.updateCategoryById(categoryDTO, categoryID);
		return new ResponseEntity<>(
						new ApiResponse(true, "Category was updated successfully."),
						HttpStatus.OK
		);
	}
}
