/**
 * 
 */
/*
 * package com.santosh.ms.order.exception;
 * 
 * import org.springframework.cloud.openfeign.support.FeignUtils; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.stereotype.Component;
 * 
 * import feign.Response;
 * 
 *//**
	 * @author santosh.kushwah
	 * @since 19-01-2022
	 *//*
		 * @Component public class FundServiceClientExceptionHandler implements
		 * FeignHttpExceptionHandler {
		 * 
		 * @Override public Exception handle(Response response) { HttpStatus httpStatus
		 * = HttpStatus.resolve(response.status()); String body =
		 * FeignUtils.readBody(response.body()); if
		 * (HttpStatus.NOT_FOUND.equals(httpStatus)) { return new
		 * MsApplicationException("",body); } return new RuntimeException(body); } }
		 */