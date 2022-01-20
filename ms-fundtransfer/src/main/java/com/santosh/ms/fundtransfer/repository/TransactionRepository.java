package com.santosh.ms.fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.santosh.ms.fundtransfer.entity.Transactions;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author santosh.kushwah
 * @since: 31-28-2021
 */

@Repository
public interface TransactionRepository extends JpaRepository<Transactions,Long> {

    @Query(value = "FROM Transactions as t where t.accountNumber = ?1 and extract(month from t.transactionDate)=?2 and extract(year from t.transactionDate)=?3")
    List<Transactions> findByMonthAndYear(long accountNo, int month,int year);

	List<Transactions> findByAccountNumberAndTransactionDateBetween(long accountNo, LocalDateTime fromDate, LocalDateTime toDate);
}
