package com.itis.merch.core.controllers;

import com.itis.merch.core.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping
	public ResponseEntity getCategories() {
		try{
			return ResponseEntity.ok(categoryService.getAll);
		}
		catch (Exception e){
			return ResponseEntity.badRequest().body("Error!");
		}
	}
}
