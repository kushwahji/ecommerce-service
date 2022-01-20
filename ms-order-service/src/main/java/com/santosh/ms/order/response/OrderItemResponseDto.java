/**
 * 
 */
package com.santosh.ms.order.response;

import java.math.BigDecimal;

import com.santosh.ms.order.client.ProductFeignClient;
import com.santosh.ms.order.client.response.ProductDto;
import com.santosh.ms.order.entity.OrderItem;

import lombok.Data;

/**
 * @author santosh.kushwah
 *
 */
@Data
public class OrderItemResponseDto {
	private BigDecimal totalPrice;
	private int quantity;
	private long productId;
	private ProductResponseDto products;
	
	public OrderItemResponseDto(OrderItem items,ProductFeignClient productRepository) {
		ProductDto d =productRepository.getProducts(items.getProductId());
		this.totalPrice = d.getPrice().multiply(new BigDecimal(items.getQuantity()));
		this.quantity = items.getQuantity();
		this.productId = items.getProductId();
		this.products = new ProductResponseDto(d);
	}
}
