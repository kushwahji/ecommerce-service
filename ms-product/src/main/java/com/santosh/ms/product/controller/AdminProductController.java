/**
 * 
 */
package com.santosh.ms.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.santosh.ms.product.service.AdminProductService;
import com.santosh.ms.product.entity.Product;
import com.santosh.ms.product.entity.ProductCategory;
import com.santosh.ms.product.request.ProductRequestCategoryDto;
import com.santosh.ms.product.request.ProductRequestDto;

/**
 * @author santosh.kushwah
 * @since 14-01-2022
 * 
 */
@RestController
public class AdminProductController {

	@Autowired
	private AdminProductService adminProductService;

	@PostMapping(value = "/products")
	public ResponseEntity<List<Product>> addProduct(@RequestBody List<ProductRequestDto> product) {
		return new ResponseEntity<>(adminProductService.addProduct(product), HttpStatus.CREATED);
	}
	@PostMapping(value = "/category")
	public ResponseEntity<List<ProductCategory>> addCategory(@RequestBody List<ProductRequestCategoryDto> product) {
		return new ResponseEntity<>(adminProductService.addCategory(product), HttpStatus.CREATED);
	}
}
