package com.bs.spring.beantest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bs.spring.beantest.dao.BeanDao;


@Service
public class BeanServiceImpl implements BeanService{
	
	//@Autowired
	private BeanDao dao;
	
	public BeanServiceImpl(
			@Qualifier("testDao") BeanDao dao) {
		super();
		this.dao = dao;
	}



	@Override
	public void testService() {
		System.out.println("beanSerivce실행");
		dao.findBean();
	}
}
