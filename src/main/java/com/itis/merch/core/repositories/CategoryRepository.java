/**
 * The CategoryRepo interface extends the JpaRepository interface and defines methods for interacting with the Category table in the database.
 * It provides a method for finding a category by name.
 *
 * @author [Marat]
 * @version 1.0
 * @since 2023.03.31
 */

package com.itis.merch.core.repositories;

import com.itis.merch.core.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	/**
	 * Finds a category in the database with the specified name.
	 *
	 * @param name the name of the category to find
	 * @return the category object if found, or null if not found
	 */
	Category findByName(String name);
}
