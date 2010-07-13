package com.gricai.common.message.server;

import java.nio.ByteBuffer;

import net.sf.json.JSONObject;

import com.gricai.common.message.Message;

public class LoginResponseMessage implements Message {
	
	private static final String TEXT_MESSAGE = "LoginResponseMessage";
	private static final String TEXT_ISLOGGED = "logged";
	private static final String TEXT_USERNAME = "username";
	
	private boolean logged;
	private String username;

	public LoginResponseMessage(String username){
		this.username = username;
	}
	@Override
	public void fillMessage(ByteBuffer data) {
		byte[] bytes =  data.array();
		String fullMessage = new String(bytes);
		int indexOfUser = fullMessage.indexOf('&')+1;
		int indexOfLogged =  fullMessage.indexOf('&', indexOfUser);
		String userString = fullMessage.substring(indexOfUser,indexOfLogged);
		setUsername(userString.substring(userString.indexOf('=') + 1));
		String loggedString = fullMessage.substring(indexOfLogged);
		setLogged(Boolean.parseBoolean(loggedString.substring(loggedString.indexOf('=') + 1)));
	
		
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public ByteBuffer toByteBuffer() {
		String text = "class=LeaveRoomMessage&"+TEXT_USERNAME+"="+username+"&"+TEXT_ISLOGGED+"="+logged;
		ByteBuffer buffer = ByteBuffer.allocate(text.length()+4);
		buffer.putInt(text.length());
		buffer.put(text.getBytes());
		return buffer;
	}
	
	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public boolean isLogged() {
		return logged;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public JSONObject toJsonObject() {
		JSONObject outer = new JSONObject();
		JSONObject inner = new JSONObject();
		outer.put(TEXT_MESSAGE, inner);
		inner.put(TEXT_USERNAME, username);
		inner.put(TEXT_ISLOGGED, logged);
		return outer;
	}

}
