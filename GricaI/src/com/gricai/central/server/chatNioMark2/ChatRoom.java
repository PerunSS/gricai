package com.gricai.central.server.chatNioMark2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.gricai.common.message.Message;
import com.gricai.common.message.server.LeaveRoomMessage;

public class ChatRoom {

	private static int ROOM_SIZE;

	private String roomName;
	private List<ChatUser> users = new ArrayList<ChatUser>();
	
	public ChatRoom(String roomName){
		this.roomName = roomName;
		Properties props = new Properties();
		FileReader reader;
		try {
			reader = new FileReader("room.properties");
			props.load(reader);
			ROOM_SIZE = (Integer) props.get("ROOM_SIZE");
		} catch (FileNotFoundException e) {
			ROOM_SIZE = 40;
		} catch (IOException e) {
			ROOM_SIZE = 40;
		}
		
	}
	
	public boolean addUser(ChatUser user) {
		if (users.size() - 1 > ROOM_SIZE){
			return false;
		}
		users.add(user);
		user.setRoom(this);
		return true;
	}
	
	public void removeUser(String username) {
		ChatUser user = null;
		for(ChatUser u:users){
			if(u.getUsername().equalsIgnoreCase(username)){
				user = u;
				break;
			}
		}
		if(user!=null){
			user.setRoom(null);
			users.remove(user);
		}
	}
	
	public String getRoomName() {
		return roomName;
	}

	public void recieveMessage(Message message) {
		if(message instanceof LeaveRoomMessage){
			removeUser(message.getUsername());
		}else
			broadcast(message.toByteBuffer(), null);
	}

	private void broadcast(ByteBuffer message, ChatUser user) {
		for (ChatUser u : users) {
			if (u != user)
				u.send(message);
		}
	}
}
