package old.chat1.sale.message;

import net.sf.json.JSONObject;


public class JoinRoomMessage extends Message {
	
	private String username;
	private String roomName;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toJsonString() {
		
		return null;
	}

	@Override
	public void fillMessage(JSONObject jsonMessage) {
		// TODO Auto-generated method stub
		
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return roomName;
	}

}
