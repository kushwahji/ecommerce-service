/**
 * 
 */
package com.santosh.ms.product.logs;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author santosh.kushwah
 *
 */
@ControllerAdvice
public class CustomResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {


	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		HttpLoggingHelper httpLoggingHelper = new HttpLoggingHelper();
		if (request instanceof ServletServerHttpRequest && response instanceof ServletServerHttpResponse) {
			try {
				httpLoggingHelper.logResponse(((ServletServerHttpRequest) request).getServletRequest(),
						((ServletServerHttpResponse) response).getServletResponse(), body);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return body;
	}

}
