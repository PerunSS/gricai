package com.gricai.central.server.chat;

import java.nio.ByteBuffer;

import com.gricai.central.server.nio.ChannelFacade;
import com.gricai.central.server.nio.InputHandler;
import com.gricai.central.server.nio.InputQueue;


public class ChatHandler implements InputHandler{
	
	private Loby loby;
	
	public ChatHandler(Loby loby){
		this.loby = loby;
	}

	@Override
	public void handleInput(ByteBuffer message, ChannelFacade channelFacade) {
		loby.handleMessage(message, channelFacade);		
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
