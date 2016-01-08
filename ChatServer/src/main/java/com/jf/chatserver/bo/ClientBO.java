package com.jf.chatserver.bo;

import java.net.Socket;
import java.util.ArrayList;


public class ClientBO {
	
	private String name; //TODO: figure out how & when the client name will be set
	private Socket connSocket;
	
	public ClientBO(String name, Socket connSocket) {
		super();
		this.name = name;
		this.connSocket = connSocket;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Socket getConnSocket() {
		return connSocket;
	}
	public void setConnSocket(Socket connSocket) {
		this.connSocket = connSocket;
	}
	
	public void sendMessage(Message msg) {
		
		if (! this.name.equals(msg.getSender())) {
			//send message
		}		
	}
	
	public Message getMessage() {
		//blocking call to receive message
		return null;
	}
	
	
}
