package com.bs.spring.beantest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.beantest.NoBean;
import com.bs.spring.beantest.service.BeanService;
import com.bs.spring.beantest.test.Test;
import com.bs.spring.beantest.test.TestTwo;

//@AllArgsConstructor
//@RequiredArgsConstructor//final로 선언된 필드에 대한 매개변수 있는 생성자 생성
@Controller
public class BeanController {
	
//	@Autowired
	private final BeanService service;
	private final NoBean bean;
	
	@Autowired
	private Test test;
	
	@Autowired
	private TestTwo test2;
	
	public BeanController(@Autowired BeanService service,
			@Autowired(required = false) NoBean bean) {
		super();
		this.service = service;
		this.bean = bean;
	}
	private String name;
	//생성자를 이용해서 의존성 주입하기
//	public BeanController(BeanService service) {
//		this.service=service;
//	}
	
	@RequestMapping("/test")
	public String beanTest() {
		System.out.println("beanController실행");
		service.testService();
		return "index";
	}
	
}










