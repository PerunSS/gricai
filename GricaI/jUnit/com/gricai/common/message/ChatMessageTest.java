package com.gricai.common.message;

import org.junit.Test;

import com.gricai.common.message.common.ChatMessage;
import com.gricai.common.message.exception.WrongMessageTypeException;


public class ChatMessageTest {

	@Test
	public void test1() throws WrongMessageTypeException{
		String username = "djura";
		String text = "testis testis";
		ChatMessage msg = new ChatMessage();
		msg.setUsername(username);
		msg.setText(text);
		
		Message msg1 = MessageFactory.createMessage(msg.toByteBuffer());
		
		Message msg2 = MessageFactory.createMessage(msg1.toByteBuffer());
		
		Message msg3 = MessageFactory.createMessage(msg2.toByteBuffer());
		
		assert ((ChatMessage)msg3).getText().equalsIgnoreCase(text);
	}
}
