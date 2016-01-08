package com.jf.chatserver.chatroom;

import com.jf.chatserver.bo.ClientBO;
import com.jf.chatserver.bo.Message;

public class Conversation extends Thread{
	
	private ClientBO client;
	private ChatRoom chatRoom;
	
	
	public Conversation(ClientBO client, ChatRoom chatRoom) {
		super();
		this.client = client;
		this.chatRoom = chatRoom;
	}
	public ClientBO getClient() {
		return client;
	}
	public void setClient(ClientBO client) {
		this.client = client;
	}
	public ChatRoom getChatRoom() {
		return chatRoom;
	}
	public void setChatRoom(ChatRoom chatRoom) {
		this.chatRoom = chatRoom;
	}
	@Override
	public void run() {
				
		while(true) {
			
			Message msg = client.getMessage();
			if (msg == null) { //TODO: add null checks for content and sender at client side
				continue;
			}
			chatRoom.addMessage(msg);
		}
		
	}
	

}
