package com.santosh.ms.account.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.santosh.ms.account.constant.AppConstants;
import com.santosh.ms.account.dto.AccountDto;
import com.santosh.ms.account.entity.Account;
import com.santosh.ms.account.exception.MsApplicationException;
import com.santosh.ms.account.repository.AccountRepository;


/**
 * @author santosh.kushwah
 * @since: 12-28-2021
 */

@Service
public class AccountServiceImpl implements AccountService {
    Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    AccountRepository accountRepository;

    @Override
    public double checkBalance(long accountNo) {
        return accountRepository.findByAccountNumberAndStatusTrue(accountNo).getBalance();
    }

    @Override
    public double depositMoney(long accountNo, double money) {
        Account account = accountRepository.findByAccountNumberAndStatusTrue(accountNo);
        if(null ==account){
            throw new MsApplicationException(AppConstants.RECORD_NOT_EXITS,AppConstants.ACCOUNT_NOT_EXITS_MSG);
        }
        account.setBalance(account.getBalance()+money);
        return accountRepository.save(account).getBalance();
    }

    @Override
    public Account findByAccountNumber(long accountNo) {
        return accountRepository.findByAccountNumberAndStatusTrue(accountNo);
    }

    @Override
    public Account update(AccountDto accountRequest) {
        Account account = accountRepository.findByAccountNumberAndStatusTrue(accountRequest.getAccountNumber());
        if(account !=null){
            account.setBalance(accountRequest.getBalance());
            return accountRepository.save(account);
        }
        return null;
    }

}
