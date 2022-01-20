package com.santosh.ms.order.response;

import lombok.Data;
import java.math.BigDecimal;

import com.santosh.ms.order.client.response.ProductDto;
import com.santosh.ms.order.entity.Product;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@Data
public class ProductResponseDto {
	private String name;
	private String sku;
	private String description;
	private BigDecimal price;
	private String imageUrl;

	public ProductResponseDto(Product products) {
		this.name = products.getName();
		this.sku = products.getSku();
		this.description = products.getDescription();
		this.price = products.getPrice();
		this.imageUrl = products.getImageUrl();
	}

	public ProductResponseDto(ProductDto products) {
		this.name = products.getName();
		this.sku = products.getSku();
		this.description = products.getDescription();
		this.price = products.getPrice();
		this.imageUrl = products.getImageUrl();
	}
}