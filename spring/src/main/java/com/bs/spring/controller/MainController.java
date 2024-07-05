package com.bs.spring.controller;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import com.bs.spring.common.aop.MyAnnotation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	private final Logger logger=LoggerFactory.getLogger(MainController.class);
	private final WebApplicationContext context;
	@MyAnnotation
	@RequestMapping("/")
	public String index(HttpSession session,
			HttpServletResponse response, Model model, Locale locale) {
		
		//log4j가 제공하는 Logger를 이용해서 로그메세지 출력하기
		// debug > info > warn > error -> level
		//debug()
		//info()
		//warn()
		//error()
		
		logger.debug("debug로그 출력");
		logger.info("info로그 출력");
		logger.warn("warn로그 출력");
		logger.error("error로그 출력");
		
		session.setAttribute("sessionId", "GDJ79");
		Cookie c=new Cookie("cookieData","cookiecookie");
		c.setMaxAge(60*60*24);
		response.addCookie(c);
		
		//국제화처리 문구 해결하기
//		String message=context.getMessage("greeting",null,Locale.US);
//		String message=context.getMessage("greeting",null,Locale.JAPAN);
		String message=context.getMessage("greeting",null,locale);
		model.addAttribute("greeting",message);		
		model.addAttribute("msg",context.getMessage("test.msg",new Object[] {
				"たまぐち","疲れて"} ,Locale.JAPAN));
		
		return "index";
	}
}
