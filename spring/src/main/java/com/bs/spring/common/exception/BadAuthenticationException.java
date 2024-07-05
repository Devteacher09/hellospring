package com.bs.spring.common.exception;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;

@Getter
public class BadAuthenticationException
	extends RuntimeException implements Serializable{
	
	
	private static final long serialVersionUID = 5789655269828820041L;
	private LocalDate eventDate;
	
	public BadAuthenticationException(String msg) {
		super(msg);
		eventDate=LocalDate.now();
	}
	
}
