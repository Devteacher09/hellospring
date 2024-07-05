package com.bs.spring.config;

import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.bs.spring.common.exception.BadAuthenticationException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvc
public class WebMVCConfig implements WebMvcConfigurer{
		
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/board/inputboard.do")
		.setViewName("board/inputBoard");
		registry.addViewController("/chatpage")
		.setViewName("chat/chatpage");
		registry.addViewController("/loginpage")
		.setViewName("member/login");
	}

	//예외처리할 bean을 등록해서 활용할 수 있음
	// HandlerExceptionResolver를 구현한 구현체
	@Bean
	public HandlerExceptionResolver handleException() {
		
		SimpleMappingExceptionResolver smer=
				new SimpleMappingExceptionResolver();
		Properties mappingException=new Properties();
		mappingException.setProperty(
				BadAuthenticationException.class.getName(),
				"common/error/authentication");
		
		smer.setExceptionMappings(mappingException);
		
		smer.setDefaultErrorView("common/error/error");
		
		return smer;
	}
	//국제화처리를 위한 bean
	//로케일에 따라 출려되는 문구를 변경
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource
			=new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
	
	@Bean
	public ObjectMapper getJackson() {
		return new ObjectMapper();
	}
	
}
