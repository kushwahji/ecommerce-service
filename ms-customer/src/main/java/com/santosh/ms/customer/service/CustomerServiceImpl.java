/**
 * 
 */
package com.santosh.ms.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.santosh.ms.customer.constant.Constant;
import com.santosh.ms.customer.entity.Customer;
import com.santosh.ms.customer.exception.MsApplicationException;
import com.santosh.ms.customer.repository.CustomerRepository;
import com.santosh.ms.customer.request.CustomerDto;
import com.santosh.ms.customer.response.CustomerResponse;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository repository;

	@Override
	public CustomerResponse save(CustomerDto request) {
		if(repository.findByPhone(request.getPhone())!=null) {
			throw new MsApplicationException(Constant.CUSTOMER_ALREADY_EXIT,Constant.CUSTOMER_ALREADY_EXIT_MSG.replace("PHONE", request.getPhone()));
		};
		Customer customer = repository.save(new Customer(request));
		return new CustomerResponse(customer);
	}

	@Override
	public CustomerResponse getCustomer(String phone) {
		Customer customer = repository.findByPhone(phone);
		if(customer == null) {
			throw new MsApplicationException(Constant.CUSTOMER_NOT_FOUND,Constant.CUSTOMER_NOT_FOUND_MSG.replace("PHONE", phone));
		}
		return new CustomerResponse(customer);
		
	}

}
