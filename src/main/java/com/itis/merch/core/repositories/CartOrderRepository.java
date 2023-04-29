/**

 This interface represents the repository for the CartOrder entity.
 */
package com.itis.merch.core.repositories;

import com.itis.merch.core.models.CartOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartOrderRepository extends JpaRepository<CartOrder, Integer> {
	/**
	 * Finds a CartOrder object based on the given user id.
	 *
	 * @param userId The user id for which the cart order is being searched.
	 * @return Optional containing the CartOrder object if it exists, otherwise empty.
	 */
	Optional<CartOrder> findCartOrderByUserId(Integer userId);

}