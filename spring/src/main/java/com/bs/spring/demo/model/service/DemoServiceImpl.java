package com.bs.spring.demo.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.bs.spring.demo.model.dao.DemoDao;
import com.bs.spring.demo.model.dto.Demo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DemoServiceImpl implements DemoService {
	
	//@Autowired
	private final DemoDao dao;
	private final SqlSessionTemplate session;
//	@Autowired
//	public DemoServiceImpl(DemoDao dao) {
//		// TODO Auto-generated constructor stub
//		this.dao=dao;
//	}
	
	@Override
	public int insertDemo(Demo d) {
		
		return dao.insertDemo(session, d);
	}

	@Override
	public int updateDemo(Demo d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Demo> selectDemoList(Map<String, Integer> param) {
		return dao.selectDemo(session, param);
	}

	@Override
	public Demo selectDemoByNo(int no) {
		// TODO Auto-generated method stub
		return null;
	}

}
