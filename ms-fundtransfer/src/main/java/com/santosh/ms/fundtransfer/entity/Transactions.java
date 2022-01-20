package com.santosh.ms.fundtransfer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Auther Santosh-Kus
 * Date: 02-01-2022
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private long accountNumber;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "TRANSACTION_DATE", nullable = false)
    private LocalDateTime transactionDate;

    private String transactionDetails;

    @Column(name = "REFERENCE_NUMBER", nullable = false)
    private String referenceNumber;

    @Column(name = "DEBIT", nullable = false)
    private double debit;

    @Column(name = "CREDIT", nullable = false)
    private double credit;

    public Transactions(long accountNumber ,String referenceNumber,double debit,double credit,String transactionDetails) {
        this.accountNumber=accountNumber;
        this.transactionDate = LocalDateTime.now();
        this.referenceNumber = referenceNumber;
        this.transactionDetails=transactionDetails;
        this.debit= debit;
        this.credit=credit;

    }

    public Transactions(Transactions transactions) {
        this.accountNumber = transactions.getAccountNumber();
        this.transactionDate = transactions.getTransactionDate();
        this.referenceNumber = transactions.getReferenceNumber();
        this.transactionDetails=transactions.getTransactionDetails();
        this.debit= transactions.getDebit();
        this.credit=transactions.getCredit();
    }
}
