package com.santosh.ms.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santosh.ms.account.entity.Customer;

/**
 * @Auther Santosh-Kus
 * Date: 12-12-2021
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByAadhaar(String uId);
}
