package com.gricai.nio.framework;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface Client extends ClientServerObject{

	public void connect(String host, int port) throws IOException;
	public void sendMessage(ByteBuffer message) throws IOException;
	
}
