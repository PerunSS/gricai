package com.gricai.central.clientMark2;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.gricai.common.message.Message;
import com.gricai.common.message.MessageFactory;
import com.gricai.common.message.exception.WrongMessageTypeException;


public class ClientListener implements Runnable {
	private Client client;
	private boolean working;
	private ByteBuffer buffer;
	
	public ClientListener(Client client){
		this.client = client;
		working = true;
		buffer = ByteBuffer.allocate(8192);
	}

	@Override
	public void run() {
		while(working){
			try {
				client.getChannel().read(buffer);
				Message msg = MessageFactory.createMessage(buffer);
				client.recieve(msg);
				buffer.clear();
			} catch (IOException e) {
				//TODO log
				e.printStackTrace();
			} catch (WrongMessageTypeException e) {
				//TODO log
				e.printStackTrace();
			}
		}
	}
	
	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void stop(){
		working = false;
	}

}
