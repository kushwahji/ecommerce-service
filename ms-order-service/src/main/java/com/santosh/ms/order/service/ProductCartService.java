/**
 * 
 */
package com.santosh.ms.order.service;

import java.util.List;
import com.santosh.ms.order.request.OrderItemDto;


/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
public interface ProductCartService {

	public void addItemToCart(String phone, Long productId, Integer quantity);

	public List<OrderItemDto> getCart(String phone);

	public void changeItemQuantity(String phone, Long productId, Integer quantity);

	public void deleteItemFromCart(String phone, Long productId);

	public boolean checkIfItemIsExist(String phone, Long productId);

	public void deleteCart(String phone);

	public void deleteFromCart(String cartId, OrderItemDto item);

}
