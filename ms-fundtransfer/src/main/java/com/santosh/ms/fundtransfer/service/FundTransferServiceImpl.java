package com.santosh.ms.fundtransfer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.santosh.ms.fundtransfer.client.AccountFeignClient;
import com.santosh.ms.fundtransfer.constants.AppConstants;
import com.santosh.ms.fundtransfer.dto.AccountDto;
import com.santosh.ms.fundtransfer.dto.FundTransferDto;
import com.santosh.ms.fundtransfer.entity.FundTransfer;
import com.santosh.ms.fundtransfer.entity.Transactions;
import com.santosh.ms.fundtransfer.exception.MsApplicationException;
import com.santosh.ms.fundtransfer.helper.Helper;
import com.santosh.ms.fundtransfer.repository.TransferRepository;
import com.santosh.ms.fundtransfer.response.FundTransferResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

/**
 * @author santosh.kushwah
 * @since 31-12-2021
 */

@Transactional
@Service
public class FundTransferServiceImpl implements FundTransferService {

    private static final String BACKEND = "backendA";

	Logger logger = LoggerFactory.getLogger(FundTransferServiceImpl.class);

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    AccountFeignClient accountFeignClient;

    
    @CircuitBreaker(name = BACKEND, fallbackMethod = "fallback")
    @Retry(name = BACKEND, fallbackMethod = "fallback")
    @Transactional
    @Override
    public FundTransferResponse transfer(FundTransferDto transferDto) {
        logger.debug("Inside Bank-Service-Impl - transfer()");
        String referenceNumber = Helper.generateReferenceNumber();
        AccountDto fromAccount = getAccount(transferDto.getFromAccountNumber());
        if (fromAccount != null) {
            if (transferDto.getFromAccountNumber() == transferDto.getToAccountNumber()) {
                throw new MsApplicationException(AppConstants.INVALID_INPUT, AppConstants.ACCOUNT_NUMBER_SAME);
            }
            validateBalance(fromAccount, transferDto.getAmount());
            toAccountCheckAndUpdate(transferDto, referenceNumber);
            prepareTransferData(transferDto, transferDto.getFromAccountNumber(), referenceNumber, transferDto.getAmount(), 0, "BY TRANSFER " + transferDto.getFromAccountNumber() + " TO " + transferDto.getToAccountNumber() + " FOR " + transferDto.getRemarks().toUpperCase());
            fromAccount.setBalance(fromAccount.getBalance() - transferDto.getAmount());
            updateBalance(fromAccount);
        } else {
            throw new MsApplicationException(AppConstants.RECORD_NOT_EXITS, AppConstants.ACCOUNT_NOT_EXITS_MSG + transferDto.getFromAccountNumber());
        }
        return new FundTransferResponse(AppConstants.TRANSACTION_MSG, referenceNumber);
    }

    private void toAccountCheckAndUpdate(FundTransferDto transfer, String referenceNumber) {
        AccountDto toAccount = getAccount(transfer.getToAccountNumber());
        if (null == toAccount) {
            throw new MsApplicationException(AppConstants.RECORD_NOT_EXITS, AppConstants.ACCOUNT_NOT_EXITS + transfer.getFromAccountNumber());
        }
        toAccount.setBalance(toAccount.getBalance() + transfer.getAmount());
        updateBalance(toAccount);
        prepareTransferData(transfer, transfer.getToAccountNumber(), referenceNumber, 0, transfer.getAmount(), "TO TRANSFER " + transfer.getToAccountNumber() + " FROM " + transfer.getFromAccountNumber() + " FOR " + transfer.getRemarks().toUpperCase());
    }

    private void prepareTransferData(FundTransferDto transferDto, long accountNo, String referenceNumber, double debit, double credit, String transDetails) {
        FundTransfer fundTransfer = new FundTransfer(transferDto);
        fundTransfer.setTransactions(new Transactions(accountNo, referenceNumber, debit, credit, transDetails));
        transferRepository.save(fundTransfer);
    }

    private void updateBalance(AccountDto account) {
            accountFeignClient.save(account);
    }

    private void validateBalance(AccountDto account, double amount) {
        if (account.getBalance() < amount) {
            throw new MsApplicationException(AppConstants.INSUFFICIENT_FUND, AppConstants.INSUFFICIENT_FUND_MSG);
        }
    }

    private AccountDto getAccount(long accountNo) {
        return accountFeignClient.findByAccountNumberAndStatusTrue(accountNo);
    }
    
    public FundTransferResponse fallback(FundTransferDto transferDto,RuntimeException e) {
    	throw new MsApplicationException("Server Down..","account service down...");
    }
}
