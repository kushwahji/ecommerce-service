/**
 * 
 */
package com.santosh.ms.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santosh.ms.product.entity.ProductCategory;

/**
 * @author santosh.kushwah
 *
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
