package com.hcl.account.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.account.test.base.BaseContextLoader;
import com.santosh.ms.account.dto.AccountDto;
import com.santosh.ms.account.repository.AccountRepository;

public class AccountApplicationTests extends BaseContextLoader {

	@Mock
	AccountRepository accountRepository;
	

	
	void test() throws JsonProcessingException {
		ObjectMapper ob = new ObjectMapper();
		//String req = "{ \"fromAccountNumber\": 301220210737381, \"toAccountNumber\": 311220211217133, \"amount\": 50, \"remarks\": \"personal\" }";
		//Mockito.when(accountRepository.save(new AccountDto(fund))).thenReturn(ob.readValue(req, FundTransfer.class));
		//callPostEndpoint("/api/fund/transfer", ob.writeValueAsString(fund));

	}


}
