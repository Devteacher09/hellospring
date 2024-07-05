package com.bs.spring.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.bs.spring.member.model.dto.Member;

public class LoginCheckInterceptor 
	implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		Member loginMember=(Member)session.getAttribute("loginMember");
		if(loginMember==null) {
			//throw new IllegalAccessException();
			request.setAttribute("msg", "로그인 후 이용할 수 있는 서비스입니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			.forward(request, response);
			return false;
		}
		return true;
	
	}

	
	
}
