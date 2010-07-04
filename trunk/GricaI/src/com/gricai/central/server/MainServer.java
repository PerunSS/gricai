package com.gricai.central.server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import ch.unifr.nio.framework.AbstractAcceptor;
import ch.unifr.nio.framework.ChannelHandler;
import ch.unifr.nio.framework.Dispatcher;

public class MainServer extends AbstractAcceptor {

	private static final String propertiesFile = "server.properties";
	private static MainServer instance;
	
	private Map<SocketChannel, MainHandler> handlers = new TreeMap<SocketChannel, MainHandler>();

	public MainServer(Dispatcher dispatcher, SocketAddress socketAddress)
			throws IOException {
		super(dispatcher, socketAddress);
	}

	@Override
	protected ChannelHandler getHandler(SocketChannel socketChannel) {
		MainHandler handler = handlers.get(socketChannel);
		if(handler == null)
			handler = new MainHandler(socketChannel);
		handlers.put(socketChannel, handler);
		return handler;
	}

	public static void startServer() throws IOException {
		if (instance == null) {
			Dispatcher dispatcher = new Dispatcher();
			dispatcher.start();

			Properties props = new Properties();
			try {
				props.load(new FileReader(propertiesFile));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int port = Integer.parseInt(props.getProperty("port"));
			SocketAddress socketAddress = new InetSocketAddress(port);
			instance = new MainServer(dispatcher, socketAddress);
			instance.start();
			System.out.println("[SERVER] " + "server started");
		}
	}

	public static void main(String[] args) throws IOException {
		MainServer.startServer();
	}
}
