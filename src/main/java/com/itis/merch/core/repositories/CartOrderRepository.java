package com.itis.merch.core.repositories;

import com.itis.merch.core.models.CartOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartOrderRepository extends JpaRepository<CartOrder, Integer> {
	Optional<CartOrder> findCartOrderByUserId(Integer userId);

}
