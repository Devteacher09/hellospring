package com.bs.spring.demo.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.demo.model.dto.Demo;

@Repository
public class DemoDaoImpl implements DemoDao {
	
	@Override
	public int insertDemo(SqlSessionTemplate session, Demo demo) {
		return session.insert("demo.insertDemo",demo);
	}

	@Override
	public int updateDemo(SqlSession session, Demo demo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Demo> selectDemo(SqlSession session, Map<String, Integer> param) {
		int cPage=param.get("cPage");
		int numPerpage=param.get("numPerpage");
		RowBounds row=new RowBounds(
				(cPage-1)*numPerpage,
				numPerpage);
		return session.selectList("demo.selectDemoAll",null,row);
	}

	@Override
	public Demo selectDemoByNo(SqlSession session, int no) {
		// TODO Auto-generated method stub
		return null;
	}

}
