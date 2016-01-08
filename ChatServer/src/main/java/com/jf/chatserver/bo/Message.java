package com.jf.chatserver.bo;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
	
	private String sender;	
	private String content;	
	private static final long serialVersionUID = 4801633306273802062L;
		
	public Message(String sender, String content) {
		this.sender = sender;
		this.content = content;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Message [sender=" + sender + ", content=" + content + "]";
	}
	
}
