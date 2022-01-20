package com.santosh.ms.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.santosh.ms.account.dto.AccountDto;
import com.santosh.ms.account.entity.Account;
import com.santosh.ms.account.service.AccountService;


@RestController
public class AccountController {

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService accountService;

    @GetMapping("/check-balance/{accountNo}")
    public String checkBalance(@PathVariable long accountNo){
        return "available balance is: "+accountService.checkBalance(accountNo);
    }

    @GetMapping("/deposit-money/{accountNo},{money}")
    public String depositMoney(@PathVariable long accountNo ,@PathVariable double money){
        return "available balance is: "+accountService.depositMoney(accountNo,money);
    }

    @GetMapping("/{accountNo}")
    public Account findByAccountNumber(@PathVariable("accountNo") long accountNo){
        return accountService.findByAccountNumber(accountNo);
    }

    @PostMapping("/update")
    public Account saveAccount(@RequestBody AccountDto request){
        return accountService.update(request);
    }
}
