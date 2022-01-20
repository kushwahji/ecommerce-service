package com.santosh.ms.fundtransfer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.santosh.ms.fundtransfer.client.AccountFeignClient;
import com.santosh.ms.fundtransfer.constants.AppConstants;
import com.santosh.ms.fundtransfer.dto.AccountDto;
import com.santosh.ms.fundtransfer.dto.TransactionDateRangeDto;
import com.santosh.ms.fundtransfer.entity.Transactions;
import com.santosh.ms.fundtransfer.exception.MsApplicationException;
import com.santosh.ms.fundtransfer.repository.TransactionRepository;
import com.santosh.ms.fundtransfer.response.StatementResponse;
import com.santosh.ms.fundtransfer.utils.DateConverter;

import java.util.List;


/**
 * @author santosh.kushwah
 * @since 31-12-2021
 */


@Service
public class TransactionServiceImpl implements TransactionService {

    Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountFeignClient accountRepository;

    @Override
    public StatementResponse statement(long accountNo, String month, int year) {
        logger.info("Inside Bank-Service-Impl - statement()");
        List<Transactions> transactions = transactionRepository.findByMonthAndYear(accountNo, DateConverter.getMonthNumber(month),year);
        AccountDto account = accountRepository.findByAccountNumberAndStatusTrue(accountNo);
        if (account == null) {
            throw new MsApplicationException(AppConstants.RECORD_NOT_EXITS, AppConstants.RECORD_NOT_FOUND_FOR_RANGE.replace("PARAM1",month).replace("PARAM2",""+year));
        }
        return new StatementResponse(account,transactions );
    }

	@Override
	public StatementResponse statementBetweenDate(TransactionDateRangeDto request) {
		List<Transactions> transactions = transactionRepository.findByAccountNumberAndTransactionDateBetween(request.getAccountNumber(),request.getFromDate(),request.getToDate());
		AccountDto account = accountRepository.findByAccountNumberAndStatusTrue(request.getAccountNumber());
        if (account == null) {
        	throw new MsApplicationException(AppConstants.RECORD_NOT_EXITS, AppConstants.RECORD_NOT_FOUND_FOR_RANGE.replace("PARAM1",""+request.getFromDate()).replace("PARAM2",""+request.getToDate()));
        }
		return new StatementResponse(account,transactions);
	}
}
