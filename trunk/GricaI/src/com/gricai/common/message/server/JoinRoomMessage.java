package com.gricai.common.message.server;

import java.nio.ByteBuffer;

import net.sf.json.JSONObject;

import com.gricai.common.message.Message;

public class JoinRoomMessage implements Message {
	
	private static final String TEXT_MESSAGE = "JoinRoomMessage";
	private static final String TEXT_USERNAME = "username";
	private static final String TEXT_ROOMNAME = "text";
	
	private String roomName;
	private String username;

	@Override
	public void fillMessage(ByteBuffer data) {
		byte[] bytes =  data.array();
		String fullMessage = new String(bytes);
		int indexOfUser = fullMessage.indexOf('&')+1;
		int indexOfRoom = fullMessage.indexOf('&', indexOfUser);
		String userString = fullMessage.substring(indexOfUser,indexOfRoom);
		setUsername(userString.substring(userString.indexOf('=') + 1));
		String roomNameString = fullMessage.substring(indexOfRoom);
		setRoomName(roomNameString.substring(roomNameString.indexOf('=') + 1));
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public ByteBuffer toByteBuffer() {
		byte[] bytes = new String("class="+TEXT_MESSAGE+"&"+TEXT_USERNAME+"=" + getUsername() + "&"+TEXT_ROOMNAME+"=" + getRoomName()).getBytes();
		return ByteBuffer.wrap(bytes);
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return roomName;
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
		inner.put(TEXT_ROOMNAME, roomName);
		return outer;
	}

}
