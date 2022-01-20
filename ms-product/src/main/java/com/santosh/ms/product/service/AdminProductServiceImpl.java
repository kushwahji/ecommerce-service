/**
 * 
 */
package com.santosh.ms.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.santosh.ms.product.entity.Product;
import com.santosh.ms.product.entity.ProductCategory;
import com.santosh.ms.product.repository.ProductCategoryRepository;
import com.santosh.ms.product.repository.ProductRepository;
import com.santosh.ms.product.request.ProductRequestCategoryDto;
import com.santosh.ms.product.request.ProductRequestDto;

/**
 * @author santosh.kushwah
 * @since 14-01-2022
 */
@Service
public class AdminProductServiceImpl implements AdminProductService {
	
	@Autowired
	ProductRepository productRepository; 
	
	@Autowired
	ProductCategoryRepository productCategoryRepository; 

	@Override
	public List<Product> addProduct(List<ProductRequestDto> product) {
		return product.stream().map(p->productRepository.save(new Product(p))).collect(Collectors.toList());
	}

	@Override
	public List<ProductCategory> addCategory(List<ProductRequestCategoryDto> productCategory) {
		return productCategory.stream().map(p->productCategoryRepository.save(new ProductCategory(p))).collect(Collectors.toList());
	}

}
