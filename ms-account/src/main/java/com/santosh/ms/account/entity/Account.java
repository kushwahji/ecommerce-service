package com.santosh.ms.account.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.santosh.ms.account.enums.AccountStatus;
import com.santosh.ms.account.enums.AccountType;
import com.santosh.ms.account.helper.Helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author santosh.kushwah
 * @since: 12-12-2021
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID", unique = true, nullable = false)
    private Long accountId;

    @Column(name = "ACCOUNT_TYPE", unique = false, nullable = false)
    private String type;

    @Column(name = "ACCOUNT_STATUS", unique = false, nullable = false)
    private boolean status;

    @Column(name = "ACCOUNT_HOLDER", unique = false, nullable = false, length = 100)
    private String accountHolder;

    @Column(name = "ACCOUNT_NUMBER", unique = true, nullable = false)
    private long accountNumber;

    @Column(name = "AVAILABLE_BALANCE", nullable = false )
    private double balance;

    @Column(name = "OPENING_BALANCE", nullable = false)
    private double openingBalance;


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    public Account(String accountHolder) {
        this.accountHolder = accountHolder;
        this.type= AccountType.SAVINGS_ACCOUNT.name();
        this.status=AccountStatus.ACTIVE.isValue();
        this.accountNumber = Helper.generateAccountNumber();
        this.balance= 0.0;
        this.openingBalance = 10000;
    }
   
    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }
}
