package com.main.lacj.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.lacj.model.biz.Biz;
import com.main.lacj.model.dto.BoardDto;
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

	@PostMapping("/insertboard")
	public String insertboard(BoardDto dto, HttpSession session) {

//		int mno=session.getAttribute("user");
		return "mainlist";
	}

	@GetMapping("/mypage")
	public String mypage() {

		return "mypage";
	}

	@RequestMapping("/login")
	public String login(MemberDto dto, HttpSession session) {

		MemberDto login = biz.selectLogin(dto);

		if (login != null) {
			session.setAttribute("user", login);
			session.setMaxInactiveInterval(60 * 10);
			return "redirect:mainlist";
		} else {

			// 로그인 실패 alert창 처리하기
			return "login";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}

	@RequestMapping("/guestLogin")
	public String guestLogin(HttpSession session) {
		MemberDto guest = new MemberDto(0, null, null, "GUEST", null, "G");

		session.setAttribute("user", guest);
		session.setMaxInactiveInterval(60 * 10);
		return "redirect:mainlist";
	}

}