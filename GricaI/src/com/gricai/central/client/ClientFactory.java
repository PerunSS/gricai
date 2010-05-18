package com.gricai.central.client;

import com.gricai.central.server.nio.InputHandler;
import com.gricai.central.server.nio.impl.InputHandlerFactory;

public class ClientFactory implements InputHandlerFactory {

	private Client client;
	
	public ClientFactory(Client client){
		this.client = client;
	}
	
	@Override
	public InputHandler newHandler() throws IllegalAccessException,
			InstantiationException {
		return new ClientHandler(client);
	}

}
