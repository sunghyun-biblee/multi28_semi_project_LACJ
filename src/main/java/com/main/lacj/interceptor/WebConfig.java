package com.main.lacj.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	// Webinterceptor 추가
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// interceptor 추가
		registry.addInterceptor(new LACJinterceptor()).excludePathPatterns("/css/**", "/fonts/**", "/js/**", "/img/**","/fonts/**","/index","/");
				
	}
}
