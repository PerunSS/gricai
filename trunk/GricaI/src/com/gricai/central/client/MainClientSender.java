package com.gricai.central.client;

import java.io.IOException;
import java.nio.ByteBuffer;

import ch.unifr.nio.framework.transform.AbstractForwarder;

public class MainClientSender extends AbstractForwarder<ByteBuffer, ByteBuffer> {

	@Override
	public void forward(ByteBuffer input) throws IOException {
		nextForwarder.forward(input);
	}

}
