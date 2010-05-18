package com.gricai.central.client;

import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.gricai.central.server.nio.BufferFactory;
import com.gricai.central.server.nio.InputHandler;
import com.gricai.central.server.nio.impl.DumbBufferFactory;
import com.gricai.central.server.nio.impl.InputHandlerFactory;
import com.gricai.central.server.nio.impl.NioDispatcher;
import com.gricai.central.server.nio.impl.StandardAcceptor;
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
	private InputHandlerFactory factory;
	private InputHandler handler;
	
	public Client(GricaiWebSocketHandler gricaiWebSocketHandler){
		this.gricaiWebSocketHandler = gricaiWebSocketHandler;
		try {
			Executor executor = Executors.newCachedThreadPool();
			BufferFactory bufFactory = new DumbBufferFactory(1024);
			NioDispatcher dispatcher = new NioDispatcher(executor, bufFactory);
			Properties props = new Properties();
			props.load(new FileReader(propertiesFile));
			String host = props.getProperty("host");
			int port = Integer.parseInt(props.getProperty("port"));
			handler = new ClientHandler(this);
			
			dispatcher.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(Message msg){
	
	}
	
	public static void main(String[] args) {
		new Client(null);
	}
	

}
