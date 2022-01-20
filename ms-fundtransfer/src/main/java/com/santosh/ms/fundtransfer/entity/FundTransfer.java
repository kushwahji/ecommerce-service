package com.santosh.ms.fundtransfer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.santosh.ms.fundtransfer.dto.FundTransferDto;

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
@Table(name = "transfer")
public class FundTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    @JsonIgnore
    private Long id;

    @Column(name = "FROM_ACCOUNT_NUMBER", nullable = false)
    private long fromAccountNumber;

    @Column(name = "TO_ACCOUNT_NUMBER", nullable = false)
    private long toAccountNumber;

    @Column(name = "AMOUNT", nullable = false)
    private double amount;

    @Column(name = "REMARKS",nullable = false)
    private String remarks;

    @JsonIgnore
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "CREATED_ON")
    private LocalDateTime createdOn;

    @JsonIgnore
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "UPDATED_ON")
    private LocalDateTime updatedOn;


    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Transactions transactions;

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }

	public FundTransfer(FundTransferDto transferDto) {
		this.fromAccountNumber = transferDto.getFromAccountNumber();
		this.toAccountNumber = transferDto.getToAccountNumber();
		this.amount = transferDto.getAmount();
		this.remarks = transferDto.getRemarks();
	}
}
