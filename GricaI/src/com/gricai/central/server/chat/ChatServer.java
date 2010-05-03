package com.gricai.central.server.chat;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.gricai.central.server.nio.BufferFactory;
import com.gricai.central.server.nio.impl.DumbBufferFactory;
import com.gricai.central.server.nio.impl.InputHandlerFactory;
import com.gricai.central.server.nio.impl.NioDispatcher;
import com.gricai.central.server.nio.impl.StandardAcceptor;

public class ChatServer {

	private ChatServer(){}
	public static void main(String[] args) throws IOException {
		Executor executor = Executors.newCachedThreadPool();
		BufferFactory bufFactory = new DumbBufferFactory(1024);
		NioDispatcher dispatcher = new NioDispatcher(executor, bufFactory);
		InputHandlerFactory factory = new Loby();
		StandardAcceptor acceptor = new StandardAcceptor(12345, dispatcher,
				factory);

		dispatcher.start();
		acceptor.newThread();
	}
}
