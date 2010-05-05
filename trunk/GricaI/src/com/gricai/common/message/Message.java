package com.gricai.common.message;

import java.nio.ByteBuffer;

import net.sf.json.JSONObject;

public interface Message {

	public String getUsername();
	public ByteBuffer toByteBuffer();
	public void fillMessage(ByteBuffer data);
	public void fillMessage(JSONObject jsonMessage);
}
