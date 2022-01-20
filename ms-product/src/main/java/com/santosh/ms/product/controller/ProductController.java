/**
 * 
 */
package com.santosh.ms.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.santosh.ms.product.response.ProductResponseDto;
import com.santosh.ms.product.service.ProductService;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;

	@GetMapping("/search")
	public List<ProductResponseDto> search(@RequestParam String title) {
		return productService.search(title);
	}
	
	@GetMapping("/products")
	public List<ProductResponseDto> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/")
	public ProductResponseDto getProducts(@RequestParam long productId) {
		return productService.getProduct(productId);
	}
}
