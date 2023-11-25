package com.main.lacj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class SemiProjectLacjApplication {

	public static void main(String[] args) {
		SpringApplication.run(SemiProjectLacjApplication.class, args);
	}
	@RequestMapping("/") // 포트번호로 들어올시 인덱스 페이지로 이동
	public String root() {
		
		return "index";
	}
	
}
