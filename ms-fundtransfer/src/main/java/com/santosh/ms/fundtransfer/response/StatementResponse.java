package com.santosh.ms.fundtransfer.response;

import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

import com.santosh.ms.fundtransfer.dto.AccountDto;
import com.santosh.ms.fundtransfer.entity.Transactions;

/**
 * @Auther Santosh-Kus
 * Date: 12-12-2021
 */

@Data
public class StatementResponse {

    private String accountHolder;
    private double accountNumber;
    private double balance;
    private double openingBalance;
    private String type;
    List<Transactions> transactions;

    public StatementResponse(AccountDto account, List<Transactions> transactions) {
        this.accountHolder = account.getAccountHolder();
        this.accountNumber = account.getAccountNumber();
        this.balance = account.getBalance();
        this.openingBalance = account.getOpeningBalance();
        this.type = account.getType();
        this.transactions=transactions.stream().map(o->new Transactions(o)).collect(Collectors.toList());
    }
}
