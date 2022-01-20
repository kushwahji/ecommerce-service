/**
 * 
 */
package com.santosh.ms.order.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.santosh.ms.order.client.ProductFeignClient;
import com.santosh.ms.order.entity.Order;
import com.santosh.ms.order.repository.ProductRepository;

import lombok.Data;

/**
 * @author santosh.kushwah
 *
 */
@Data
public class OrderHistoryResponse {
	private List<OrderDetails> orderDetails;
	public OrderHistoryResponse(Order order) {
		
	}
	
    public OrderHistoryResponse(String phone, List<OrderDetails> odDetails) {
		this.orderDetails = odDetails;	
	}

	@Data
	public static class OrderDetails{
		private String phone;
		private String customer;
		private String status;
		private BigDecimal totalAmount;
		private String payStatus;
		private String orderTrackingId;
		private String shippingAddress;
		List<OrderItemResponseDto> items;
		
		public OrderDetails(){
			
			
		}
		public OrderDetails(Order order,ProductFeignClient productRepository) {
			this.phone =order.getPhone();
			this.totalAmount = order.getTotal();
			this.customer = order.getName();
			this.status = order.getStatus();
			this.payStatus = order.getPayStatus();
			this.orderTrackingId = order.getTrackingNumber();
			this.shippingAddress = order.getAddress();
			this.items = order.getItems().stream().map(it->new OrderItemResponseDto(it,productRepository)).collect(Collectors.toList());
		}
	}
}
