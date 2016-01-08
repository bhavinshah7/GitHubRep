package com.jf.chatserver.chatroom;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import com.jf.chatserver.bo.ClientBO;
import com.jf.chatserver.bo.Message;

public class MessageDispatcher extends Thread {
	
	private ChatRoom chatRoom;
	
	public MessageDispatcher(ChatRoom chatroom) {
		this.chatRoom = chatroom;		
	}



	@Override
	public void run() {		
		while(true) {

			Message msg = chatRoom.pollMessage();
			if (msg == null) {
				continue;
			}

			for (ClientBO client : chatRoom.getMembers()) {					
				client.sendMessage(msg);					
			}

		}
	}

}
