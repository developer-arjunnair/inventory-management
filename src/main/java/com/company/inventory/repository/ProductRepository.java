package com.company.inventory.repository;

import com.company.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author anair@wayfair.com
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
