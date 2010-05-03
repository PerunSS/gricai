package com.gricai.common.message;

import java.nio.ByteBuffer;

public class ChatMessage implements Message {
	
	private String userName;
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public void fillMessage(ByteBuffer data) {
		byte[] bytes =  new byte[data.capacity()];
		data.get(bytes, 0, bytes.length);
		String fullMessage = new String(bytes);
		setUserName(fullMessage.substring(fullMessage.indexOf('&'),fullMessage.indexOf('&',fullMessage.indexOf('&')) ));
		setMessage(fullMessage.substring(fullMessage.indexOf('&', fullMessage.indexOf('&'))));
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteBuffer toByteBuffer() {
		byte[] bytes = new String("Chat message &" + getUserName() + "&" + getMessage()).getBytes();
		return ByteBuffer.wrap(bytes);
	}

}
