package com.santosh.ms.fundtransfer.service;


import com.santosh.ms.fundtransfer.dto.FundTransferDto;
import com.santosh.ms.fundtransfer.response.FundTransferResponse;

/**
 * @author santosh.kushwah
 * @since 31-28-2021
 */


public interface FundTransferService {
    FundTransferResponse transfer(FundTransferDto transfer);
}
