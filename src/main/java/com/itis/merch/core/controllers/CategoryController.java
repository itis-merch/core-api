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
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity getCategories(){
        try{
            return ResponseEntity.ok(categoryService.getAll());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/{category_id}")
    public ResponseEntity getCategoryById(@RequestParam Integer id){
        try{
            return ResponseEntity.ok(categoryService.getById(id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping
    public ResponseEntity createCategory(@RequestBody Category category){
        try{
            categoryService.create(category);
            return ResponseEntity.ok().body("Success!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping("/{category_id}")
    public ResponseEntity updateCategoryById(@RequestBody Category category, @RequestParam("id") Integer id){
        try{
            categoryService.updateById(category, id);
            return ResponseEntity.ok().body("Пользователь успешно обновлен");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
