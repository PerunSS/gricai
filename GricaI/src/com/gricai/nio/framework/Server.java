package com.gricai.nio.framework;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface Server extends ClientServerObject{

	
	public void sendMessage(SocketChannel socketChannel, ByteBuffer message) throws IOException;
	public void startServer(String filename) throws IOException;

}
