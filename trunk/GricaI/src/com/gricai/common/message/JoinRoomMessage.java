package com.gricai.common.message;

import java.nio.ByteBuffer;

public class JoinRoomMessage implements Message {
	
	private String roomName;

	@Override
	public void fillMessage(ByteBuffer data) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteBuffer toByteBuffer() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return roomName;
	}

}
