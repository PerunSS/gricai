package com.gricai.central.server.chat;

import java.nio.ByteBuffer;

import com.gricai.central.server.nio.ChannelFacade;

public class ChatUser {

	private String username;
	private boolean logged;
	private ChatRoom room;
	private ChannelFacade facade;
	
	public void send(ByteBuffer message) {
		facade.outputQueue().enqueue(message.asReadOnlyBuffer());
	}	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isLogged() {
		return logged;
	}
	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	public ChatRoom getRoom() {
		return room;
	}
	public void setRoom(ChatRoom room) {
		this.room = room;
	}
	public ChannelFacade getFacade() {
		return facade;
	}
	public void setFacade(ChannelFacade facade) {
		this.facade = facade;
	}
}
