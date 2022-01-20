package com.santosh.ms.fundtransfer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.santosh.ms.fundtransfer.dto.TransactionDateRangeDto;
import com.santosh.ms.fundtransfer.response.StatementResponse;
import com.santosh.ms.fundtransfer.service.TransactionService;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.ws.rs.PathParam;
/**
 * @author santosh.kushwah
 * @since 31-28-2021
 */

@RestController
public class StatementController {
    Logger logger = LoggerFactory.getLogger(StatementController.class);

    @Autowired
    TransactionService statementService;

    @GetMapping("/statement/{accountNo},{month},{year}")
    public StatementResponse statement(@Valid @PathParam("accountNo") long accountNo , @PathParam("month") String month, @Min(value = 4,message = "year should be 4 digits only.") @PathParam("year") int year){
        logger.info("Inside account-controller - statement()");
        return statementService.statement(accountNo,month,year);
    }
    @PostMapping("/statement")
    public StatementResponse statementBetweenDate(@Valid @RequestBody TransactionDateRangeDto request){
        logger.info("Inside account-controller - statementBetweenDate()");
        return statementService.statementBetweenDate(request);
    }
}
