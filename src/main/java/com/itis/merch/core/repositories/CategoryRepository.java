/**
 * The CategoryRepository interface is responsible for managing the persistence of Category objects in the database.
 * This interface extends the JpaRepository interface, which provides basic CRUD operations for Category entities.
 * It also specifies that the primary key for Category entities is an Integer.
 */

package com.itis.merch.core.repositories;

import com.itis.merch.core.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
