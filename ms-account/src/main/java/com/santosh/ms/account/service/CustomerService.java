package com.santosh.ms.account.service;
import com.santosh.ms.account.dto.CustomerRequestDto;
import com.santosh.ms.account.entity.Customer;


public interface CustomerService {
    Customer register(CustomerRequestDto customer);
}
