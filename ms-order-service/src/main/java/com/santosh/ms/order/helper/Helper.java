package com.santosh.ms.order.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.santosh.ms.order.client.CustomerFeignClient;
import com.santosh.ms.order.constant.Constant;
import com.santosh.ms.order.entity.OrderItem;
import com.santosh.ms.order.exception.MsApplicationException;
import com.santosh.ms.order.response.CustomerResponse;
import com.santosh.ms.order.utils.DateConverter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author santosh.kushwah
 * @since: 12-12-2021
 */

@Component
public class Helper {

	@Autowired
	CustomerFeignClient customerFeignClient;

	static Random r = new Random();

	private Helper() {

	}

	public static long generateAccountNumber() {
		return Long.parseLong(DateConverter.convertDateToString(new Date(), "ddMMyyyyhhmmss") + generateRandom(1, 5));
	}

	public static String generateReferenceNumber() {
		return UUID.randomUUID().toString();
	}

	private static int generateRandom(int minimum, int maximum) {
		int rand = r.nextInt(50);
		return (rand * maximum) + minimum;
	}

	public static String generateOrderNumber() {
		return "OD" + DateConverter.convertDateToString(new Date(), "ddMMyyyyhhmmss") + generateRandom(1, 5);
	}

	public static BigDecimal countTotalPrice(List<OrderItem> cart) {
		final BigDecimal total = BigDecimal.ZERO;
		cart.stream().map(m -> total.add(m.getProductPrice())).collect(Collectors.toList());
		return total;
	}

	public static BigDecimal getSubTotalForItem(BigDecimal price, int quantity) {
		return price.multiply(BigDecimal.valueOf(quantity));
	}

	public CustomerResponse getCustomer(String phone) {
		CustomerResponse customer = null;
		try {
			customer = customerFeignClient.getCustomer(phone);
			if (customer == null) {
				throw new MsApplicationException(Constant.CUSTOMER_NOT_FOUND, Constant.CUSTOMER_NOT_FOUND_MSG);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

}
