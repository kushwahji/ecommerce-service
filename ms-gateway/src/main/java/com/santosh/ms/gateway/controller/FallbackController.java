/**
 * 
 */
package com.santosh.ms.gateway.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author santosh.kushwah
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/fallback")
public class FallbackController {
	
	@GetMapping("/account")
    public String accountServiceFallback(){
        return "Account service is down......";
    }
	
	@GetMapping("/fund")
    public String fundServiceFallback(){
        return "Fund service is down......";
    }
	
	@GetMapping("/customer")
    public String customerServiceFallback(){
        return "Customer service is down......";
    }
	
	@GetMapping("/product")
    public String productServiceFallback(){
        return "Product service is down......";
    }
	
	@GetMapping("/order")
    public String orderServiceFallback(){
        return "Order service is down......";
    }

}
