package com.bs.spring.websocket.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
	private String type;
	private String sender;
	private String receiver;
	private String room;
	private String msg;
}
