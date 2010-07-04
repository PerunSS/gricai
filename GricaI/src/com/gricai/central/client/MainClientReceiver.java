package com.gricai.central.client;

import java.io.IOException;
import java.nio.ByteBuffer;

import ch.unifr.nio.framework.transform.AbstractForwarder;

public class MainClientReceiver extends AbstractForwarder<ByteBuffer, Void> {

	MainClient client;
	
	MainClientReceiver(MainClient client){
		this.client = client;
	}
	
	@Override
	public void forward(ByteBuffer input) throws IOException {
		// print out incoming string
		System.out.println("[CLIENT] " + "received \"" + input + "\"");

		// signal that input has arrived
		client.getLock().lock();
		try {
			client.getInputArrived().signalAll();
		} finally {
			client.getLock().unlock();
		}

	}

}
