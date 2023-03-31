package com.itis.merch.core.controllers;

import com.itis.merch.core.models.Category;
import com.itis.merch.core.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity getCategoryById(@RequestParam Integer id) {
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
			return ResponseEntity.ok().body("Category was create successfully.");

		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body("Error!");
		}
	}
}
