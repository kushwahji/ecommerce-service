package com.santosh.ms.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.santosh.ms.order.client.response.ProductDto;


@FeignClient(name = "http://MS-PRODUCT")
public interface ProductFeignClient {

    @GetMapping("/product/")
	ProductDto getProducts(@RequestParam long productId);
}
