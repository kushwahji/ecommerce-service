package com.santosh.ms.account.logs;

/**
 * 
 */


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author santosh.kushwah
 *
 */
@Component
public class LoggerIntercepter implements HandlerInterceptor {
	Logger logger = LoggerFactory.getLogger(LoggerIntercepter.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long startTime = Instant.now().toEpochMilli();
		logger.info("Request URL::" + request.getRequestURL().toString() + ":: Start Time=" + Instant.now());
		request.setAttribute("startTime", startTime);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("Executing After Handler method..." + getRequestURI(request));
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (Long) request.getAttribute("startTime");

		logger.info("Request URL::" + request.getRequestURL().toString() + ":: Time Taken="
				+ (Instant.now().toEpochMilli() - startTime));
		logger.info("After completing request...");

	}

	public static String getRequestURI(HttpServletRequest request) {
		return request.getRequestURI().replaceAll("[\n|\r|\t]", "");
	}
}
