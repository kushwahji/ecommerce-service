package com.santosh.ms.customer.logs;

import java.io.IOException;
import java.nio.charset.Charset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

/**
 * @author SANTOSH
 *
 */
@Component
public class HttpLoggingInterceptor implements ClientHttpRequestInterceptor {
	Logger log = LoggerFactory.getLogger(HttpLoggingInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		long startTime = System.nanoTime();
		logRequest(request, body, startTime);
		ClientHttpResponse response = execution.execute(request, body);
		logResponse(response, startTime);

		response.getHeaders().add("headerName", "VALUE");

		return response;
	}

	private void logRequest(HttpRequest request, byte[] body, long startTime) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("===========================request begin================================================");
			log.debug("Start Time  : {}", +startTime);
			log.debug("URI         : {}", request.getURI());
			log.debug("Method      : {}", request.getMethod());
			log.debug("Headers     : {}", request.getHeaders());
			log.debug("Request body: {}", new String(body, "UTF-8"));
			log.debug("Time taken  : {}" + (System.nanoTime() - startTime) / 1000000 + "ms");
			log.debug("==========================request end================================================");
		}
	}

	private void logResponse(ClientHttpResponse response, long startTime) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("============================response begin==========================================");
			log.debug("Start Time   : {}", +startTime);
			log.debug("Status code  : {}", response.getStatusCode());
			log.debug("Status text  : {}", response.getStatusText());
			log.debug("Headers      : {}", response.getHeaders());
			log.debug("Response body: {}", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
			log.debug("Time taken   : {}" + (System.nanoTime() - startTime) / 1000000 + "ms");
			log.debug("=======================response end=================================================");
		}
	}
}