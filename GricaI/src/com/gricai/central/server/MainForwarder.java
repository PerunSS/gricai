package com.gricai.central.server;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.gricai.common.message.Message;
import com.gricai.common.message.MessageFactory;
import com.gricai.common.message.exception.WrongMessageTypeException;

import ch.unifr.nio.framework.transform.AbstractForwarder;

public class MainForwarder extends AbstractForwarder<ByteBuffer, ByteBuffer>{

	@Override
	public void forward(ByteBuffer input) throws IOException {
		//input je u stvari message
		try {
			Message msg = MessageFactory.createMessage(input);
			System.out.println("[SERVER] " + msg.getUsername());
		} catch (WrongMessageTypeException e) {
			e.printStackTrace();
		}
		
		nextForwarder.forward(input);
	}

}
