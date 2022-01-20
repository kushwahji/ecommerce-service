/**
 * 
 */
package com.santosh.ms.product.service;

import java.util.List;

import com.santosh.ms.product.entity.Product;
import com.santosh.ms.product.entity.ProductCategory;
import com.santosh.ms.product.request.ProductRequestCategoryDto;
import com.santosh.ms.product.request.ProductRequestDto;

/**
 * @author santosh.kushwah
 *
 */
public interface AdminProductService {

	List<Product> addProduct(List<ProductRequestDto> product);

	List<ProductCategory> addCategory(List<ProductRequestCategoryDto> product);

}
