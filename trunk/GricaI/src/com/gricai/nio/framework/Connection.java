package com.gricai.nio.framework;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface Connection {

	public void send(ByteBuffer msg) throws IOException;
	public void close();
}
