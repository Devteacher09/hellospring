package com.bs.spring.beantest;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class BeanTest {
	private String title="어노테이션빈";
}
