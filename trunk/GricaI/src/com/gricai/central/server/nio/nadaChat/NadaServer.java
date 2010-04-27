package com.gricai.central.server.nio.nadaChat;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.gricai.central.server.nio.BufferFactory;
import com.gricai.central.server.nio.impl.DumbBufferFactory;
import com.gricai.central.server.nio.impl.InputHandlerFactory;
import com.gricai.central.server.nio.impl.NioDispatcher;
import com.gricai.central.server.nio.impl.StandardAcceptor;

/**
 * Created by IntelliJ IDEA. User: ron Date: May 15, 2006 Time: 6:20:59 PM
 */
public class NadaServer {
	private NadaServer() {
		// cannot instantiate
	}

	public static void main(String[] args) throws IOException {
		Executor executor = Executors.newCachedThreadPool();
		BufferFactory bufFactory = new DumbBufferFactory(1024);
		NioDispatcher dispatcher = new NioDispatcher(executor, bufFactory);
		InputHandlerFactory factory = new NadaProtocol();
		StandardAcceptor acceptor = new StandardAcceptor(1234, dispatcher,
				factory);

		dispatcher.start();
		acceptor.newThread();
	}
}
