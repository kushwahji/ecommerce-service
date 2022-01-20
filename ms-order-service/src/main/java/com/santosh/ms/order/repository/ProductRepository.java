/**
 * 
 */
package com.santosh.ms.order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santosh.ms.order.entity.Product;

/**
 * @author santosh.kushwah
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Page<Product> findByCategoryId(Long id, Pageable pageable);

    Page<Product> findByNameContainingOrDescriptionContaining(String name,String desc, Pageable pageable);
}
