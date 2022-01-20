package com.santosh.ms.account.service;

import com.santosh.ms.account.dto.AccountDto;
import com.santosh.ms.account.entity.Account;

public interface AccountService {

    double checkBalance(long accountNo);

    double depositMoney(long accountNo, double money);

    Account findByAccountNumber(long accountNo);

    Account update(AccountDto account);
}
