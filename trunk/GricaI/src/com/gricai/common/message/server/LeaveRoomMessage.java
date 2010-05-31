package com.gricai.common.message.server;

import java.nio.ByteBuffer;

import com.gricai.common.message.Message;

import net.sf.json.JSONObject;

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

	@Override
	public void fillMessage(JSONObject jsonMessage) {
		// TODO Auto-generated method stub
		
	}

}
