/**
 * 
 */
package com.santosh.ms.customer.controller;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.santosh.ms.customer.request.CustomerDto;
import com.santosh.ms.customer.response.CustomerResponse;
import com.santosh.ms.customer.service.CustomerService;

/**
 * @author santosh.kushwah
 *
 */
@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/save")
	public ResponseEntity<CustomerResponse> save(@Valid @RequestBody CustomerDto request) {
		return ResponseEntity.accepted().body(customerService.save(request));
	}
	@GetMapping("/{phone}")
	public ResponseEntity<CustomerResponse> getCustomer(@Valid @RequestParam @Size(min = 10,max = 10,message = "phone should be 1o digits only. ") String phone) {
		return ResponseEntity.accepted().body(customerService.getCustomer(""+phone));
	}

}
