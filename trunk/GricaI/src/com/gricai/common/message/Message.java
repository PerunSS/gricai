package com.gricai.common.message;

import java.nio.ByteBuffer;

public interface Message {

	public String getUsername();
	public ByteBuffer toByteBuffer();
	public void fillMessage(ByteBuffer data);
}
