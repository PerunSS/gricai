package com.gricai.central.server.message;

public interface Message {

	public byte[] toByteArray();
	public void fillMessage(byte[] data);
}
