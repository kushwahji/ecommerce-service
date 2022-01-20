/**
 * 
 */
package com.santosh.ms.order.controller;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.santosh.ms.order.response.OrderHistoryResponse;
import com.santosh.ms.order.response.OrderResponse;
import com.santosh.ms.order.service.OrderService;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/checkout")
	public OrderResponse order(@Valid @RequestParam("phone") @Size(min = 10,max = 10,message = "phone should be 10 digits only.") String phone, @RequestParam("accountNumber") long accountNumber) {
		return orderService.order(phone,accountNumber);
	}

	@GetMapping("/my-order")
	public OrderHistoryResponse myOrder(@RequestParam String phone){
		return orderService.myOrder(phone);
	}
}
