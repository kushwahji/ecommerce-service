/**
 * 
 */
package com.santosh.ms.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santosh.ms.order.entity.ProductCategory;

/**
 * @author santosh.kushwah
 *
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
