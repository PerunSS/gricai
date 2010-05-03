package com.gricai.common.message;

import java.nio.ByteBuffer;

public class JoinRoomResponseMessage implements Message {
	
	private static final String TEXT_CANJOIN = "can_join";
	private static final String TEXT_ROOMNAME = "room_name";
	
	private String roomName;
	private boolean canJoin;

	@Override
	public void fillMessage(ByteBuffer data) {
		byte[] bytes =  data.array();
		String fullMessage = new String(bytes);
		int indexOfRoom = fullMessage.indexOf('&')+1;
		int indexOfCanJoin = fullMessage.indexOf('&', indexOfRoom);
		String roomNameString = fullMessage.substring(indexOfRoom,indexOfCanJoin-1);
		setRoomName(roomNameString.substring(roomNameString.indexOf('=') + 1));
		String canJoinString = fullMessage.substring(indexOfCanJoin);
		String canJoin = canJoinString.substring(canJoinString.indexOf('=') + 1);
		if (canJoin.equals("true")){
			setCanJoin(true);
		} else setCanJoin(false);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteBuffer toByteBuffer() {
		byte[] bytes;
		if (isCanJoin()){
			bytes = new String("class=JoinRoomResponseMessage&"+TEXT_ROOMNAME+"=" + getRoomName()+ "&"+TEXT_CANJOIN+"=" + "true").getBytes();
		} else {
			bytes = new String("class=JoinRoomResponseMessage&"+TEXT_ROOMNAME+"=" + getRoomName()+ "&"+TEXT_CANJOIN+"=" + "false").getBytes();
		}
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

}
