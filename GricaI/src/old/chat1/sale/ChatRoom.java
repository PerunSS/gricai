package old.chat1.sale;

import java.util.ArrayList;
import java.util.List;

import old.chat1.sale.message.JoinRoomResponseMessage;
import old.chat1.sale.message.Message;
import old.chat1.sale.message.UserJoinedRoomMessage;

import org.jwebsocket.api.WebSocketException;


public class ChatRoom {

	private static final int ROOM_SIZE = 40;

	private String roomName;
	private List<User> users = new ArrayList<User>();

	public boolean addUser(User user) {
		if (users.size() - 1 > ROOM_SIZE){
			try {
				JoinRoomResponseMessage message  = new JoinRoomResponseMessage();
				message.setJoined(false);
				user.getWebSocket().send(message.toJsonString());
			} catch (WebSocketException e) {
				//TODO log err
			}
			return false;
		}
		users.add(user);
		user.setRoom(this);
		
		try {
			JoinRoomResponseMessage message  = new JoinRoomResponseMessage();
			message.setJoined(true);
			user.getWebSocket().send(message.toJsonString());
		} catch (WebSocketException e) {
			//TODO log err
		}
		UserJoinedRoomMessage message = new UserJoinedRoomMessage();
		message.setUsername(user.getUsername());
		broadcast(message.toJsonString(), user);
		return true;
	}

	public void removeUser(User user) {
		user.setRoom(null);
		users.remove(user);
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void recieveMessage(Message message) {
		broadcast(message.toJsonString(), null);
	}

	private void broadcast(String message, User user) {
		for (User u : users) {
			if (u != user)
				try {
					u.getWebSocket().send(message);
				} catch (WebSocketException e) {
					// TODO log
				}
		}
	}

}
