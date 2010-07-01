package com.gricai.central.server.chatNioMark2;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.gricai.central.server.nioMark2.ChannelFacade;
import com.gricai.central.server.nioMark2.InputHandler;
import com.gricai.central.server.nioMark2.impl.InputHandlerFactory;
import com.gricai.common.message.Message;
import com.gricai.common.message.MessageFactory;
import com.gricai.common.message.common.ChatMessage;
import com.gricai.common.message.exception.WrongMessageTypeException;
import com.gricai.common.message.server.ErrorMessage;
import com.gricai.common.message.server.JoinRoomMessage;
import com.gricai.common.message.server.JoinRoomResponseMessage;
import com.gricai.common.message.server.LeaveRoomMessage;
import com.gricai.common.message.server.LoginMessage;
import com.gricai.common.message.server.LoginResponseMessage;

/**
 * Lobby is used to store all users and all rooms. It is handler factory and it
 * work and distributes all messages
 * 
 * @author aleksandarvaricak
 * 
 */
public class Lobby implements InputHandlerFactory {

	private Map<String, ChatUser> users = Collections
			.synchronizedMap(new HashMap<String, ChatUser>());
	private Map<String, ChatRoom> rooms = Collections
			.synchronizedMap(new HashMap<String, ChatRoom>());

	@Override
	public InputHandler newHandler() throws IllegalAccessException,
			InstantiationException {
		System.out.println("new handler");
		return new ChatHandler(this);
	}

	/**
	 * method handles message and send answer to the client
	 * 
	 * @param message
	 * @param facade
	 */
	void handleMessage(ByteBuffer message, ChannelFacade facade) {
		Message msg = null;
		try {
			msg = MessageFactory.createMessage(message);
		} catch (WrongMessageTypeException e) {
			e.printStackTrace();
		}
		System.out.println("msg: "+msg);
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
				LoginResponseMessage messageResponse = new LoginResponseMessage(
						username);
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
		} else {
			facade.outputQueue().enqueue(
					new ErrorMessage(ErrorMessage.UNKNOWN_MESSAGE)
							.toByteBuffer());
		}
	}

}
