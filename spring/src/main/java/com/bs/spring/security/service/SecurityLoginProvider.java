package com.bs.spring.security.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bs.spring.member.model.dao.MemberDao;
import com.bs.spring.member.model.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Setter;

//시큐리티가 이용하는 인증처리 클래스
//UserDetailsService인터페이스를 구현해야함.
// -> loadUserByUsername()메소드를 재정의
// public UserDedails loadUserByUsername(String username) 
//					throws UsernameNotFoundException
// 매개변수 username -> 클라이언트가 입력한 아이디값
// 반환값 UserDetails -> UserDetails인터페이스를 구현한 객체
//   Member DTO객체가 구현
@AllArgsConstructor
public class SecurityLoginProvider implements UserDetailsService{
	
	private final MemberDao dao;
	private final SqlSession session;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member loginMember=dao.selectMemeberById(session,username);
		
		return loginMember;
	}
}
