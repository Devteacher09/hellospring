package com.bs.spring.common.aop;

import java.lang.reflect.Field;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;

//공통관심기능을 관리하는 클래스
@Slf4j
public class LoggerAspect {
	
	//공통의 기능을 정의 -> 메소드
	//메소드 선언방식이 정해져 있음
	//void 메소드명(JoinPoint)
	//Around -> Object 메소드명(ProceedingJoinPoint)
	
	public void loggerBefore(JoinPoint jp) {
		log.debug("---- aop before메소드 실행 ----");
//		log.debug("실행실행");
		//JoinPoint객체는 target메소드의 각종 정보를 저장하고 있는 객체
		//target메소드의 클래스, 메소드정보 가져오기 -> Signature객체를 이용
		Signature sig=jp.getSignature();
		log.debug("클래스명 "+sig.getDeclaringTypeName());
		log.debug("메소드명 "+sig.getName());
		Class targetClass=sig.getDeclaringType();
		for(Field f : targetClass.getDeclaredFields()) {
			log.debug(f.getName());
		}
		log.debug("----------------------------");
	}
	
	public void loggerAfter(JoinPoint jp) {
		log.debug("---------- aop after 매소드 실행 -------");
		log.debug("------------------------------------");
	}
	
}
