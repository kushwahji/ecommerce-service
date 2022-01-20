/**
 * 
 */
package com.santosh.ms.order.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.santosh.ms.order.client.ProductFeignClient;
import com.santosh.ms.order.client.response.ProductDto;
import com.santosh.ms.order.constant.Constant;
import com.santosh.ms.order.exception.MsApplicationException;
import com.santosh.ms.order.helper.Helper;
import com.santosh.ms.order.redis.RedisService;
import com.santosh.ms.order.request.OrderItemDto;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@Service
public class ProductCartServiceImpl implements ProductCartService {

	@Autowired
	RedisService redisService;

	@Autowired
	ProductFeignClient productFeignClient;
	
	@Autowired
	Helper helper;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<OrderItemDto> getCart(String cartId) {
		List<OrderItemDto> response = (List) redisService.getCart(cartId, OrderItemDto.class);
		if (response.isEmpty()) {
			throw new MsApplicationException(Constant.ITEM_NOT_FOUND,
					Constant.ITEM_NOT_FOUND_MSG);
		}
		return response;
	}

	@Override
	public void deleteCart(String cartId) {
		redisService.deleteCart(cartId);
	}

	@Override
	public void addItemToCart(String phone, Long productId, Integer quantity) {
		helper.getCustomer(phone);
		ProductDto product = getProduct(productId);
		OrderItemDto item = new OrderItemDto(quantity, product.getProductId(),
				Helper.getSubTotalForItem(product.getPrice(), quantity));
		redisService.addItemToCart(phone, item);
	}

	@Override
	public void changeItemQuantity(String cartId, Long productId, Integer quantity) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<OrderItemDto> cart = (List) redisService.getCart(cartId, OrderItemDto.class);
		for (OrderItemDto item : cart) {
			if ((item.getProductId()).equals(productId)) {
				redisService.deleteItemFromCart(cartId, item);
				item.setQuantity(quantity);
				item.setPrice(Helper.getSubTotalForItem(getProduct(item.getProductId()).getPrice(), quantity));
				redisService.addItemToCart(cartId, item);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteItemFromCart(String cartId, Long productId) {
		@SuppressWarnings("rawtypes")
		List<OrderItemDto> cart = (List) redisService.getCart(cartId, OrderItemDto.class);
		for (OrderItemDto item : cart) {
			if ((item.getProductId()).equals(productId)) {
				redisService.deleteItemFromCart(cartId, item);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean checkIfItemIsExist(String cartId, Long productId) {
		@SuppressWarnings("unchecked")
		List<OrderItemDto> cart = (List) redisService.getCart(cartId, OrderItemDto.class);
		for (OrderItemDto item : cart) {
			if ((item.getProductId()).equals(productId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void deleteFromCart(String phone, OrderItemDto item) {
		redisService.deleteItemFromCart(phone, item);
	}

	public ProductDto getProduct(long productId) {
		return productFeignClient.getProducts(productId);

	}

}
