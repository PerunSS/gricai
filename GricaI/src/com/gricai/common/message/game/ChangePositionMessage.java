package com.gricai.common.message.game;

import java.nio.ByteBuffer;

import net.sf.json.JSONObject;

import com.gricai.common.message.Message;

public class ChangePositionMessage implements Message {
	private static final String TEXT_MESSAGE = "ChangePositionMessage";
	private static final String TEXT_USERNAME = "username";
	private static final String TEXT_POSITION = "position";
	
	private int position;
	private String username;
	
	@Override
	public void fillMessage(ByteBuffer data) {
		byte[] bytes =  data.array();
		String fullMessage = new String(bytes);
		int indexOfUser = fullMessage.indexOf('&')+1;
		int indexOfPosition = fullMessage.indexOf('&', indexOfUser);
		String userString = fullMessage.substring(indexOfUser,indexOfPosition);
		setUsername(userString.substring(userString.indexOf('=') + 1));
		String textString = fullMessage.substring(indexOfPosition);
		setPosition(Integer.parseInt(textString.substring(textString.indexOf('=') + 1)));
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public ByteBuffer toByteBuffer() {
		String text = "class="+TEXT_MESSAGE+"&"+TEXT_USERNAME+"=" + getUsername() + "&"+TEXT_POSITION+"=" + position;
		ByteBuffer buffer = ByteBuffer.allocate(text.length()+4);
		buffer.putInt(text.length());
		buffer.put(text.getBytes());
		return buffer;
	}
	
	@Override
	public JSONObject toJsonObject(){
		JSONObject outer = new JSONObject();
		JSONObject inner = new JSONObject();
		outer.put(TEXT_MESSAGE, inner);
		inner.put(TEXT_USERNAME, username);
		inner.put(TEXT_POSITION, position);
		return outer;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
