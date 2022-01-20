package com.santosh.ms.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.santosh.ms.order.request.FundTransferDto;
import com.santosh.ms.order.response.FundTransferResponse;

@FeignClient(name = "http://MS-FUNDTRANSFER")
public interface FundFeignClient {

    @GetMapping("/fund/transfer")
    FundTransferResponse transfer(@RequestBody FundTransferDto transfer);
}
