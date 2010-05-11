package com.gricai.local.server;

import java.io.IOException;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.jwebsocket.api.WebSocket;
import org.jwebsocket.api.WebSocketException;
import org.jwebsocket.api.WebSocketHandler;

import com.gricai.central.client.Client;
import com.gricai.common.message.Message;
import com.gricai.common.message.MessageFactory;
import com.gricai.common.message.exception.WrongMessageTypeException;

public class GricaiWebSocketHandler implements WebSocketHandler {
	
	private Client client;
	private String username;

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void init(Properties props) {
		client = new Client();
	}

	@Override
	public void onClose(WebSocket webSocket) {
		try {
			client.close();
			webSocket.close();
		} catch (IOException e) {
			//TODO log err
			e.printStackTrace();
		} catch (WebSocketException e) {
			// TODO log err
			e.printStackTrace();
		}
	}

	@Override
	public void onException(WebSocket webSocket, Throwable e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMessage(WebSocket webSocket, Object message) {
		if(message instanceof JSONObject){
			try {
				Message msg = MessageFactory.createMessage((JSONObject)message, username);
				client.sendMessage(msg);
				Message response = client.recieveMessage();
				webSocket.send(response.toByteBuffer().array());
			} catch (WrongMessageTypeException e) {
				e.printStackTrace();
			} catch (WebSocketException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onOpen(WebSocket webSocket) {
		// TODO Auto-generated method stub

	}

}
