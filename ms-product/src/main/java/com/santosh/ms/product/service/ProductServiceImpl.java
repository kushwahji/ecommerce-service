/**
 * 
 */
package com.santosh.ms.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.santosh.ms.product.constant.Constant;
import com.santosh.ms.product.entity.Product;
import com.santosh.ms.product.exception.MsApplicationException;
import com.santosh.ms.product.repository.ProductRepository;
import com.santosh.ms.product.response.ProductResponseDto;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<ProductResponseDto> search(String title) {
		List<Product> products = productRepository.findByNameContainingOrDescriptionContaining(title,title);
		if (!products.isEmpty()) {
			return products.stream().map(ProductResponseDto::new).collect(Collectors.toList());
		} else {
			throw new MsApplicationException(Constant.PRODUCT_NOT_FOUND,Constant.PRODUCT_NOT_FOUND_MSG.replace("ID", title));
		}
	}

	@Override
	public List<ProductResponseDto> getAllProducts() {
		List<Product> products = productRepository.findAll();
		if (!products.isEmpty()) {
			return products.stream().map(ProductResponseDto::new).collect(Collectors.toList());
		} else {
			throw new MsApplicationException(Constant.PRODUCT_NOT_FOUND,Constant.PRODUCT_NOT_FOUND);
		}
	}

	@Override
	public ProductResponseDto getProduct(long productId) {
		Optional<Product> products = productRepository.findById(productId);
		if (products.isPresent()) {
			return new ProductResponseDto(products.get());
		} else {
			throw new MsApplicationException(Constant.PRODUCT_NOT_FOUND,Constant.PRODUCT_NOT_FOUND_MSG.replace("ID", "" + productId));
		}
	}

}
