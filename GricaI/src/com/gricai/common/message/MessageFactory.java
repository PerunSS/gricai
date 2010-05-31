package com.gricai.common.message;

import java.nio.ByteBuffer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gricai.common.message.exception.WrongMessageTypeException;
import com.gricai.common.message.server.ChatMessage;
import com.gricai.common.message.server.ErrorMessage;
import com.gricai.common.message.server.JoinRoomMessage;
import com.gricai.common.message.server.LeaveRoomMessage;
import com.gricai.common.message.server.LoginMessage;

public class MessageFactory {

	private static final String CHAT_MESSAGE = "ChatMessage";
	private static final String ERROR_MESSAGE = "ErrorMessage";
	private static final String JOIN_ROOM_MESSAGE = "JoinRoomMessage";
//	private static final String JOIN_ROOM_RESPONSE_MESSAGE = "JoinRoomResponseMessage";
	private static final String LEAVE_ROOM_MESSAGE = "LeaveRoomMessage";
	private static final String LOGIN_MESSAGE = "LoginMessage";
//	private static final String LOGIN_RESPONSE_MESSAGE = "LoginResponseMessage";
	
	public static Message createMessage(ByteBuffer data) throws WrongMessageTypeException{
		byte[] bytes =  data.array();
		String fullMessage = new String(bytes);
		String messageType = fullMessage.substring(fullMessage.indexOf('=') + 1,fullMessage.indexOf('&'));
		//System.out.println(messageType);
		Message message = createMessageFromString(messageType);
		message.fillMessage(data);
		return message;
		}

	private static Message createMessageFromString( String messageType) throws WrongMessageTypeException{
		Message message;
		if ( messageType.equals(CHAT_MESSAGE)){
			message = new ChatMessage();
		} else if ( messageType.equals(ERROR_MESSAGE)){
			message = new ErrorMessage();
		} else if ( messageType.equals(JOIN_ROOM_MESSAGE)){
			message = new JoinRoomMessage();
//		} else if ( messageType.equals(JOIN_ROOM_RESPONSE_MESSAGE)){
//			message = new JoinRoomResponseMessage();
		} else if ( messageType.equals(LEAVE_ROOM_MESSAGE)){
			message = new LeaveRoomMessage();
		} else if ( messageType.equals(LOGIN_MESSAGE)){
			message = new LoginMessage();
//		} else if ( messageType.equals(LOGIN_RESPONSE_MESSAGE)){
//			message = new LoginResponseMessage();
		} else {
			throw new WrongMessageTypeException("Wrong message type!");
		}
		return message;
	}
	
	
	public static Message createMessage(JSONObject jsonMessage,String username) throws WrongMessageTypeException{
		String message = "class=" + jsonMessage.names().getString(0);
		JSONObject parameters = jsonMessage.getJSONObject(jsonMessage.names().getString(0));
		JSONArray parametersKeys = parameters.names();
		for (int i = 0; i < parametersKeys.size(); i ++){
			message =  message + "&" + parametersKeys.getString(i) + "=" + parameters.getString(parametersKeys.getString(i));
		}
		byte[] bytes = message.getBytes();
		return createMessage(ByteBuffer.wrap(bytes));
	}
}
