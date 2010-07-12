package com.gricai.nio.framework;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface ClientServerObject extends Runnable {

	public void onMessage(SocketChannel socketChannel, ByteBuffer message) throws IOException;
}
