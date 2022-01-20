package com.santosh.ms.fundtransfer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.santosh.ms.fundtransfer.dto.AccountDto;


@FeignClient(name = "http://MS-ACCOUNT/account/")
public interface AccountFeignClient {

    @GetMapping("{accountNo}")
    AccountDto findByAccountNumberAndStatusTrue(@PathVariable("accountNo") long accountNo);

    @PostMapping("update")
    AccountDto save(@RequestBody AccountDto account);
}
