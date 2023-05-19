package com.itis.merch.core.repositories;

import com.itis.merch.core.models.Product;
import com.itis.merch.core.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
}
