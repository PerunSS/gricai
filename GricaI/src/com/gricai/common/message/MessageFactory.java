package com.gricai.common.message;

import java.nio.ByteBuffer;

import com.gricai.common.message.exception.WrongMessageTypeException;

public class MessageFactory {

	private static final String CHAT_MESSAGE = "ChatMessage";
	private static final String ERROR_MESSAGE = "ErrorMessage";
	private static final String JOIN_ROOM_MESSAGE = "JoinRoomMessage";
	private static final String JOIN_ROOM_RESPONSE_MESSAGE = "JoinRoomResponseMessage";
	private static final String LEAVE_ROOM_MESSAGE = "LeaveRoomMessage";
	private static final String LOGIN_MESSAGE = "LoginMessage";
	private static final String LOGIN_RESPONSE_MESSAGE = "LoginResponseMessage";
	
	public static Message createMessage(ByteBuffer data) throws WrongMessageTypeException{
		byte[] bytes =  new byte[data.capacity()];
		data.get(bytes, 0, bytes.length);
		String fullMessage = new String(bytes);
		String messageType = fullMessage.substring(fullMessage.indexOf('=') + 1,fullMessage.indexOf('&'));
		Message message;
		if ( messageType == CHAT_MESSAGE){
			message = new ChatMessage();
		} else if ( messageType == ERROR_MESSAGE){
			message = new ErrorMessage();
		} else if ( messageType == JOIN_ROOM_MESSAGE){
			message = new JoinRoomMessage();
		} else if ( messageType == JOIN_ROOM_RESPONSE_MESSAGE){
			message = new JoinRoomResponseMessage();
		} else if ( messageType == LEAVE_ROOM_MESSAGE){
			message = new LeaveRoomMessage();
		} else if ( messageType == LOGIN_MESSAGE){
			message = new LoginMessage();
		} else if ( messageType == LOGIN_RESPONSE_MESSAGE){
			message = new LoginResponseMessage();
		} else {
			throw new WrongMessageTypeException("Wrong message type!");
		}
		
		message.fillMessage(data);
		
		return message;
	}
}
