package com.gricai.common.message.common;

import java.nio.ByteBuffer;

import com.gricai.common.message.Message;

import net.sf.json.JSONObject;

public class ChatMessage implements Message {
	
	private static final String TEXT_USERNAME = "username";
	private static final String TEXT_TEXT = "text";
	
	private String username;
	private String text;


	@Override
	public void fillMessage(ByteBuffer data) {
		byte[] bytes =  data.array();
		String fullMessage = new String(bytes);
		int indexOfUser = fullMessage.indexOf('&')+1;
		int indexOfText = fullMessage.indexOf('&', indexOfUser);
		String userString = fullMessage.substring(indexOfUser,indexOfText);
		setUsername(userString.substring(userString.indexOf('=') + 1));
		String textString = fullMessage.substring(indexOfText);
		setText(textString.substring(textString.indexOf('=') + 1));
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public ByteBuffer toByteBuffer() {
		String text = "class=ChatMessage&"+TEXT_USERNAME+"=" + getUsername() + "&"+TEXT_TEXT+"=" + getText();
		ByteBuffer buffer = ByteBuffer.allocate(text.length()+4);
		buffer.putInt(text.length());
		buffer.put(text.getBytes());
		return buffer;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public JSONObject toJsonObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
