package com.bs.spring.beantest.dao;

import org.springframework.stereotype.Repository;

@Repository("testDao")
public class BeanDaoImpl implements BeanDao{
	
	//@Override
	public void findBean() {
		System.out.println("beanDao실행");
	}
}
