package com.gricai.central.server.chatNioMark2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.gricai.central.server.nioMark2.BufferFactory;
import com.gricai.central.server.nioMark2.impl.DumbBufferFactory;
import com.gricai.central.server.nioMark2.impl.InputHandlerFactory;
import com.gricai.central.server.nioMark2.impl.NioDispatcher;
import com.gricai.central.server.nioMark2.impl.StandardAcceptor;

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
		InputHandlerFactory factory = new Lobby();
		int port = Integer.parseInt(props.getProperty("port"));
		StandardAcceptor acceptor = new StandardAcceptor(port, dispatcher,
				factory);

		dispatcher.start();
		acceptor.newThread();
	}
}
