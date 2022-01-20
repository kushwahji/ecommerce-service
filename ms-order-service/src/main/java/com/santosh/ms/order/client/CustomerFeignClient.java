package com.santosh.ms.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.santosh.ms.order.response.CustomerResponse;



@FeignClient(name = "http://MS-CUSTOMER")
public interface CustomerFeignClient {

    @GetMapping("/customer/{phone}")
    CustomerResponse getCustomer(@RequestParam String phone);

}
