package com.santosh.ms.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.santosh.ms.account.dto.CustomerRequestDto;
import com.santosh.ms.account.entity.Customer;
import com.santosh.ms.account.service.CustomerService;

import javax.validation.Valid;

@RestController
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @PostMapping("/customer/apply")
    public Customer register(@Valid @RequestBody CustomerRequestDto customer){
        logger.info("Inside account-controller - register()");
        return customerService.register(customer);
    }
}
