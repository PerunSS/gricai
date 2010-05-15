package com.gricai.central.client;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Properties;

import com.gricai.common.message.Message;
import com.gricai.common.message.MessageFactory;
import com.gricai.common.message.exception.WrongMessageTypeException;
/**
 * client is used to communicate between central server and local server
 * @author aleksandarvaricak
 *
 */
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
	
	/**
	 * method sends message object to central server
	 * @param message
	 */
	public void sendMessage(Message message){
		try {
			os.write(message.toByteBuffer().array());
			os.write("\r\n".getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * method receives message object from central server
	 * @return message
	 */
	public Message recieveMessage(){
		byte[] buffer = new byte[1024];
		try {
			is.read(buffer);
			return MessageFactory.createMessage(ByteBuffer.wrap(buffer));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WrongMessageTypeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * this method need to be called when client is exiting
	 * @throws IOException
	 */
	public void close() throws IOException{
		is.close();
		os.close();
		socket.close();
	}
	
}
