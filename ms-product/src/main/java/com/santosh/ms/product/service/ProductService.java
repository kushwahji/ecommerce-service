/**
 * 
 */
package com.santosh.ms.product.service;

import java.util.List;

import com.santosh.ms.product.response.ProductResponseDto;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
public interface ProductService {
	
	List<ProductResponseDto> search(String title);

	List<ProductResponseDto> getAllProducts();

	ProductResponseDto getProduct(long productId);

}
