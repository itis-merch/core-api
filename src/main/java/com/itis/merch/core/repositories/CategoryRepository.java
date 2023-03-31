package com.itis.merch.core.repositories;

import com.itis.merch.core.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
