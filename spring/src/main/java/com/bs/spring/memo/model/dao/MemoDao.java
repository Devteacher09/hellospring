package com.bs.spring.memo.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.memo.model.dto.Memo;

public interface MemoDao {
	List<Memo> selectMemoAll(SqlSession session,Map<String,Integer> param);
	int selectMemoCount(SqlSession session);
	int insertMemo(SqlSession session, Memo m);
}
