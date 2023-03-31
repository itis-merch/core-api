/**
 * The CategoryRepository interface is responsible for managing the persistence of Category objects in the database.
 * This interface extends the JpaRepository interface, which provides basic CRUD operations for Category entities.
 * It also specifies that the primary key for Category entities is an Integer.
 *
 * In addition to the basic CRUD operations, this interface defines a custom method, findByName(), which takes a String
 * parameter representing the name of the category to search for. This method returns a Category object that matches the
 * specified name.
 *
 * Note that this method does not require an implementation, as Spring Data JPA will automatically generate the necessary
 * SQL query based on the method name.
 */

package com.itis.merch.core.repositories;

import com.itis.merch.core.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Category findByName(String name);
}
