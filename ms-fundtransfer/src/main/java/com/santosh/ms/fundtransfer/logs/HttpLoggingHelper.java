/**
 * 
 */
package com.santosh.ms.fundtransfer.logs;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author santosh.kushwah
 *
 */
@Component
public class HttpLoggingHelper {
	Logger logger = LoggerFactory.getLogger(HttpLoggingHelper.class);

	private String prefix = "HCL";
	ObjectMapper om = new ObjectMapper();

	public void logRequest(HttpServletRequest request, Object body) throws JsonProcessingException {
		String requestLog = Arrays
				.asList("", "=======================Service Request Start=======================",
						"Remote Address	:" + request.getRemoteAddr(), "Method			:" + request.getMethod(),
						"Path			:" + request.getRequestURI(), "Headers		:" + getHeaderAsString(request),
						"Parameter		:" + getParameter(request), "Attributes		:" + getAttributes(request,prefix),
						"Request Body	:" + om.writeValueAsString(body),
						"======================Service Request End=========================")
				.stream().collect(Collectors.joining(System.lineSeparator()));
		logger.info(requestLog);
	}

	public void logResponse(HttpServletRequest request, HttpServletResponse response, Object body) throws JsonProcessingException {
		String requestLog = Arrays.asList("", "=======================Service Response Start=======================",
				"Request Attributes	:" + getAttributes(request, prefix), "Status Code		:" + response.getStatus(),
				"Path			:" + request.getRequestURI(), "Headers		:" + getHeaderAsString(response),
				"Request Body	:" + om.writeValueAsString(body),
				"======================Service Response End=========================").stream()
				.collect(Collectors.joining(System.lineSeparator()));
		logger.info(requestLog);
	}

	private String getHeaderAsString(HttpServletResponse response) {
		return response.getHeaderNames().stream().map(m -> response.getHeader(m)).collect(Collectors.toList())
				.toString();

	}

	private String getAttributes(HttpServletRequest request, String prefix) {
		return java.util.Collections.list(request.getAttributeNames()).stream().filter(f -> f.startsWith(prefix))
				.map(m -> request.getAttribute(m).toString()).collect(Collectors.toList()).toString();
	}

	private String getParameter(HttpServletRequest request) {
		return java.util.Collections.list(request.getParameterNames()).stream()
				.map(m -> request.getParameter(m).toString()).collect(Collectors.toList()).toString();
	}

	private String getHeaderAsString(HttpServletRequest request) {
		return java.util.Collections.list(request.getHeaderNames()).stream()
				.map(m -> request.getHeader(m).toString()).collect(Collectors.toList()).toString();
	}

}
