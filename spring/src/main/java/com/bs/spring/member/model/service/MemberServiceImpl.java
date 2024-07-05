package com.bs.spring.member.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.bs.spring.member.model.dao.MemberDao;
import com.bs.spring.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	
	private final MemberDao dao;
	private final SqlSession session;
	
	@Override
	public Member selectMemberById(String userId) {
		return dao.selectMemeberById(session, userId);
	}

	@Override
	public int insertMember(Member m) {
		//DML -> 트렌젝션처리 autocommit해줌
		return dao.insertMember(session,m);
	}
	
	

}
