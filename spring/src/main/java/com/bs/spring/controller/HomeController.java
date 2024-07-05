package com.bs.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.beantest.Animal;
import com.bs.spring.beantest.BeanTest;
import com.bs.spring.beantest.Food;
import com.bs.spring.beantest.Person;

@Controller
public class HomeController {
	
//	@Autowired
//	private Animal obok;
//	@Autowired
//	private Animal dosoon;
	
	@Autowired
	@Qualifier("obok")
	private Animal a;
	
	@Autowired
	private Person p;
	
	@Autowired
	private List<Animal> animals;
	
	@Autowired
	private List<Person> persons;
	
	@Autowired
	private BeanTest bean;
	
	@Autowired
	private Food food;
	
	@Autowired
	private Food gom;
	
//	@Autowired
//	private Gson gson;
	
	@Autowired
	private List<Food> foods;
	
	
//	@RequestMapping("/gson")
//	public void data(HttpServletResponse response) throws Exception {
//		gson.toJson(gom,response.getWriter());
//	}
	
	@RequestMapping("/test1")
	public String home() {
		System.out.println("메인화면으로 연결");
//		System.out.println(obok);
//		System.out.println(dosoon);
		System.out.println(a);
		System.out.println(p);
		System.out.println(animals);
		System.out.println(persons);
		System.out.println(food);
		System.out.println(bean);
		System.out.println(gom);
		System.out.println(foods);
		return "index";
	}
}
