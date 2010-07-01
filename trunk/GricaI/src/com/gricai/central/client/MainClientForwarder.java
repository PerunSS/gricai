package com.gricai.central.client;

import java.io.IOException;

import ch.unifr.nio.framework.transform.AbstractForwarder;

public class MainClientForwarder extends AbstractForwarder<String, Void> {

	MainClient client;
	
	MainClientForwarder(MainClient client){
		this.client = client;
	}
	
	@Override
	public void forward(String input) throws IOException {
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
