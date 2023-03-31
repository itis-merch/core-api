package com.itis.merch.core.services;

import com.itis.merch.core.dto.category.CategoryDTO;
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
}
