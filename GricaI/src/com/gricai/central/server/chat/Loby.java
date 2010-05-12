package com.gricai.central.server.chat;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.gricai.central.server.nio.ChannelFacade;
import com.gricai.central.server.nio.InputHandler;
import com.gricai.central.server.nio.impl.InputHandlerFactory;
import com.gricai.common.message.ChatMessage;
import com.gricai.common.message.ErrorMessage;
import com.gricai.common.message.JoinRoomMessage;
import com.gricai.common.message.JoinRoomResponseMessage;
import com.gricai.common.message.LeaveRoomMessage;
import com.gricai.common.message.LoginMessage;
import com.gricai.common.message.LoginResponseMessage;
import com.gricai.common.message.Message;
import com.gricai.common.message.MessageFactory;
import com.gricai.common.message.exception.WrongMessageTypeException;

public class Loby implements InputHandlerFactory {

	private Map<String, ChatUser> users = Collections
			.synchronizedMap(new HashMap<String, ChatUser>());
	private Map<String, ChatRoom> rooms = Collections
			.synchronizedMap(new HashMap<String, ChatRoom>());

	@Override
	public InputHandler newHandler() throws IllegalAccessException,
			InstantiationException {
		return new ChatHandler(this);
	}

	void handleMessage(ByteBuffer message, ChannelFacade facade) {
		Message msg = null;
		try {
			msg = MessageFactory.createMessage(message);
		} catch (WrongMessageTypeException e) {
			e.printStackTrace();
		}
		if (msg != null) {
			System.out.println("message: " + msg);
			String username = msg.getUsername();
			if (username == null) {
				facade.outputQueue().enqueue(
						new ErrorMessage(ErrorMessage.NULL_USERNAME)
								.toByteBuffer());
				return;
			}
			if (msg instanceof LoginMessage) {
				ChatUser user = new ChatUser();
				user.setUsername(username);
				user.setFacade(facade);
				LoginResponseMessage messageResponse = new LoginResponseMessage(username);
				boolean checkLog = true;
				// TODO check log in database
				if (checkLog) {
					user.setLogged(true);
					users.put(username, user);
				}
				messageResponse.setLogged(checkLog);
				user.send(messageResponse.toByteBuffer());
			} else {
				ChatUser user = users.get(username);
				if (user == null) {
					facade.outputQueue().enqueue(
							new ErrorMessage(ErrorMessage.NULL_USER)
									.toByteBuffer());
					return;
				}
				if (!user.isLogged()) {
					user.send(new ErrorMessage(ErrorMessage.NOT_LOGGED)
							.toByteBuffer());
					return;
				}
				if (msg instanceof JoinRoomMessage) {
					JoinRoomMessage jrm = (JoinRoomMessage) msg;
					String roomName = jrm.getRoomName();
					ChatRoom room = rooms.get(roomName);
					if (room == null) {
						room = new ChatRoom(roomName);
						rooms.put(roomName, room);
					}
					boolean canJoin = room.addUser(user);
					JoinRoomResponseMessage jrrm = new JoinRoomResponseMessage();
					jrrm.setCanJoin(canJoin);
					user.send(jrrm.toByteBuffer());
				} else if ((msg instanceof ChatMessage || msg instanceof LeaveRoomMessage)
						&& user.getRoom() != null) {
					user.getRoom().recieveMessage(msg);
				} else {
					user.send(new ErrorMessage(ErrorMessage.UNKNOWN_MESSAGE)
							.toByteBuffer());
				}
			}
		}
	}

}
