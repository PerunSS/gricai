package com.gricai.common.message;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class ChatMessage implements Message {
	
	private static final String TEXT_USERNAME = "username";
	private static final String TEXT_TEXT = "text";
	
	private String username;
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	@Override
	public void fillMessage(ByteBuffer data) {
		byte[] bytes =  new byte[data.capacity()];
		data.get(bytes, 0, bytes.length);
		String fullMessage = new String(bytes);
		Map<String, String> messageData = new HashMap<String, String>();
		String parts[] = fullMessage.split("&");
		for(String part:parts){
			String key = part.substring(0,part.indexOf('='));
			String value = part.substring(part.indexOf('=')+1);
			messageData.put(key, value);
		}
		username = messageData.get(TEXT_USERNAME);
		text = messageData.get(TEXT_TEXT);
//		setUserName(fullMessage.substring(fullMessage.indexOf('&'),fullMessage.indexOf('&',fullMessage.indexOf('&')) ));
//		setText(fullMessage.substring(fullMessage.indexOf('&', fullMessage.indexOf('&'))));
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public ByteBuffer toByteBuffer() {
		byte[] bytes = new String("class=ChatMessage&"+TEXT_USERNAME+"=" + getUsername() + "&"+TEXT_TEXT+"=" + getText()).getBytes();
		return ByteBuffer.wrap(bytes);
	}


	public void setUserName(String username) {
		this.username = username;
	}

}
