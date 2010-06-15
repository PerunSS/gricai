package com.gricai.common.message.game;

import java.nio.ByteBuffer;

import net.sf.json.JSONObject;

import com.gricai.common.message.Message;

public class ChangeTeamMessage implements Message {

	private static final String TEXT_MESSAGE = "ChangePositionMessage";
	private static final String TEXT_USERNAME = "username";
	private static final String TEXT_TEAMNUMBER = "teamNumber";
	
	private int teamNumber;
	private String username;
	
	@Override
	public void fillMessage(ByteBuffer data) {
		byte[] bytes =  data.array();
		String fullMessage = new String(bytes);
		int indexOfUser = fullMessage.indexOf('&')+1;
		int indexOfTeam = fullMessage.indexOf('&', indexOfUser);
		String userString = fullMessage.substring(indexOfUser,indexOfTeam);
		setUsername(userString.substring(userString.indexOf('=') + 1));
		String textString = fullMessage.substring(indexOfTeam);
		setTeamNumber(Integer.parseInt(textString.substring(textString.indexOf('=') + 1)));
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public ByteBuffer toByteBuffer() {
		byte[] bytes = new String("class="+TEXT_MESSAGE+"&"+TEXT_USERNAME+"=" + getUsername() + "&"+TEXT_TEAMNUMBER+"=" + teamNumber).getBytes();
		return ByteBuffer.wrap(bytes);
	}
	
	@Override
	public JSONObject toJsonObject(){
		JSONObject outer = new JSONObject();
		JSONObject inner = new JSONObject();
		outer.put(TEXT_MESSAGE, inner);
		inner.put(TEXT_USERNAME, username);
		inner.put(TEXT_TEAMNUMBER, teamNumber);
		return outer;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}

}
