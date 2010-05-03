package com.gricai.central.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import com.gricai.common.message.ChatMessage;
import com.gricai.common.message.Message;
import com.gricai.common.message.MessageFactory;
import com.gricai.common.message.exception.WrongMessageTypeException;

public class Client {

	InputStream is;
	OutputStream os;
	Socket socket;
	
	public Client(){
		try {
			socket = new Socket("localhost", 12345);
			is = socket.getInputStream();
			os = socket.getOutputStream();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(Message message){
		try {
			os.write(message.toByteBuffer().array());
			os.write("\r\n".getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Message recieveMessage(){
		byte[] buffer = new byte[1024];
		try {
			is.read(buffer);
			return MessageFactory.createMessage(ByteBuffer.wrap(buffer));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongMessageTypeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void close() throws IOException{
		is.close();
		os.close();
		socket.close();
	}
	
	public static void main(String[] args) {
		Client c = new Client();
		System.out.println("sending...");
		c.sendMessage(new ChatMessage());
		System.out.println("sent");
		Message msg = c.recieveMessage();
		System.out.println(msg);
	}
}
