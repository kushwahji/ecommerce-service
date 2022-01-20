/**
 * 
 */
package com.hcl.account.test.base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

	

/**
 * @author santosh.kushwah
 *
 */
@Component
@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = { "junit", "junit-properties" })
@SpringBootTest
public class BaseContextLoader {
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	public MvcResult callPostEndpoint(String endpoint, String input) {
		MvcResult result = null;
		try {
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(endpoint)
					.accept(MediaType.APPLICATION_JSON).content(input).contentType(MediaType.APPLICATION_JSON);
			result = mockMvc.perform(requestBuilder).andReturn();
			assertEquals(201, result.getResponse().getStatus());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}
	
	public MvcResult callGetEndpoint(String endpoint) {
		MvcResult result = null;
		try {
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(endpoint)
					.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
			result = mockMvc.perform(requestBuilder).andReturn();
			assertEquals(202, result.getResponse().getStatus());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}
}
