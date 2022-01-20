package com.santosh.ms.account.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.santosh.ms.account.constant.AppConstants;
import com.santosh.ms.account.dto.CustomerRequestDto;
import com.santosh.ms.account.entity.Customer;
import com.santosh.ms.account.exception.MsApplicationException;
import com.santosh.ms.account.repository.CustomerRepository;
import com.santosh.ms.account.service.CustomerService;

import javax.transaction.Transactional;

/**
 * @author santosh.kushwah
 * @since: 12-12-2021
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    @Override
    public Customer register(CustomerRequestDto customer) {
        Customer response = null;

        logger.info("Inside Bank-Service-Impl - register()");
        if (customerRepository.findByAadhaar(customer.getAadhaar()) != null) {
            throw new MsApplicationException(AppConstants.RECORD_ALREADY_EXITS, AppConstants.RECORD_EXITS_PHONE_UID);
        }
        response = customerRepository.save(new Customer(customer));

        return response;
    }
}