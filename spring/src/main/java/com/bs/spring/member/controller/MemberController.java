package com.bs.spring.member.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@SessionAttributes({"loginMember"})
@RequestMapping("/member")
public class MemberController {
		
	private final MemberService service;
	private final BCryptPasswordEncoder pwencoder;
//	private Logger logger=Logger
	@PostMapping("/login.do")
	public String login(String userId, String pw,Model model) {
//			HttpSession session,Model model) {
		Member m=service.selectMemberById(userId);
//		System.out.println(m);
		log.debug("{}",m);
		String page="";
		
//		if(page.equals("")) throw new RuntimeException();
		
		//if(m!=null&&m.getPassword().equals(pw)) {
		if(m!=null&&pwencoder.matches(pw, m.getPassword())){
			//session.setAttribute("loginMember", m);
			model.addAttribute("loginMember",m);
			page="redirect:/";
		}else {
			model.addAttribute("msg","아이디나 패스워드가 일치하지 않습니다.:(");
			model.addAttribute("loc","/");
			page="common/msg";
		}
		
		return page;
	}
	
	@GetMapping("/logout.do")
	//public String logout(HttpSession session) {
	public String logout(SessionStatus session) {
		//session.invalidate();
		if(!session.isComplete()) session.setComplete();
		
		return "redirect:/";
	}
	
	@GetMapping("/enrollmember.do")
	public void enrollMember(
			@ModelAttribute("member") Member m) {}
	
	@PostMapping("/enrollend.do")
	public String insertMember(@Validated Member m, 
			BindingResult isResult, Model model) {
		if(isResult.hasErrors()) {
			//설정한 유효성검사를 통과하지 못한것.
			return "member/enrollmember";
		}
		//패스워드 암호화
		String encodePw=pwencoder.encode(m.getPassword());
//		System.out.println(encodePw);
		log.debug(encodePw);
		m.setPassword(encodePw);
		
		int result=service.insertMember(m);
		if(result>0) {
			return login(m.getUserId(),m.getPassword(),model);
		}else {
			model.addAttribute("msg","회원가입실패");
			model.addAttribute("loc","/member/enrollmember.do");
			return "common/msg";
		}
	}
}








