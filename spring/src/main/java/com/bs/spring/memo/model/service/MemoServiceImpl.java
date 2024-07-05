package com.bs.spring.memo.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.bs.spring.memo.model.dao.MemoDao;
import com.bs.spring.memo.model.dto.Memo;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class MemoServiceImpl implements MemoService {
	private final MemoDao dao;
	private final SqlSession session;
	@Override
	public List<Memo> selectMemoList(Map<String, Integer> param) {
		return dao.selectMemoAll(session, param);
	}

	@Override
	public int selectMemoCount() {
		return dao.selectMemoCount(session);
	}

	@Override
	public int insertMemo(Memo m) {
		return dao.insertMemo(session, m);
	}

}
