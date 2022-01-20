package com.santosh.ms.fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.santosh.ms.fundtransfer.entity.FundTransfer;

/**
 * @Auther Santosh-Kus
 * Date: 31-12-2021
 */

@Repository
public interface TransferRepository extends JpaRepository<FundTransfer,Long> {
}
