package com.gricai.central.server;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import ch.unifr.nio.framework.AbstractChannelHandler;

public class MainHandler extends AbstractChannelHandler {

	private SocketChannel socketChannel;
	
	public MainHandler(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
		MainForwarder forwarder = new MainForwarder();
		
		reader.setNextForwarder(forwarder);
		
		forwarder.setNextForwarder(writer);
	}

	@Override
	public void channelException(Exception exception) {
		System.out.println("[SERVER] " + "Exception on channel: " + exception);
		try {
			handlerAdapter.closeChannel();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void inputClosed() {
		System.out.println("[SERVER] " + "EchoClient closed the connection");
		try {
			handlerAdapter.closeChannel();
		} catch (IOException ex) {
			// TODO change this
			ex.printStackTrace();
		}

	}

	public void setSocketChannel(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	public SocketChannel getSocketChannel() {
		return socketChannel;
	}

}
