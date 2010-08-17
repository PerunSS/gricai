package com.gricai.nio.framework.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.gricai.nio.framework.Connection;
import com.gricai.nio.framework.Server;
import com.gricai.nio.framework.User;

public abstract class ServerImpl implements Server {
	
	private ServerSocketChannel serverSocketChannel;
	private Selector selector;
	private int PORT = 45453;
	
	private Map<SocketChannel, User> connections = new HashMap<SocketChannel, User>();

	@Override
	public abstract void onMessage(SocketChannel socketChannel, ByteBuffer message)
			throws IOException;

	@Override
	public synchronized void sendMessage(SocketChannel socketChannel, ByteBuffer message)
			throws IOException {
		connections.get(socketChannel).getConnection().send(message);
	}

	@Override
	public void startServer(String filename) throws IOException {
		if(filename == null){
			filename = "server.properties";
		}
		loadProps(filename);
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.socket().bind(new InetSocketAddress(PORT));

		while (!serverSocketChannel.isOpen()) {
		}
		System.out.println("Server started...");

		selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void close(SocketChannel socketChannel) throws IOException {
		connections.remove(socketChannel);
	}

	private void loadProps(String filename) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		while(true){
			try {
				selector.selectNow();
			} 
			catch (Exception e) {
				//TODO - log err
				e.printStackTrace();
			}
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectedKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				int kro = key.isValid() ? key.readyOps() : -1;
				if ((kro & SelectionKey.OP_READ) == SelectionKey.OP_READ)
					doRead(key);
				if ((kro & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT)
					doAccept(key);
				if ((kro & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE)
					doWrite(key);
				iterator.remove();
			}

		}
	}
	
	private void doAccept(SelectionKey key) {
		try {
			ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
			SocketChannel userSocketChannel = serverSocketChannel.accept();
			userSocketChannel.configureBlocking(false);
			userSocketChannel.socket().setKeepAlive(true);
			SelectionKey selectionKey = userSocketChannel.register(selector, SelectionKey.OP_READ
					| SelectionKey.OP_WRITE);
			Connection conn = new ConnectionImpl(selectionKey, this);
			selectionKey.attach(conn);
			User user = new User();
			user.setConnection(conn);
			connections.put(userSocketChannel, user);
			System.out.println("added client");
		} catch (Exception e) {
			//TODO - logerr
		}

	}
	

	private void doRead(SelectionKey key) {
		ConnectionImpl conn = (ConnectionImpl) key.attachment();
		if (conn != null)
			conn.doRead();
		else {
			//TODO - logerr
		}
	}
	
	private void doWrite(SelectionKey key) {
		ConnectionImpl conn = (ConnectionImpl) key.attachment();
		if (conn != null)
			conn.doWrite();
		else {
			//TODO - logerr
		}
	}
	
	public void broadcast(ByteBuffer message) throws IOException{
		System.out.println("sending to all");
		for(Map.Entry<SocketChannel, User> entry:connections.entrySet()){
			entry.getValue().getConnection().send(message);
		}
	}

}
