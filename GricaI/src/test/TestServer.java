package test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.gricai.nio.framework.impl.ServerImpl;

public class TestServer extends ServerImpl {

	@Override
	public void onMessage(SocketChannel socketChannel, ByteBuffer message)
			throws IOException {
		ByteBuffer newBuffer = ByteBuffer.allocate(4+message.limit());
		newBuffer.putInt(message.limit());
		newBuffer.put(message);
		broadcast(newBuffer);
	}
	
	public static void main(String[] args) throws IOException {
		TestServer server = new TestServer();
		server.startServer("test");
	}

}
