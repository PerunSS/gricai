package com.gricai.common.message;

import java.nio.ByteBuffer;

import net.sf.json.JSONObject;

public class JoinRoomResponseMessage implements Message {
	
	private static final String TEXT_CANJOIN = "canJoin";
	private static final String TEXT_ROOMNAME = "roomName";
	
	private String roomName;
	private boolean canJoin;

	@Override
	public void fillMessage(ByteBuffer data) {
		byte[] bytes =  data.array();
		String fullMessage = new String(bytes);
		int indexOfRoom = fullMessage.indexOf('&')+1;
		int indexOfCanJoin = fullMessage.indexOf('&', indexOfRoom);
		String roomNameString = fullMessage.substring(indexOfRoom,indexOfCanJoin);
		setRoomName(roomNameString.substring(roomNameString.indexOf('=') + 1));
		String canJoinString = fullMessage.substring(indexOfCanJoin);
		setCanJoin(Boolean.parseBoolean(canJoinString.substring(canJoinString.indexOf('=') + 1)));
		
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteBuffer toByteBuffer() {
		byte[] bytes = new String("class=JoinRoomResponseMessage&"+TEXT_ROOMNAME+"=" + getRoomName()+ "&"+TEXT_CANJOIN+"=" + canJoin).getBytes();
		
		return ByteBuffer.wrap(bytes);
	}

	public void setCanJoin(boolean canJoin) {
		this.canJoin = canJoin;
	}

	public boolean isCanJoin() {
		return canJoin;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	@Override
	public void fillMessage(JSONObject jsonMessage) {
		// TODO Auto-generated method stub
		
	}

}
