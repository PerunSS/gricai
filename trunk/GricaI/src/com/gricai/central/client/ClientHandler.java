package com.gricai.central.client;

import java.nio.ByteBuffer;

import com.gricai.central.server.nio.ChannelFacade;
import com.gricai.central.server.nio.InputHandler;
import com.gricai.central.server.nio.InputQueue;

public class ClientHandler implements InputHandler {
	
	private Client client;
	
	public ClientHandler(Client client){
		this.client = client;
	}

	@Override
	public void handleInput(ByteBuffer message, ChannelFacade channelFacade) {
		// TODO Auto-generated method stub

	}

	@Override
	public ByteBuffer nextMessage(ChannelFacade channelFacade) {
		InputQueue inputQueue = channelFacade.inputQueue();
		int nlPos = inputQueue.indexOf((byte) '\n');

		if (nlPos == -1)
			return null;

		if ((nlPos == 1) && (inputQueue.indexOf((byte) '\r') == 0)) {
			inputQueue.discardBytes(2); // eat CR/NL by itself
			return null;
		}
		System.out.println("next message");
		return (inputQueue.dequeueBytes(nlPos + 1));
	}

	@Override
	public void started(ChannelFacade channelFacade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void starting(ChannelFacade channelFacade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopped(ChannelFacade channelFacade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopping(ChannelFacade channelFacade) {
		// TODO Auto-generated method stub

	}

}
