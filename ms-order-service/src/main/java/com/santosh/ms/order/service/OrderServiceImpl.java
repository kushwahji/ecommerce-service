/**
 * 
 */
package com.santosh.ms.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.santosh.ms.order.client.CustomerFeignClient;
import com.santosh.ms.order.client.FundFeignClient;
import com.santosh.ms.order.client.ProductFeignClient;
import com.santosh.ms.order.constant.Constant;
import com.santosh.ms.order.entity.Order;
import com.santosh.ms.order.entity.Product;
import com.santosh.ms.order.exception.MsApplicationException;
import com.santosh.ms.order.helper.Helper;
import com.santosh.ms.order.repository.OrderRepository;
import com.santosh.ms.order.repository.ProductRepository;
import com.santosh.ms.order.request.FundTransferDto;
import com.santosh.ms.order.request.OrderItemDto;
import com.santosh.ms.order.response.OrderHistoryResponse;
import com.santosh.ms.order.response.OrderHistoryResponse.OrderDetails;
import com.santosh.ms.order.response.OrderResponse;
import com.santosh.ms.order.response.ProductResponseDto;

/**
 * @author santosh.kushwah
 * @since 15-01-2022
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Value("${enterprice_to_account_number}")
	private long toAccountNumber;

	@Autowired
	CustomerFeignClient customerFeignClient;

	@Autowired
	FundFeignClient fundFeignClient;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductCartService cartService;
	
	@Autowired
	ProductFeignClient productFeignClient;
	

	@Autowired
	Helper helper;

	@Override
	public OrderResponse order(String phone, long accountNumber) {
		helper.getCustomer(phone);
		List<OrderItemDto> carts = cartService.getCart(phone);
		if (!carts.isEmpty()) {
			return new OrderResponse(Constant.ORDER_PLACED,placeOrder(phone, accountNumber, carts));
		} else {
			throw new MsApplicationException(Constant.ITEM_NOT_FOUND, Constant.ITEM_NOT_FOUND_MSG);
		}

	}

	@Override
	public OrderHistoryResponse myOrder(String phone) {
		List<Order> orders = orderRepository.findByPhone(phone);
		if(orders.isEmpty()) {
			throw new MsApplicationException(Constant.ORDER_NOT_FOUND,Constant.ORDER_NOT_FOUND_MSG.replace("PHONE",phone));
		}
		return new OrderHistoryResponse(phone, orders.stream().map(o->new OrderDetails(o,productFeignClient)).collect(Collectors.toList()));
	}

	private String placeOrder(String phone, long accountNumber, List<OrderItemDto> carts) {
		Order order = new Order(helper.getCustomer(phone), accountNumber, carts);
		payment(accountNumber, order.getTotal(), "Place Order");
		orderRepository.save(order);
		cartService.deleteCart(phone);
		return order.getTrackingNumber();

	}

	public void payment(long accountNumber, BigDecimal amount, String remarks) {
		if (amount.doubleValue() > 50) {
			fundFeignClient
					.transfer(new FundTransferDto(accountNumber, toAccountNumber, amount.doubleValue(), remarks));
		} else {
			throw new MsApplicationException(Constant.MINIMUM_AMONT,
					Constant.MINIMUM_AMONT_MSG);

		}
	}

	@Cacheable(value = "productResponseDtoCache", key = "#productId")
	public ProductResponseDto getProducts(long productId) {
		Optional<Product> products = productRepository.findById(productId);
		if (products.isPresent()) {
			return new ProductResponseDto(products.get());
		}
		return null;
	}
}
