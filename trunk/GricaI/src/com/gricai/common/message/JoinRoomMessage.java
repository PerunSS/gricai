package com.gricai.common.message;

import java.nio.ByteBuffer;

public class JoinRoomMessage implements Message {
	
	private static final String TEXT_USERNAME = "username";
	private static final String TEXT_ROOMNAME = "text";
	
	private String roomName;
	private String username;

	@Override
	public void fillMessage(ByteBuffer data) {
		byte[] bytes =  new byte[data.capacity()];
		data.get(bytes, 0, bytes.length);
		String fullMessage = new String(bytes);
		int indexOfUser = fullMessage.indexOf('&')+1;
		int indexOfRoom = fullMessage.indexOf('&', indexOfUser);
		String userString = fullMessage.substring(indexOfUser,indexOfRoom-1);
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
		byte[] bytes = new String("class=JoinRoomMessage&"+TEXT_USERNAME+"=" + getUsername() + "&"+TEXT_ROOMNAME+"=" + getRoomName()).getBytes();
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

}
