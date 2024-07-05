package com.bs.spring.memo.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.memo.model.dto.Memo;
@Repository
public class MemoDaoImpl implements MemoDao {

	@Override
	public List<Memo> selectMemoAll(SqlSession session, Map<String, Integer> param) {
		RowBounds row=new RowBounds(
				(param.get("cPage")-1)*param.get("numPerpage"),
				param.get("numPerpage"));
		return session.selectList("memo.selectMemoAll",null,row);
	}

	@Override
	public int selectMemoCount(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectOne("memo.selectMemoCount");
	}

	@Override
	public int insertMemo(SqlSession session, Memo m) {
		// TODO Auto-generated method stub
		return session.insert("memo.insertMemo",m);
	}

}
