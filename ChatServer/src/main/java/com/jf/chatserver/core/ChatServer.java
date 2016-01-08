package com.jf.chatserver.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.jf.chatserver.bo.ClientBO;
import com.jf.chatserver.chatroom.ChatRoom;

public class ChatServer implements Runnable{

	private int port;
	private HashMap<String, ChatRoom> hmIdVsChatRoom;
	private HashMap<String, ClientBO> hmNameVsClient;
	private static int counter;
	
	public ChatServer(int port) {
		this.port = port;
		this.hmIdVsChatRoom = new HashMap<String, ChatRoom>();
	}

	@Override
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(port);			
			while (true) {
				try {
					Socket socket = ss.accept();
					
					Thread serviceTh = new Thread(new Service(socket));
					serviceTh.start();
					
//					ObjectInputStream 
					
					BufferedReader nis = new BufferedReader(new InputStreamReader(socket.getInputStream()));					
					String name = nis.readLine();
					//unique id needed
					if (hmNameVsClient.containsKey(name)) {
						name = "client" + counter;
						counter ++;
					}					
					ClientBO client = new ClientBO(name, socket);
					hmNameVsClient.put(name, client);
					
				} catch (IOException e) {				
					e.printStackTrace();
				}				
			}			
		} catch (IOException e1) {			
			e1.printStackTrace();
		}





	}

}
