package com.gricai.central.client;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Properties;

import org.jwebsocket.api.WebSocketException;

import com.gricai.common.message.Message;
import com.gricai.local.server.GricaiWebSocketHandler;
/**
 * client is used to communicate between central server and local server
 * @author aleksandarvaricak
 *
 */
public class Client {

	private static final String propertiesFile = "server.properties";
	private GricaiWebSocketHandler gricaiWebSocketHandler;
	private SocketChannel channel;
	private ClientListener listener;
	
	public SocketChannel getChannel() {
		return channel;
	}

	public Client(GricaiWebSocketHandler gricaiWebSocketHandler){
		this.gricaiWebSocketHandler = gricaiWebSocketHandler;
		Properties props = new Properties();
		try {
			props.load(new FileReader(propertiesFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int port = Integer.parseInt(props.getProperty("port"));
		String host = props.getProperty("host");
		try {
			channel = SocketChannel.open();
			channel.configureBlocking(false);
			channel.connect(new InetSocketAddress(host, port));
		} catch (IOException e) {
			e.printStackTrace();
		}
		listener = new ClientListener(this);
		listener.start();
	}
	
	public void sendMessage(Message msg){
		if(channel!=null && channel.isOpen()){
			try {
				channel.write(msg.toByteBuffer());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	void recieve(Message msg){
		try {
			gricaiWebSocketHandler.sendMessageToWebClient(msg);
		} catch (WebSocketException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Client(null);
	}
	

}
