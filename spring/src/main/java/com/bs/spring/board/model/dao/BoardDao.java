package com.bs.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;

public interface BoardDao {
	List<Board> selectList(SqlSession session,Map<String,Integer> page);
	Board selectBoardByNo(SqlSession session,int boardNo);
	int insertBoard(SqlSession session,Board b);
	int selectBoardCount(SqlSession session);
	int updateBoardReadCount(SqlSession session, int boardNo);
	int insertAttachment(SqlSession session,Attachment attach);
}
