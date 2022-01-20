package com.hcl.fundtransfer;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.fundtransfer.base.BaseContextLoader;
import com.santosh.ms.fundtransfer.dto.FundTransferDto;
import com.santosh.ms.fundtransfer.entity.FundTransfer;
import com.santosh.ms.fundtransfer.entity.Transactions;
import com.santosh.ms.fundtransfer.repository.TransactionRepository;
import com.santosh.ms.fundtransfer.repository.TransferRepository;

public class MsFundTransferApplicationTests extends BaseContextLoader {

	@Mock
	TransferRepository transferRepository;
	
	@Mock
	TransactionRepository transactionRepository;

	
	void test() throws JsonProcessingException {
		ObjectMapper ob = new ObjectMapper();
		String req = "{ \"fromAccountNumber\": 301220210737381, \"toAccountNumber\": 311220211217133, \"amount\": 50, \"remarks\": \"personal\" }";
		FundTransferDto fund = ob.readValue(req, FundTransferDto.class);
		Mockito.when(transferRepository.save(new FundTransfer(fund))).thenReturn(ob.readValue(req, FundTransfer.class));
		callPostEndpoint("/api/fund/transfer", ob.writeValueAsString(fund));

	}

	
	void test_possitive_for_statement() throws JsonProcessingException {
		String url = "/api/statement/{accountNo},{month},{year}?accountNo=301220210737381&month=january&year=2022";
		String res = "{ \"transactions\": [ { \"accountNumber\": 301220210737381, \"transactionDate\": \"2022-01-03 11:38:56\", \"transactionDetails\": \"TO TRANSFER 301220210737381 TO 301220210734202 FOR PERSONAL\", \"referenceNumber\": \"561e3ab2-405f-4102-a224-275bd5a69fb8\", \"debit\": 0, \"credit\": 100 } ] }";
		ObjectMapper mapper = new ObjectMapper();
		Transactions st = mapper.readValue(res, Transactions.class);
		List<Transactions> list = new ArrayList<>();
		list.add(st);
		Mockito.when(transactionRepository.findByMonthAndYear(301220210737381L, 12, 2021)).thenReturn(list);
		callGetEndpoint(url);

	}
}
