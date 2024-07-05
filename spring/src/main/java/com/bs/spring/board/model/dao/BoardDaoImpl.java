package com.bs.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
@Repository
public class BoardDaoImpl implements BoardDao {

	@Override
	public List<Board> selectList(SqlSession session, Map<String, Integer> page) {
		// TODO Auto-generated method stub
		return session.selectList("board.boardList",null,
				new RowBounds((page.get("cPage")-1)*page.get("numPerpage"),
						page.get("numPerpage")));
	}

	@Override
	public Board selectBoardByNo(SqlSession session, int boardNo) {
		return session.selectOne("board.boardByNo",boardNo);
	}

	@Override
	public int insertBoard(SqlSession session, Board b) {
		// TODO Auto-generated method stub
		return session.insert("board.boardInsert",b);
	}

	@Override
	public int selectBoardCount(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectOne("board.boardCount");
	}

	@Override
	public int updateBoardReadCount(SqlSession session, int boardNo) {
		// TODO Auto-generated method stub
		return session.update("board.boardUpdate",boardNo);
	}

	@Override
	public int insertAttachment(SqlSession session, Attachment attach) {
		// TODO Auto-generated method stub
		return session.insert("board.insertAttach",attach);
	}
	
	
	
	
	
	

}
