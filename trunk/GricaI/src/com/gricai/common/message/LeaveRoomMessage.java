package com.gricai.common.message;

import java.nio.ByteBuffer;

public class LeaveRoomMessage implements Message {

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
		byte[] bytes = new String("class=LeaveRoomMessage&").getBytes();
		return ByteBuffer.wrap(bytes);
	}

}
