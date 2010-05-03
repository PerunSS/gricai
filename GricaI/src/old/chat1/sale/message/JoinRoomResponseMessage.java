package old.chat1.sale.message;

import net.sf.json.JSONObject;

public class JoinRoomResponseMessage extends Message {

	private boolean joined;
	public boolean isJoined() {
		return joined;
	}

	public void setJoined(boolean joined) {
		this.joined = joined;
	}

	@Override
	public void fillMessage(JSONObject jsonMessage) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toJsonString() {
		return "{ \"type\" : \"JoinRoomResponseMessage\" , \"joined\" : \""+joined+"\" }";
	}

}
