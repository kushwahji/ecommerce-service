/**
 * 
 */
package com.santosh.ms.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santosh.ms.customer.entity.Customer;

/**
 * @author santosh.kushwah
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByPhone(String phone);
}
