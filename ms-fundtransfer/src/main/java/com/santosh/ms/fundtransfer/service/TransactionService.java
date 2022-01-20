package com.santosh.ms.fundtransfer.service;

import com.santosh.ms.fundtransfer.dto.TransactionDateRangeDto;
import com.santosh.ms.fundtransfer.response.StatementResponse;

/**
 * @Auther Santosh-Kus
 * Date: 31-28-2021
 */

public interface TransactionService {
    StatementResponse statement(long accountNo, String month, int year);

	StatementResponse statementBetweenDate(TransactionDateRangeDto request);
}
