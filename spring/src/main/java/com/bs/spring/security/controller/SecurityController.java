package com.bs.spring.security.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SecurityController {
	
	@RequestMapping("/loginsuccess")
	public String loginsuccess() {
		log.debug("로그인성공!");
		//로그인한 정보를 가져오기
		Object o=SecurityContextHolder.getContext()
			.getAuthentication().getPrincipal();
		log.debug("{}",o);
		
		return "redirect:/";
	}
	@RequestMapping("/loginfail")
	public String loginFail(Model m) {
		m.addAttribute("msg","로그인 실패!");
		m.addAttribute("loc","/loginpage");
		
		return "common/msg";
	}
	
}
