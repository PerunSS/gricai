package com.gricai.nio.framework.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.gricai.nio.framework.Client;
import com.gricai.nio.framework.Connection;

public abstract class ClientImpl implements Client {

	private SocketChannel channel;
	private Selector selector;
	private ExecutorService xec;
	private Connection connection;

	private Object mutex = new Object();

	private static final String IP = "localhost";
	private static final int PORT = 45453;
	
	public ClientImpl(){
		try {
			connect(IP,PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void connect(String host, int port) throws IOException {
		xec = Executors.newFixedThreadPool(Runtime.getRuntime()
				.availableProcessors() * 4);

		channel = SocketChannel.open();
		channel.configureBlocking(false);
		channel.connect(new InetSocketAddress(IP, PORT));
		selector = Selector.open();
		channel.register(selector, SelectionKey.OP_CONNECT
				| SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		xec.execute(this);
		synchronized (mutex) {
			while (connection == null) {
				try {
					mutex.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("connected");
		}
	}

	@Override
	public synchronized void sendMessage(ByteBuffer message) throws IOException {
		System.out.println("sending message");
		connection.send(message);
	}

	@Override
	public abstract void onMessage(SocketChannel socketChannel, ByteBuffer message)
			throws IOException;

	@Override
	public void run() {
		while (true) {
			try {
				selector.select(30);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectedKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				int kro = key.readyOps();
				if ((kro & SelectionKey.OP_READ) == SelectionKey.OP_READ)
					doRead(key);
				if ((kro & SelectionKey.OP_CONNECT) == SelectionKey.OP_CONNECT)
					doConnect(key);
				if ((kro & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE)
					doWrite(key);
				iterator.remove();
			}
		}
	}

	private void doConnect(SelectionKey key) {
		try {
			System.out.println("connecting...");
			SocketChannel keyChannel = (SocketChannel) key.channel();
			keyChannel.socket().setSoTimeout(100);
			if (keyChannel.isConnectionPending()) {
				keyChannel.finishConnect();
			}

			keyChannel.socket().setKeepAlive(true);
			ConnectionImpl conn = new ConnectionImpl(key, this);
			key.attach(conn);
			connection = conn;
			synchronized (mutex) {
				mutex.notifyAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void doRead(SelectionKey key) {
		ConnectionImpl conn = (ConnectionImpl) key.attachment();
		if (conn != null)
			conn.doRead();
		else {
			// TODO - logerr
		}
	}

	private void doWrite(SelectionKey key) {
		ConnectionImpl conn = (ConnectionImpl) key.attachment();
		if (conn != null)
			conn.doWrite();
		else {
			// TODO - logerr
		}
	}

}
