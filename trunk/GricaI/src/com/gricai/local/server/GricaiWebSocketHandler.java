package com.gricai.local.server;

import java.util.Properties;

import net.sf.json.JSONObject;

import org.jwebsocket.api.WebSocket;
import org.jwebsocket.api.WebSocketException;
import org.jwebsocket.api.WebSocketHandler;

import com.gricai.central.client.Client;
import com.gricai.common.message.Message;
import com.gricai.common.message.MessageFactory;
import com.gricai.common.message.exception.WrongMessageTypeException;
import com.gricai.common.message.server.LoginResponseMessage;

public class GricaiWebSocketHandler implements WebSocketHandler {
	
	private Client client;
	private String username;
	private WebSocket webSocket;
	private boolean logged = false;

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void init(Properties props) {
		client = new Client(this);
	}

	@Override
	public void onClose(WebSocket webSocket) {
		try {
			//client.close();
			webSocket.close();
//		} catch (IOException e) {
//			//TODO log err
//			e.printStackTrace();
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
//				Message response = null;//client.recieveMessage();
//				if(!logged && response instanceof LoginResponseMessage){
//					LoginResponseMessage lrm = (LoginResponseMessage)response;
//					username = lrm.getUsername();
//					logged = true;
//				}
//				sendMessageToWebClient(response);
			} catch (WrongMessageTypeException e) {
				e.printStackTrace();
//			} catch (WebSocketException e) {
//				e.printStackTrace();
			}
		}
	}

	@Override
	public void onOpen(WebSocket webSocket) {
		this.webSocket = webSocket;
	}
	
	public void sendMessageToWebClient(Message msg) throws WebSocketException{
		try {
			if(!logged && msg instanceof LoginResponseMessage){
				LoginResponseMessage lrm = (LoginResponseMessage)msg;
				username = lrm.getUsername();
				logged = true;
			}
			webSocket.send(msg.toByteBuffer().array());
		} catch (WebSocketException e) {
			e.printStackTrace();
			throw e;
		}
	}

}
