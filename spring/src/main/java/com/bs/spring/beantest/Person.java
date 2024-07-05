package com.bs.spring.beantest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {
	private String name;
	private int age;
	private Animal a;
	
	public void init() {
		System.out.println("생성 후 실행");
		System.out.println(this.name+this.age+this.a);
	}
	public void destroyTest() {
		System.out.println("소멸!!!");
		System.out.println(this.name+this.age+this.a);
	}
	
}
