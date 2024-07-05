package com.bs.spring.common.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class AnnoLoggerAspect {

//	@Pointcut(타겟메소드에 대한 표현식)
	//메소드선언
	@Pointcut("execution(* com.bs.spring.memo..*(..))")
	public void test() {}
	
//	@Pointcut("execution(* com.bs.spring..insert*(..))")
//	public void test2() {}
	
	@Pointcut("within(com.bs.spring.demo.controller.DemoController)")
	public void test3() {}
	
	
	@Before("test()")
	public void loggerTest(JoinPoint jp) {
		log.debug("---- anno aspect before실행 ----");
	}
	
	@After("test()")
	public void loggerTestAfter(JoinPoint jp) {
		Signature sig=jp.getSignature();
		log.debug("끝냄 : "+sig.getDeclaringTypeName()+"."+sig.getName());
		Object[] params=jp.getArgs();
		if(params!=null) {
			for(Object o : params) {
				log.debug("{}",o);
			}
		}
	}
	
	@Before("execution(* com.bs.spring..insert*(..))")
	public void inertLogger(JoinPoint jp) {
		log.debug("-------- insert메소드 실행 로그 ------");
		Signature sig=jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+"."+sig.getName()+"를 실행");
		Object[] param=jp.getArgs();
		if(param!=null) {
			log.debug("전달된파라미터");
			for(Object o : param) {
				log.debug("{}",o);
			}
		}
	}
	
	@Before("test3()")
	public void withinTest(JoinPoint jp) {
		log.debug("----- within 실행 ----");
	}
	//Around적용하기
	//before, after를 동시에 적용
	@Around("within(com.bs.spring..dao.*)")
	public Object daoTest(ProceedingJoinPoint jp) throws Throwable{
		//전, 후 로직을 한번에 설정할 수 있음
		// 지역변수를 전, 후에 공유할 수 있음
		
		//전에 대한 로직, 후에 대한 로직은 ProceedingJoinPoint클래스에서 제공하는
		//proceed()메소드를 호출한 라인을 기준으로 나눔
		//proceed()메소드 호출전 라인 : before로직
		//proceed()메소드 호출후 라인 : after로직
		//procced()메소드를 호출하면 object를 반환해줌.
		log.debug("===== around before log =====");
		StopWatch watch=new StopWatch();
		watch.start();
		Object obj=jp.proceed();
		
		log.debug("==== around after log ====");
		watch.stop();
		log.debug("실행시간 : "+watch.getTotalTimeMillis()+"ms");
		return obj;
		
		
	}
	
	
	@AfterThrowing(value="within(com.bs.spring..controller.*)",
			throwing = "e")
	public void afterThrowingLogger(JoinPoint jp,Throwable e) {
		log.debug("==== exception발생 비상비상! =====");
		Signature sig=jp.getSignature();
		log.error("실행 메소드 : "+sig.getDeclaringTypeName()
				+"."+sig.getName());
		Object[] param=jp.getArgs();
		if(param!=null) {
			log.error("파라미터");
			Arrays.stream(param).forEach(p->{
				log.error("{}",p);				
			});
		}
		log.error(e.getMessage());
		StackTraceElement[] stackTrace=e.getStackTrace();
		for(StackTraceElement s:stackTrace) {
			log.debug("{}",s);
		}
		
	}
	
	//인터페이스 구현체들한테 적용하기
	@Before("within(com.bs.spring..*Dao+)")
	public void interTest() {
		log.debug("---- dao 인터페이스구현체 적용 ---");
	}
	//생성한 어노테이션이 설정한 메소드에 적용하기
	@Before("@annotation(com.bs.spring.common.aop.MyAnnotation)")
	public void annotest() {
		log.debug("---- 어노테이션적용 메소드 ----");
	}
	
}






