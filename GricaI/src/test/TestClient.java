package test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.gricai.common.message.Message;
import com.gricai.common.message.MessageFactory;
import com.gricai.common.message.common.ChatMessage;
import com.gricai.common.message.exception.WrongMessageTypeException;
import com.gricai.nio.framework.impl.ClientImpl;

public class TestClient extends ClientImpl {

	@Override
	public void onMessage(SocketChannel socketChannel, ByteBuffer message)
			throws IOException {
		try {
			Message msg = MessageFactory.createMessage(message);
			if(msg instanceof ChatMessage){
				ChatMessage chmsg = (ChatMessage)msg;
				System.out.println(chmsg.getUsername()+": "+chmsg.getText());
			}
		} catch (WrongMessageTypeException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		TestClient client = new TestClient();
		Thread thread = new Thread(client);
		thread.start();
		ChatMessage msg = new ChatMessage();
		msg.setUsername("pera");
		msg.setText("testis testis");
		client.sendMessage(msg.toByteBuffer());
		msg.setUsername("kara");
		msg.setText("banana");
		client.sendMessage(msg.toByteBuffer());
	}

}
