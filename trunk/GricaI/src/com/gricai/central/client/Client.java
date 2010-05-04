package com.gricai.central.client;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Properties;

import com.gricai.common.message.ChatMessage;
import com.gricai.common.message.Message;
import com.gricai.common.message.MessageFactory;
import com.gricai.common.message.exception.WrongMessageTypeException;

public class Client {

	private InputStream is;
	private OutputStream os;
	private Socket socket;
	private static final String propertiesFile = "server.properties";
	
	public Client(){
		try {
			Properties props = new Properties();
			props.load(new FileReader(propertiesFile));
			String host = props.getProperty("host");
			int port = Integer.getInteger(props.getProperty("port"));
			socket = new Socket(host, port);
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
