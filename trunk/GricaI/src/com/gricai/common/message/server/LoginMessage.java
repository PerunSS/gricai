package com.gricai.common.message.server;

import java.nio.ByteBuffer;

import net.sf.json.JSONObject;

import com.gricai.common.message.Message;

public class LoginMessage implements Message {
	
	private static final String TEXT_MESSAGE = "LoginMessage";
	private static final String TEXT_USERNAME = "username";
	private static final String TEXT_PASSWORD = "password";

	private String username;
	private String password;
	
	@Override
	public void fillMessage(ByteBuffer data) {
		byte[] bytes =  data.array();
		String fullMessage = new String(bytes);
		int indexOfUser = fullMessage.indexOf('&')+1;
		int indexOfPassword = fullMessage.indexOf('&', indexOfUser);
		String userString = fullMessage.substring(indexOfUser,indexOfPassword);
		setUsername(userString.substring(userString.indexOf('=') + 1));
		String passwordString = fullMessage.substring(indexOfPassword);
		setPassword(passwordString.substring(passwordString.indexOf('=') + 1));

	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public ByteBuffer toByteBuffer() {
		String text = "class=LoginMessage&"+TEXT_USERNAME+"=" + getUsername() + "&"+TEXT_PASSWORD+"=" + getPassword();
		ByteBuffer buffer = ByteBuffer.allocate(text.length()+4);
		buffer.putInt(text.length());
		buffer.put(text.getBytes());
		return buffer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		inner.put(TEXT_PASSWORD, password);
		return outer;
	}

}