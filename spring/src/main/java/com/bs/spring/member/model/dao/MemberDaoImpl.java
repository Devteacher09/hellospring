package com.bs.spring.member.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.member.model.dto.Member;
//@Repository("dao")
public class MemberDaoImpl implements MemberDao {

	@Override
	public Member selectMemeberById(SqlSession session, String userId) {
		return session.selectOne("member.selectMemberById",userId);
	}

	@Override
	public int insertMember(SqlSession session, Member m) {
		return session.insert("member.insertMember",m);
	}

	
	
	
}
