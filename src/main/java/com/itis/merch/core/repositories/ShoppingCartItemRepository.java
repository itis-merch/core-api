package com.itis.merch.core.repositories;

import com.itis.merch.core.models.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for ShoppingCartItem model.
 */
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Integer> {

}

