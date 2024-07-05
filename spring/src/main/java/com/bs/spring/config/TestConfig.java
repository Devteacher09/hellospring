package com.bs.spring.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import com.bs.spring.beantest.BeanTest;
import com.bs.spring.beantest.Food;
import com.bs.spring.beantest.Person;

@Configuration//하나의 설정파일 -> springbean configuration.xml과 동일
@ComponentScan(basePackages = "com.bs.spring",
	includeFilters = {@ComponentScan.Filter(
			type=FilterType.REGEX,
			pattern = {"com.bs.spring.beantest.test.*"})}
		)
public class TestConfig {

	//bean등록하기
	//@Bean어노테이션이 선언된 메소드를 선언
	@Bean(name="f")
	public Food food() {
		return Food.builder().build();
	}
	
	@Bean(name="food") 
	public Food food2() {
		return Food.builder().name("피자").price(2500).build();
	}
	
	@Bean(name="gom")
	@Order(2)
	public Food food3(List<Person> persons) {
		return Food.builder().name("곰탕")
				.likePerson(persons).price(9000).build();
	}
	@Bean(name="gom2")
	@Order(1)
	public Food food4(@Qualifier("p") Person per) {
		return Food.builder().name("곰탕")
				.likePerson(List.of(per)).price(9000).build();
	}
	
	@Bean
	@Primary
	public BeanTest listBean() {
		List<Food> foods=List.of(food(),food2());
		return new BeanTest();
	}
	
//	@Bean
//	public Gson gson() {
//		Gson gson=new Gson();
//		return gson;
//	}
//	@Bean
//	public BasicDataSource datasource() {
//		BasicDataSource srouce=new BasicDataSource();
		
//	}
	
}
