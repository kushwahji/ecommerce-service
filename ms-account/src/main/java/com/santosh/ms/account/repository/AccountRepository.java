package com.santosh.ms.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santosh.ms.account.entity.Account;


/**
 * @author santosh.kushwah
 * @since: 12-12-2021
 */

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByAccountNumberAndStatusTrue(long fromAccountNumber);
}
