package com.bs.spring.demo.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.demo.model.dto.Demo;

public interface DemoDao {
	int insertDemo(SqlSessionTemplate session,Demo demo);
	int updateDemo(SqlSession session, Demo demo);
	List<Demo> selectDemo(SqlSession session,Map<String,Integer> param);
	Demo selectDemoByNo(SqlSession session,int no);
	
	
}
