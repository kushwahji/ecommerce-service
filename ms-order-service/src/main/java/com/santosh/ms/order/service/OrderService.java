/**
 * 
 */
package com.santosh.ms.order.service;


import com.santosh.ms.order.response.OrderHistoryResponse;
import com.santosh.ms.order.response.OrderResponse;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
public interface OrderService {
	
	OrderHistoryResponse myOrder(String phone);

	OrderResponse order(String phone, long accountNumber);
}
