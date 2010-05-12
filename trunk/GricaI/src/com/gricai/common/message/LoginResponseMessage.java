package com.gricai.common.message;

import java.nio.ByteBuffer;

import net.sf.json.JSONObject;

public class LoginResponseMessage implements Message {
	
	private static final String TEXT_ISLOGGED = "logged";
	private static final String TEXT_USERNAME = "username";
	
	private boolean logged;
	private String username;

	public LoginResponseMessage(String username){
		this.username = username;
	}
	@Override
	public void fillMessage(ByteBuffer data) {
//		byte[] bytes =  data.array();
//		String fullMessage = new String(bytes);
//		int indexOfLogged = fullMessage.indexOf('&')+1;
//		String loggedString = fullMessage.substring(indexOfLogged);
//		setLogged(Boolean.parseBoolean(loggedString.substring(loggedString.indexOf('=') + 1)));
	/*	if (logged.equals("true")){
			setLogged(true);
		} else setLogged(false);*/
		
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteBuffer toByteBuffer() {
		byte[] bytes = new String("class=LeaveRoomMessage&"+TEXT_ISLOGGED+"="+logged+"&"+TEXT_USERNAME+"="+username).getBytes();
		
		return ByteBuffer.wrap(bytes);
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public boolean isLogged() {
		return logged;
	}

	@Override
	public void fillMessage(JSONObject jsonMessage) {
		// TODO Auto-generated method stub
		
	}

}
