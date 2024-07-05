package com.bs.spring.demo.model.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.demo.model.dto.Demo;

public interface DemoService {
	int insertDemo(Demo d);
	int updateDemo(Demo d);
	List<Demo> selectDemoList(Map<String,Integer> param);
	Demo selectDemoByNo(int no);
}
