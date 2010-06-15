package com.gricai.common.message.game;

import java.nio.ByteBuffer;

import net.sf.json.JSONObject;

import com.gricai.common.message.Message;

public class JoinGameMessage implements Message {
	private static final String TEXT_MESSAGE = "ChangePositionMessage";
	private static final String TEXT_USERNAME = "username";
	
	private String username;

	@Override
	public void fillMessage(ByteBuffer data) {
		byte[] bytes =  data.array();
		String fullMessage = new String(bytes);
		int indexOfUser = fullMessage.indexOf('&')+1;
		String userString = fullMessage.substring(indexOfUser);
		setUsername(userString.substring(userString.indexOf('=') + 1));
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public ByteBuffer toByteBuffer() {
		byte[] bytes = new String("class="+TEXT_MESSAGE+"&"+TEXT_USERNAME+"=" + getUsername()).getBytes();
		return ByteBuffer.wrap(bytes);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public JSONObject toJsonObject() {
		JSONObject outer = new JSONObject();
		JSONObject inner = new JSONObject();
		outer.put(TEXT_MESSAGE, inner);
		inner.put(TEXT_USERNAME, username);
		return outer;
	}

}
