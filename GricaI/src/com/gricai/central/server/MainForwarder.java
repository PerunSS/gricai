package com.gricai.central.server;

import java.io.IOException;
import java.nio.ByteBuffer;

import ch.unifr.nio.framework.transform.AbstractForwarder;

public class MainForwarder extends AbstractForwarder<ByteBuffer, ByteBuffer>{

	@Override
	public void forward(ByteBuffer input) throws IOException {
		//input je u stvari message
		
		
		System.out.println("[SERVER] " + input.toString());
		nextForwarder.forward(input);
	}

}
