package com.gricai.central.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.gricai.common.message.Message;
import com.gricai.common.message.MessageFactory;
import com.gricai.common.message.exception.WrongMessageTypeException;
import com.gricai.nio.framework.impl.ClientImpl;

public class Client extends ClientImpl {

	@Override
	public void onMessage(SocketChannel socketChannel, ByteBuffer message)
			throws IOException {
		try {
			Message msg = MessageFactory.createMessage(message);
			System.out.println("CLIENT: "+msg.getClass());
		} catch (WrongMessageTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
