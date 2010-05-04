package com.gricai.central.server.chat;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.gricai.central.server.nio.BufferFactory;
import com.gricai.central.server.nio.impl.DumbBufferFactory;
import com.gricai.central.server.nio.impl.InputHandlerFactory;
import com.gricai.central.server.nio.impl.NioDispatcher;
import com.gricai.central.server.nio.impl.StandardAcceptor;

public class ChatServer {

	private static final String propertiesFile = "server.properties";

	private ChatServer() {
	}

	public static void main(String[] args) throws IOException {
		Properties props = new Properties();
		props.load(new FileReader(propertiesFile));
		Executor executor = Executors.newCachedThreadPool();
		BufferFactory bufFactory = new DumbBufferFactory(1024);
		NioDispatcher dispatcher = new NioDispatcher(executor, bufFactory);
		InputHandlerFactory factory = new Loby();
		int port = Integer.parseInt(props.getProperty("prop"));
		StandardAcceptor acceptor = new StandardAcceptor(port, dispatcher,
				factory);

		dispatcher.start();
		acceptor.newThread();
	}
}
