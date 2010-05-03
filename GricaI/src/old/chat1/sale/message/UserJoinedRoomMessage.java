package old.chat1.sale.message;

import net.sf.json.JSONObject;

public class UserJoinedRoomMessage extends Message {
	
	private String username;

	@Override
	public void fillMessage(JSONObject jsonMessage) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toJsonString() {
		return "{ \"type\" : \"UserJoinedRoomMessage\" , \"username\" : \""+username+"\" }";
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

}
