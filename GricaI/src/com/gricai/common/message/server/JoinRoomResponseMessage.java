package com.gricai.common.message.server;

import java.nio.ByteBuffer;

import net.sf.json.JSONObject;

import com.gricai.common.message.Message;

public class JoinRoomResponseMessage implements Message {
	
	private static final String TEXT_MESSAGE = "JoinRoomResponseMessage";
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
		String text = "class="+TEXT_MESSAGE+"&"+TEXT_ROOMNAME+"=" + getRoomName()+ "&"+TEXT_CANJOIN+"=" + canJoin;
		ByteBuffer buffer = ByteBuffer.allocate(text.length()+4);
		buffer.putInt(text.length());
		buffer.put(text.getBytes());
		return buffer;
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
	public JSONObject toJsonObject() {
		JSONObject outer = new JSONObject();
		JSONObject inner = new JSONObject();
		outer.put(TEXT_MESSAGE, inner);
		inner.put(TEXT_ROOMNAME, roomName);
		inner.put(TEXT_CANJOIN, canJoin);
		return outer;
	}

}
