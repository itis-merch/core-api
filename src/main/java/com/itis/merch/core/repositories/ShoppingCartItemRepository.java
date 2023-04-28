package com.itis.merch.core.repositories;

import com.itis.merch.core.models.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Integer> {

}