package com.santosh.ms.product.response;

import lombok.Data;
import java.math.BigDecimal;

import com.santosh.ms.product.entity.Product;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@Data
public class ProductResponseDto {
	private long productId;
	private String name;
	private String sku;
	private String description;
	private BigDecimal price;
	private String imageUrl;

	public ProductResponseDto(Product products) {
		this.productId = products.getId();
		this.name = products.getName();
		this.sku = products.getSku();
		this.description = products.getDescription();
		this.price = products.getPrice();
		this.imageUrl = products.getImageUrl();
	}

	public ProductResponseDto(ProductResponseDto products) {
		this.name = products.getName();
		this.sku = products.getSku();
		this.description = products.getDescription();
		this.price = products.getPrice();
		this.imageUrl = products.getImageUrl();
	}
}