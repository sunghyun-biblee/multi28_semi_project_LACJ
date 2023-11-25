package com.main.lacj.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class LACJinterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(LACJinterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {
		logger.info("[interceptor] : preHandle");

		Object user = request.getSession().getAttribute("user");
		
		if (user != null ) {

			return true;
		}else{
			response.sendRedirect("/none");
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		logger.info("[interceptor] : postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		logger.info("[interceptor] : afterCompletion");
	}

}