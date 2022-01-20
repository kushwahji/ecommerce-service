package com.santosh.ms.fundtransfer.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.santosh.ms.fundtransfer.dto.FundTransferDto;
import com.santosh.ms.fundtransfer.response.FundTransferResponse;
import com.santosh.ms.fundtransfer.service.FundTransferService;

import javax.validation.Valid;

/**
 * @Auther Santosh-Kus
 * Date: 31-28-2021
 */

@RestController
public class FundTransferController {
    Logger logger = LoggerFactory.getLogger(FundTransferController.class);

    @Autowired
    FundTransferService fundService;
    
    @PostMapping("/transfer")
    public FundTransferResponse transfer(@Valid @RequestBody FundTransferDto transfer){
        logger.info("Inside fund-controller - transfer()");
        return fundService.transfer(transfer);
    }
}
