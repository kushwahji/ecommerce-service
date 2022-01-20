/**
 * 
 */
package com.santosh.ms.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santosh.ms.product.entity.Product;

/**
 * @author santosh.kushwah
 * @since 16-01-2022
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Product findByCategoryId(Long id);
	
	List<Product> findByNameContainingOrDescriptionContaining(String title, String title2);
}
