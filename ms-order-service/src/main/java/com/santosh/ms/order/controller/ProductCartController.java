/**
 * 
 */
package com.santosh.ms.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.santosh.ms.order.request.OrderItemDto;
import com.santosh.ms.order.service.ProductCartService;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@RestController
public class ProductCartController {

	@Autowired
	ProductCartService cartService;

	@GetMapping(value = "/cart/carts")
	public ResponseEntity<List<OrderItemDto>> getCart(@RequestParam String phone) {
		return new ResponseEntity<>(cartService.getCart(phone), HttpStatus.OK);
	}

	@GetMapping(value = "/cart/add-to-cart", params = {"phone", "productId", "quantity",  })
	public ResponseEntity<String> addToCart(@RequestParam String phone ,@RequestParam Long productId,
			@RequestParam Integer quantity) {
		cartService.addItemToCart(phone, productId, quantity);
		return new ResponseEntity<>("Item added successfully", HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/cart/delete-cart")
	public ResponseEntity<String> removeFromCart(@RequestParam("phone") String phone) {
		cartService.deleteCart(phone);
		return new ResponseEntity<>("cart deleted successfully", HttpStatus.OK);
	}
}
