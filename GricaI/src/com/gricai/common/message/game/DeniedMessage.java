package com.gricai.common.message.game;

import java.nio.ByteBuffer;

import net.sf.json.JSONObject;

import com.gricai.common.message.Message;

public class DeniedMessage implements Message {
	private static final String TEXT_MESSAGE = "DeniedMessage";
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
	
	@Override
	public JSONObject toJsonObject(){
		JSONObject msg = new JSONObject();
		msg.put(TEXT_MESSAGE, null);
		return msg;
	}

}
