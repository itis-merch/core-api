/**
 * The ProductRepository interface extends the JpaRepository interface
 * for the {@code Product} class and provides additional methods for
 * accessing product data from the database.
 * <p>
 * This interface is annotated with the {@code @Repository} annotation,
 * indicating that it is a Spring Data repository component.
 */
package com.itis.merch.core.repositories;

import com.itis.merch.core.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}