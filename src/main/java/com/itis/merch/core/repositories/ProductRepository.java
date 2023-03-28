package com.itis.merch.core.repositories;

import com.itis.merch.core.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
