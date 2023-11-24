package com.main.lacj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LacjController {
//  index.html > login page

	@PostMapping("/regist")
	public String regist() {

		return "regist";
	}

	@GetMapping("/mypage")
	public String mypage() {

		return "mypage";
	}

	@RequestMapping("/mainlist")
	public String mainlist() {

		return "mainlist";
	}
}
