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
		registry.addInterceptor(new LACJinterceptor())
		.excludePathPatterns("/css/**", "/fonts/**", "/js/**", "/img/**","/fonts/**")
		.addPathPatterns("/boardinsertform")
		.addPathPatterns("/boardUpdate")
		.addPathPatterns("/boardDelete")
		.addPathPatterns("/boardUpdateForm")
		.addPathPatterns("/memberdelete")
		.addPathPatterns("/mypage")
		.addPathPatterns("/mypagefail")
		.addPathPatterns("/updatemember")
		.addPathPatterns("/mypagedetail")
		.addPathPatterns("/uplikes")
		.addPathPatterns("/downlikes")
		.addPathPatterns("/addcomment")
		.addPathPatterns("/logout")
		.addPathPatterns("/boardinsert")
		.addPathPatterns("/mainlist")
		.addPathPatterns("/boarddetail");
	}
}
