package com.bs.spring.websocket.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChattingServer extends TextWebSocketHandler{
	
	private Map<String,WebSocketSession> clients=new HashMap<>();
	
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("손님들어왔다!");
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Message msg=mapper.readValue(message.getPayload(),
											Message.class);//json
		
		switch(msg.getType()) {
			case "open": addClient(session, msg);break;
			case "msg" : sendMessage(msg);break;
			case "close" : break;
		}		
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String id="";
		for(Map.Entry<String,WebSocketSession> client:clients.entrySet()) {
			WebSocketSession cSession=client.getValue();
			if(session.equals(cSession)) {
				id=client.getKey();
				break;
			}
		}
		clients.remove(id);
		sendMessage(Message.builder().type("close").sender(id).build());
		attendMessage();
	}
	
	private void addClient(WebSocketSession session, Message msg) {
		clients.put(msg.getSender(), session);
		sendMessage(msg);
		attendMessage();
		
	}
	private void attendMessage() {
		try {
			Message msg=Message.builder().type("attend")
					.msg(mapper.writeValueAsString(clients.keySet()))
					.build();
			sendMessage(msg);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void sendMessage(Message msg) {
		
		for(Map.Entry<String,WebSocketSession> client:clients.entrySet()) {
			WebSocketSession cSession=client.getValue();
			try {
				String message=mapper.writeValueAsString(msg);
				cSession.sendMessage(new TextMessage(message));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
