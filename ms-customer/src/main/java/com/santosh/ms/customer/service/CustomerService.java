/**
 * 
 */
package com.santosh.ms.customer.service;

import com.santosh.ms.customer.request.CustomerDto;
import com.santosh.ms.customer.response.CustomerResponse;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
public interface CustomerService {

	CustomerResponse save(CustomerDto request);

	CustomerResponse getCustomer(String phone);

}
