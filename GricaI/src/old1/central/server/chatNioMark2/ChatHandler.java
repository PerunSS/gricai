package old1.central.server.chatNioMark2;

import java.nio.ByteBuffer;

import old1.central.server.nioMark2.ChannelFacade;
import old1.central.server.nioMark2.InputHandler;
import old1.central.server.nioMark2.InputQueue;



public class ChatHandler implements InputHandler{
	
	private Lobby loby;
	
	public ChatHandler(Lobby loby){
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
		System.out.println("next message");
		return (inputQueue.dequeueBytes(nlPos + 1));
	}

	@Override
	public void started(ChannelFacade channelFacade) {
		// TODO Auto-generated method stub
		System.out.println("started");
	}

	@Override
	public void starting(ChannelFacade channelFacade) {
		// TODO Auto-generated method stub
		System.out.println("starting");
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
