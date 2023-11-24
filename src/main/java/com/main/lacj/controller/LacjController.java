package com.main.lacj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.lacj.model.biz.Biz;
import com.main.lacj.model.dto.MemberDto;

@Controller
public class LacjController {
//     index.html > login page

	@Autowired
	private Biz biz;

	@RequestMapping("/mainlist")
	public String mainlist(Model model) {
		model.addAttribute("list", biz.selectList());
		return "mainlist";
	}

	@PostMapping("/insertRes")
	public String intsertRes(MemberDto dto) {

		if (biz.insertRegi(dto) > 0) {
			return "redirect:mainlist";
		} else {
			return "redirect:regist";
		}

	}

	@GetMapping("/regist")
	public String regist() {

		return "regist";
	}

	@GetMapping("/mypage")
	public String mypage() {

		return "mypage";
	}

	@GetMapping("/login")
	public String login() {

		return "login";
	}

}