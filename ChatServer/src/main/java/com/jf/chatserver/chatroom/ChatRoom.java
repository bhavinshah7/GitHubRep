package com.jf.chatserver.chatroom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import com.jf.chatserver.bo.ClientBO;
import com.jf.chatserver.bo.Message;

public class ChatRoom implements Runnable {

	private String name;
	private int port;
	private int capacity;
	private ArrayList<ClientBO> members; 
	private LinkedBlockingQueue<Message> messages; 
	private MessageDispatcher dispatcher; 

	public ChatRoom(String name, int port, int capacity) {
		this.name = name;
		this.port = port;
		this.capacity = capacity;
		this.members = new ArrayList<ClientBO>();
		this.messages = new LinkedBlockingQueue<Message>();
		this.dispatcher = new MessageDispatcher(this);		
	}

	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public ArrayList<ClientBO> getMembers() {
		return members;
	}
	public void addMessage(Message msg) {
		messages.add(msg);
	}

	public Message pollMessage() {
		return messages.poll();
	}

	public void clearMessages() {
		messages.clear();
	}


	@Override
	public void run() {
		
		dispatcher.start();

		try {
			ServerSocket ss = new ServerSocket(port);
			Socket socket;			
			for (int i=0; i<capacity; i++){				
				try {
					socket = ss.accept(); 
					ClientBO client = new ClientBO("", socket);
					members.add(client);
					Conversation convTh = new Conversation(client,this);
					convTh.start();
					// Map of service threads to be stopped when client disconnects or becomes inactive??
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {				
			e1.printStackTrace();
		}

	}

}
