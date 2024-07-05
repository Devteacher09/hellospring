package com.bs.spring.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.dto.Board;
import com.bs.spring.common.exception.BadAuthenticationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

	private final BoardDao dao;
	private final SqlSession session;
	
	@Override
	public List<Board> selectList(Map<String, Integer> page) {
		return dao.selectList(session, page);
	}

	@Override
	public Board selectBoardByNo(int boardNo) {
		// TODO Auto-generated method stub
		return dao.selectBoardByNo(session, boardNo);
	}

	@Override
//	@Transactional
	public int insertBoard(Board b) {
		int result=0;
		try {
			result=dao.insertBoard(session, b);
			log.debug("생성된 게시글번호 : {}",b.getBoardNo());
			if(result>0&&b.getFiles().size()>0) {
				int boardNo=b.getBoardNo();
				b.getFiles().stream().forEach(attachment->{
					attachment.setBoardNo(boardNo);
					int result1=dao.insertAttachment(session,attachment);
					if(result1==0) {
						throw new BadAuthenticationException("첨부파일등록실패");
					}
				});
			}else {
				throw new BadAuthenticationException("게시글등록실패");
			}
		}catch(RuntimeException e) {
			throw new BadAuthenticationException("게시글등록실패");
		}
		return result;
	}

	@Override
	public int selectBoardCount() {
		// TODO Auto-generated method stub
		return dao.selectBoardCount(session);
	}

	@Override
	public int updateBoardReadCount(int boardNo) {
		// TODO Auto-generated method stub
		return dao.updateBoardReadCount(session, boardNo);
	}
	
	public Map<String,Object> selectBoardList(Map<String,Integer> page){
		Map<String,Object> resultMap=new HashMap();
		resultMap.put("boards",dao.selectList(session, page));
		resultMap.put("boardCount", dao.selectBoardCount(session));
		return resultMap;			
	}

}
