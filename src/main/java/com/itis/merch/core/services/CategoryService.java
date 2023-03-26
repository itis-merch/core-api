package com.itis.merch.core.services;

import com.itis.merch.core.dto.category.CategoryDTO;
import com.itis.merch.core.models.Category;
import com.itis.merch.core.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    public List<CategoryDTO> getAll(){
        return categoryRepo.findAll().stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
    }

    public CategoryDTO getById(Integer id) throws Exception {
        Category category = categoryRepo.findById(id).get();

        if(category == null){
            throw new Exception("Категория не найдена");
        }

        return new CategoryDTO(category);
    }

    public Category create(Category category) throws Exception{
        if(categoryRepo.findByName(category.getName()) != null){
            throw new Exception("Такая категория уже существует");
        }
        return categoryRepo.save(category);
    }

    public Category updateById(Category category, Integer id) throws Exception{
        Category updatedCategory = categoryRepo.findById(id).get();

        if(updatedCategory == null){
            throw new Exception("Категория не существует");
        }

        updatedCategory.setName(category.getName());
        updatedCategory.setDescription(category.getDescription());

        return categoryRepo.save(updatedCategory);
    }
}
