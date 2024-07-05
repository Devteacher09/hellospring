package com.bs.spring.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.member.model.dto.Member;

public interface MemberDao {
	Member selectMemeberById(SqlSession session,String userId);
	int insertMember(SqlSession session,Member m);
}
