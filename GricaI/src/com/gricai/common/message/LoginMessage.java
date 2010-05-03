package com.gricai.common.message;

import java.nio.ByteBuffer;

public class LoginMessage implements Message {
	
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
		String userString = fullMessage.substring(indexOfUser,indexOfPassword-1);
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
		byte[] bytes = new String("class=LoginMessage&"+TEXT_USERNAME+"=" + getUsername() + "&"+TEXT_PASSWORD+"=" + getPassword()).getBytes();
		return ByteBuffer.wrap(bytes);
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

}
