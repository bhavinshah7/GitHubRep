package com.jf.chatserver.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Service implements Runnable {
	
	Socket socket;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	public Service(Socket socket) {
		this.socket = socket;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
