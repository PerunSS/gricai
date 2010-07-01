package old1.central.clientMark2;

import java.nio.ByteBuffer;

import old1.central.server.nioMark2.ChannelFacade;
import old1.central.server.nioMark2.InputHandler;


public class ClientInputHandler implements InputHandler {

	@Override
	public void handleInput(ByteBuffer message, ChannelFacade channelFacade) {
		// TODO Auto-generated method stub

	}

	@Override
	public ByteBuffer nextMessage(ChannelFacade channelFacade) {
		// TODO Auto-generated method stub
		return null;
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
