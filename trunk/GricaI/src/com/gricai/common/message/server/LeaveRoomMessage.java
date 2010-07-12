package com.gricai.common.message.server;

import java.nio.ByteBuffer;

import net.sf.json.JSONObject;

import com.gricai.common.message.Message;

public class LeaveRoomMessage implements Message {

	private static final String TEXT_MESSAGE = "LeaveRoomMessage";
	
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
		byte[] bytes = new String("class="+TEXT_MESSAGE+"&").getBytes();
		return ByteBuffer.wrap(bytes);
	}

	@Override
	public JSONObject toJsonObject() {
		JSONObject outer = new JSONObject();
		outer.put(TEXT_MESSAGE, null);
		return outer;
	}

}
