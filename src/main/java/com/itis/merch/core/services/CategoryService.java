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
