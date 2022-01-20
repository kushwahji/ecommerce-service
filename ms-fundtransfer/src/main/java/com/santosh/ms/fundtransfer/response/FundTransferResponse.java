package com.santosh.ms.fundtransfer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther Santosh-Kus
 * Date: 31-12-2021
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundTransferResponse {
    private String message;
    private String transactionId;
}
